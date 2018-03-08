package com.sugosumadesu.buntafujikawa.rssreader.parser

import com.sugosumadesu.buntafujikawa.rssreader.data.Link
import com.sugosumadesu.buntafujikawa.rssreader.data.Site

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

import javax.xml.xpath.XPath
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathExpressionException
import javax.xml.xpath.XPathFactory

import org.w3c.dom.Document
import org.w3c.dom.Node
import org.w3c.dom.NodeList

/**
 * RSS1用のパーサ
 */
class Rss1Parser : FeedParser {

    private lateinit var site: Site
    private lateinit var links: List<Link>

    override fun parse(document: Document): Boolean {
        val factory: XPathFactory = XPathFactory.newInstance()
        val xPath: XPath = factory.newXPath()

        try {
            val siteTitle: String = xPath.evaluate("//channel/title/text()", document)
            val siteDescription: String = xPath.evaluate("//channel/description/text()", document)

            this.site = Site(title = siteTitle, description = siteDescription)

            // ドキュメント内の<item>要素を全て取り出す
            val items: NodeList = xPath.evaluate("//item", document, XPathConstants.NODESET) as NodeList

            // 日付文字をdateに変換するためのformatter
            val dateFormat: SimpleDateFormat = SimpleDateFormat("yyy-MM-dd'T'HH:mm:ssz", Locale.ENGLISH)

            val articleCount: Int = items.length
            for (i in 0 until articleCount) {
                val item: Node = items.item(i)

                val linkTitle: String = xPath.evaluate("./title/text()", item)
                val linkDescription: String = xPath.evaluate("./description/text()", item)

                val pubDate: String = xPath.evaluate("./date/text()", item)
                val linkPubDate: Long = if (pubDate.isEmpty()) -1L else dateFormat.parse(pubDate).time

                val linkUrl: String = xPath.evaluate("./link/text()", item)

                val link: Link = Link(title =  linkTitle, description = linkDescription, pubDate = linkPubDate, url = linkUrl)
            }

            return true

        } catch (e: XPathExpressionException) {
            e.printStackTrace()
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return false
    }

    override fun getSite(): Site {
        return site
    }

    override fun getLinkList(): List<Link> {
        return links
    }
}
