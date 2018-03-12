package com.example.buntafujikawa.rssreader.loader

import android.content.Context
import android.support.v4.content.AsyncTaskLoader
import com.sugosumadesu.buntafujikawa.rssreader.data.Link
import com.sugosumadesu.buntafujikawa.rssreader.loader.AddSiteLoader

class AwsProblemInformationLoader(context: Context) : AsyncTaskLoader<List<Link>>(context) {

    override fun loadInBackground(): List<Link>? {
        // TODO XMLに障害情報取得のAPIのパスをまとめて取得したいけど、うまく取れないので後で
//        val factory: XPathFactory = XPathFactory.newInstance()
//        val xPath: XPath = factory.newXPath()
//        val feedListXml = aws_trouble_information_feed_list
//        val items: NodeList = xPath.evaluate("//item", feedListXml, XPathConstants.NODESET) as NodeList

        return AddSiteLoader.fetchFeedList("http://status.aws.amazon.com/rss/ec2-ap-northeast-1.rss")
    }
}