package com.sugosumadesu.buntafujikawa.rssreader.loader

import android.content.Context
import android.support.v4.content.AsyncTaskLoader

import com.sugosumadesu.buntafujikawa.rssreader.data.Link
import com.sugosumadesu.buntafujikawa.rssreader.net.HttpGet
import com.sugosumadesu.buntafujikawa.rssreader.parser.RssParser

import java.io.InputStream

class AwsNewestInformationLoader(context: Context) : AsyncTaskLoader<List<Link>>(context) {
    companion object {
        const val AWS_NEW_FEED_URL = "https://aws.amazon.com/jp/new/feed/"
    }

    override fun loadInBackground(): List<Link>? {
        // TODO AddSiteLoaderの一部を持ってきてるので、この作りで行くなら使いまわせるようにしたいけどまずは動くように。。
        val httpGet: HttpGet = HttpGet(AWS_NEW_FEED_URL)

        httpGet.get()
        if (!httpGet.get()) return null

        // レスポンスの解析
        val input: InputStream = httpGet.getResponse()
        val parser: RssParser = RssParser()
        if (!parser.parse(input)) return null

        return parser.getLinkList()
    }
}
