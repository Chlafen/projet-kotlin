package com.example.nyt.model
import com.example.nyt.data.Article
import com.example.nyt.data.TopArticles
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface nytAPI {

    // Returns 10 articles of a certain type
    @GET("search/v2/articlesearch.json")
    fun getArticles(
        @Query("sort") sort: String = "newest",
        @Query("q") searchQuery: String,
        @Query("fq") filterQuery: String = "news_desk:()",
        @Query("api-key") apiKey: String = "KJ30ktW6tV37FlGsqgXVHl6Qk3TjMGTt",
    ): Call<Article>
    // Returns 20 Top Articles in the last week on a certain metric
    // metric can be "viewed" | "shared" | "emailed"
    @GET("mostpopular/v2/{metric}/7.json")
    fun getTopArticles(
        @Query("metric") metric: Metric,
        @Query("api-key") apiKey: String = "KJ30ktW6tV37FlGsqgXVHl6Qk3TjMGTt"
   ): Call<TopArticles>
}


