package com.example.nyt.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nyt.data.Article
import com.example.nyt.data.TopArticles
import com.example.nyt.model.ArticleType
import com.example.nyt.model.Metric
import com.example.nyt.model.nytRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NYTViewModel : ViewModel() {

    private val _articles = MutableLiveData<Article>()
    val articles: LiveData<Article> get() = _articles

    private val _topArticles = MutableLiveData<TopArticles>()
    val topArticles: LiveData<TopArticles> get() = _topArticles

    fun getArticles(articleType: ArticleType) {
        val call = nytRetrofit.retrofitService.getArticles(articleType.value)
        Log.d("request", call.toString())
        call.enqueue(object : Callback<Article> {
            override fun onResponse(
                call: Call<Article>,
                response: Response<Article>
            ) {
                Log.d("response", response.toString())
                if (response.isSuccessful) {
                    _articles.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<Article>, t: Throwable) {
                Log.e("Error", "Call Failed", t)
            }
        })
    }


    fun getTopArticles(metric: Metric) {
        val call = nytRetrofit.retrofitService.getTopArticles(metric)
        Log.d("request", call.toString())
        call.enqueue(object : Callback<TopArticles> {
            override fun onResponse(call: Call<TopArticles>, response: Response<TopArticles>) {
                if (response.isSuccessful) {
                    _topArticles.postValue(response.body())

                }
            }

            override fun onFailure(call: Call<TopArticles>, t: Throwable) {
                Log.e("Error", "Call Failed", t)
            }
        })
    }
}
