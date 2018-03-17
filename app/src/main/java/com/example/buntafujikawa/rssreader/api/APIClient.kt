package com.sugosumadesu.buntafujikawa.rssreader.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {
    private const val TWITTER_URL = "https://api.twitter.com/1.1/search/tweets.json"

    // 現状twitterのapiしか使ってないので、base urlをtwitterとしている
    private fun restClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(TWITTER_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun fetchTweet() {
        // TODO 処理を追加
    }
}
