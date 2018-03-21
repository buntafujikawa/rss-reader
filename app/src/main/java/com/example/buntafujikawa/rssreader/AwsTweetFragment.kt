package com.sugosumadesu.buntafujikawa.rssreader

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sugosumadesu.buntafujikawa.rssreader.adapter.LinkAdapter
import com.sugosumadesu.buntafujikawa.rssreader.api.APIClient
import com.sugosumadesu.buntafujikawa.rssreader.common.LinkListType
import com.sugosumadesu.buntafujikawa.rssreader.data.Link

import kotlinx.android.synthetic.main.fragment_links.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * AWSの公式ツイッターアカウントのツイートを表示するFragment
 */
class AwsTweetFragment : Fragment(), LinkAdapter.OnItemClickListener {

    companion object {

        private const val LINK_LIST_TYPE_PARAM = "link_list_type_param"

        private lateinit var links: MutableList<Link>

        fun newInstance(linkListType: LinkListType): AwsTweetFragment {
            val fragment = AwsTweetFragment()
            val args = Bundle()
            args.putInt(LINK_LIST_TYPE_PARAM, linkListType.rawValue)
            fragment.arguments = args
            return fragment
        }
    }

    // リストがタップされた時のリスナー
    interface AwsTweetFragmentListener {
        fun onLinkClicked(link: Link)
    }

    private lateinit var mAdapter: LinkAdapter

    // インターフェースを実装しているかチェックする
    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context !is AwsTweetFragmentListener) {
            throw RuntimeException(context.javaClass.simpleName + " does not implement AwsTweetFragmentListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val v: View = inflater.inflate(R.layout.fragment_links, container, false)
        val context: Context = inflater.context
        val recyclerView: RecyclerView = v.LinkList as RecyclerView

        // LayoutManagerは必須
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        mAdapter = LinkAdapter(context)
        mAdapter.setOnItemClickListener(this)
        recyclerView.adapter = mAdapter

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val dateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US)
        links = ArrayList<Link>()

        APIClient.fetchAWSTweetList(context!!, {
            it.forEach {
                // すごい手抜きだけど、時間かけたくないのでこれでよしとする
                val link = Link(title = "アマゾンウェブサービス", description = it.text, pubDate = dateFormat.parse(it.created_at).time, url = "https://twitter.com/awscloud_jp")
                links.add(link)
            }

            mAdapter.addItems(links)
        },
            // 処理失敗した場合
            Log.w("AwsTweetFragment", "fetchAWSTweetList error")
        )
    }

    override fun onItemClick(link: Link) {
        val listener: AwsTweetFragmentListener = activity as AwsTweetFragmentListener
        listener.onLinkClicked(link)
    }
}
