package com.sugosumadesu.buntafujikawa.rssreader

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.buntafujikawa.rssreader.loader.AwsProblemInformationLoader
import com.sugosumadesu.buntafujikawa.rssreader.adapter.LinkAdapter
import com.sugosumadesu.buntafujikawa.rssreader.common.LinkListType
import com.sugosumadesu.buntafujikawa.rssreader.data.Link

import kotlinx.android.synthetic.main.fragment_links.view.*

/**
 * AWSの最新情報一覧を表示するFragment
 * TODO まずはこれで表示をさせてみる、そのあとに共通化をさせるようにする
 */
class AwsProblemInformationFragment : Fragment(), LoaderManager.LoaderCallbacks<List<Link>>, LinkAdapter.OnItemClickListener {

    companion object {
        // LinkListTypeに合わせている
        private const val LOADER_LINKS_TROUBLE: Int = 2

        private const val LINK_LIST_TYPE_PARAM = "link_list_type_param"

        fun newInstance(linkListType: LinkListType): AwsProblemInformationFragment {
            val fragment = AwsProblemInformationFragment()
            val args = Bundle()
            args.putInt(LINK_LIST_TYPE_PARAM, linkListType.rawValue)
            fragment.arguments = args
            return fragment
        }
    }

    // リストがタップされた時のリスナー
    interface AwsProblemInformationFragmentListener {
        fun onLinkClicked(link: Link)
    }

    private lateinit var mAdapter: LinkAdapter

    // インターフェースを実装しているかチェックする
    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context !is AwsProblemInformationFragmentListener) {
            throw RuntimeException(context.javaClass.simpleName + " does not implement AwsProblemInformationFragmentListener")
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

        loaderManager.initLoader(this.arguments!![LINK_LIST_TYPE_PARAM] as Int, null, this)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // ビューが破棄されたらloaderも不要なため
        loaderManager.destroyLoader(LOADER_LINKS_TROUBLE)
    }

    override fun onItemClick(link: Link) {
        val listener: AwsProblemInformationFragmentListener = activity as AwsProblemInformationFragmentListener
        listener.onLinkClicked(link)
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<List<Link>>? {
        if (id == LOADER_LINKS_TROUBLE) {
            val loader: AwsProblemInformationLoader = AwsProblemInformationLoader(this.context!!)
            loader.forceLoad()
            return loader
        }

        return null
    }

    override fun onLoadFinished(loader: Loader<List<Link>>, data: List<Link>?) {
        val id: Int = loader.id

        if (id == LOADER_LINKS_TROUBLE && data != null && data.isNotEmpty()) {
            mAdapter.addItems(data)
        }
    }

    override fun onLoaderReset(loader: Loader<List<Link>>?) {

    }
}
