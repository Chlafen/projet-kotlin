package com.example.nyt.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nyt.data.Article
import com.example.nyt.data.Doc
import com.example.nyt.data.TopArticles
import com.example.nyt.model.ArticleType
import com.example.nyt.model.Metric
import com.example.nyt.model.nytRetrofit
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NYTViewModel : ViewModel() {

    private val _articles = MutableLiveData<Article>()
    private val _apiError = MutableLiveData<String>()
    private val _lastCallDateTime = MutableLiveData<Long>()
    val articles: LiveData<Article> get() = _articles
    val apiError: LiveData<String> get() = _apiError
    private val _topArticles = MutableLiveData<TopArticles>()
    val topArticles: LiveData<TopArticles> get() = _topArticles

    private var searchQuery = MutableStateFlow("")
    val searchText = searchQuery.asStateFlow()

    private val articlesFlow = flowOf(_articles)

    val searchResults: StateFlow<List<Doc>> =
         searchText.combine(articlesFlow) { query, articles ->
                if(query.isEmpty()) articles.value?.response?.docs ?: emptyList()
                articles.value?.response?.docs?.filter {
                    it.headline.main.contains(query, ignoreCase = true)
                } ?: emptyList()
            }
            .stateIn(
                scope = viewModelScope,
                initialValue = articles.value?.response?.docs ?: emptyList(),
                started = SharingStarted.WhileSubscribed(5_000)
            )

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getArticles(articleType: ArticleType, searchQ:String="") {
        val diffTime = (((System.currentTimeMillis() - (_lastCallDateTime.value ?:0))))
        if(diffTime <= 5000){
            Log.d("getArticles", "getArticles: TOO SOON ${diffTime.toString()}")
            return
        }
        _lastCallDateTime.postValue( System.currentTimeMillis())
        val filterQ = "news_desk:(\"${articleType.value}\")"
        val call = nytRetrofit.retrofitService.getArticles(searchQ, filterQ)
        Log.d("request", call.toString())
        call.enqueue(object : Callback<Article> {
            override fun onResponse(
                call: Call<Article>,
                response: Response<Article>
            ) {
                Log.d("response", response.toString())
                if (response.isSuccessful) {
                    _articles.postValue(response.body())
                    articlesFlow.mapLatest { it }
                    _apiError.postValue(null)
                }else{
                    Log.d("error", response.message())
                    _apiError.postValue(response.message())
                }
            }

            override fun onFailure(call: Call<Article>, t: Throwable) {
                _apiError.postValue(t.message)
                Log.e("Error", "Call Failed", t)
            }
        })
    }

    fun onSearchQueryChange(newQuery: String) {
        searchQuery.value = newQuery
    }

    fun getTopArticles(metric: Metric) {
        val diffTime = (((System.currentTimeMillis() - (_lastCallDateTime.value ?:0))))
        if(diffTime <= 5000){
            Log.d("getArticles", "getArticles: TOO SOON ${diffTime.toString()}")
            return
        }
        _lastCallDateTime.postValue( System.currentTimeMillis())
        val call = nytRetrofit.retrofitService.getTopArticles(metric)
        Log.d("request", call.toString())
        call.enqueue(object : Callback<TopArticles> {
            override fun onResponse(call: Call<TopArticles>, response: Response<TopArticles>) {
                if (response.isSuccessful) {
                    _apiError.postValue(null)
                    _topArticles.postValue(response.body())
                }else{
                    _apiError.postValue(response.message())
                }
            }

            override fun onFailure(call: Call<TopArticles>, t: Throwable) {
                Log.e("Error", "Call Failed", t)
                _apiError.postValue(t.message)
            }
        })
    }
}
