package com.example.buntafujikawa.rssreader.loader

import android.content.Context
import android.support.v4.content.AsyncTaskLoader

import com.sugosumadesu.buntafujikawa.rssreader.data.Link
import com.sugosumadesu.buntafujikawa.rssreader.net.HttpGet
import com.sugosumadesu.buntafujikawa.rssreader.parser.RssParser
import org.w3c.dom.NodeList

import java.io.InputStream
import javax.xml.xpath.XPath
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory

class AwsProblemInformationLoader(context: Context) : AsyncTaskLoader<List<Link>>(context) {

    override fun loadInBackground(): List<Link>? {
        // TODO XMLに障害情報取得のAPIのパスをまとめて取得したいけど、うまく取れないので後で
//        val factory: XPathFactory = XPathFactory.newInstance()
//        val xPath: XPath = factory.newXPath()
//        val feedListXml = aws_trouble_information_feed_list
//        val items: NodeList = xPath.evaluate("//item", feedListXml, XPathConstants.NODESET) as NodeList

        // TODO 全部動いたらリファクタする
        val httpGet: HttpGet = HttpGet("http://status.aws.amazon.com/rss/ec2-ap-northeast-1.rss")

        if (!httpGet.get()) return null

        // レスポンスの解析
        val input: InputStream = httpGet.getResponse()
        val parser: RssParser = RssParser()
        if (!parser.parse(input)) return null

        return parser.getLinkList()
    }
}