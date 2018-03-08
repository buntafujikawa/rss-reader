package com.sugosumadesu.buntafujikawa.rssreader.loader

import android.content.AsyncTaskLoader
import android.content.Context

import com.sugosumadesu.buntafujikawa.rssreader.data.Site
import com.sugosumadesu.buntafujikawa.rssreader.database.RssRepository

class SiteListLoader(context: Context) : AsyncTaskLoader<List<Site>>(context) {

    override fun loadInBackground(): List<Site> {
        // 登録されているサイトを全て取得
        return RssRepository.getAllSites(context)
    }
}
