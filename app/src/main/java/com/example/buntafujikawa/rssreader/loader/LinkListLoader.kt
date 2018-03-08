package com.sugosumadesu.buntafujikawa.rssreader.loader

import android.support.v4.content.AsyncTaskLoader
import android.content.Context

import com.sugosumadesu.buntafujikawa.rssreader.data.Link
import com.sugosumadesu.buntafujikawa.rssreader.database.RssRepository

class LinkListLoader(context: Context) : AsyncTaskLoader<List<Link>>(context) {
    override fun loadInBackground(): List<Link> {
        return RssRepository.getAllLinks(context)
    }
}
