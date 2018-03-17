package com.sugosumadesu.buntafujikawa.rssreader.service

/**
 * TwitterのAPIからAWSの公式アカウントのTweetを取得する
 */
import com.sugosumadesu.buntafujikawa.rssreader.response.Tweet
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

// https://developer.twitter.com/en/docs/tweets/search/api-reference/get-search-tweets.html
interface TwitterService {
    // TODO その他の制御項目も追加をしたほうが良い
    // getの書き方が違う気がするなあ
    @GET("?q={queries}")
    fun fetchTweet(@Path("queries") queries: String) : Call<Tweet>
}
