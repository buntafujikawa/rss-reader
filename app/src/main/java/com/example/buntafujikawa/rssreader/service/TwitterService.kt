package com.sugosumadesu.buntafujikawa.rssreader.service

import com.sugosumadesu.buntafujikawa.rssreader.response.Tweet
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

// https://developer.twitter.com/en/docs/tweets/timelines/api-reference/get-statuses-user_timeline
interface TwitterService {
    @GET("statuses/user_timeline.json")
    fun fetchTweet(@Query("screen_name") name: String, @Query("count") count: Int) : Observable<List<Tweet>>
}
