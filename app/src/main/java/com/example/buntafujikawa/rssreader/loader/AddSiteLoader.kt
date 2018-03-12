package com.sugosumadesu.buntafujikawa.rssreader.loader

import android.content.AsyncTaskLoader
import android.content.Context

import com.sugosumadesu.buntafujikawa.rssreader.data.Link
import com.sugosumadesu.buntafujikawa.rssreader.data.Site
import com.sugosumadesu.buntafujikawa.rssreader.database.RssRepository
import com.sugosumadesu.buntafujikawa.rssreader.net.HttpGet
import com.sugosumadesu.buntafujikawa.rssreader.parser.RssParser

import java.io.InputStream

/**
 * 記事の一覧を取得するためのLoader
 */

class AddSiteLoader(context: Context, private var url: String) : AsyncTaskLoader<Site>(context) {

    companion object {
        fun fetchFeedList(url: String, parser: RssParser = RssParser()): List<Link>? {
            if (url.isEmpty()) return null

            // RSSフィードをダウンロード
            val httpGet: HttpGet = HttpGet(url)
            if (!httpGet.get()) return null

            // レスポンスの解析
            val input: InputStream = httpGet.getResponse()
            if (!parser.parse(input)) return null

            return parser.getLinkList()
        }
    }

    override fun loadInBackground(): Site? {
        val parser: RssParser = RssParser()
        val links: List<Link> = fetchFeedList(this.url, parser) ?: return null
        val site: Site = parser.getSite()

        site.url = this.url
        site.linkCount = links.size.toLong()

        // サイトを登録
        val feedId: Long = RssRepository.insertSite(context, site)
        site.id = feedId

        if (feedId > 0 && links.isNotEmpty()) {
            // 記事の登録
            RssRepository.insertLinks(context, feedId, links)

            return site
        }

        return null
    }
}
