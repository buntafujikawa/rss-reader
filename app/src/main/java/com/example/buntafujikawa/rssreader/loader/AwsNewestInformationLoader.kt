package com.sugosumadesu.buntafujikawa.rssreader.loader

import android.content.Context
import android.support.v4.content.AsyncTaskLoader
import com.sugosumadesu.buntafujikawa.rssreader.data.Link

class AwsNewestInformationLoader(context: Context) : AsyncTaskLoader<List<Link>>(context) {
    companion object {
        const val AWS_NEW_FEED_URL = "https://aws.amazon.com/jp/new/feed/"
    }

    override fun loadInBackground(): List<Link>? {
        return AddSiteLoader.fetchFeedList(AWS_NEW_FEED_URL)
    }
}
