package com.example.nyt.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nyt.data.Article
import com.example.nyt.model.ArticleType
import com.example.nyt.model.nytRetrofit
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.mapLatest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object NYTViewModel : ViewModel() {
    private val _articles = MutableLiveData<Article>()
    private val _articlesType = MutableLiveData<ArticleType>()
    private val _apiError = MutableLiveData<String>()
    private val _loadingState = MutableLiveData<Boolean>()
    private val _lastCallDateTime = MutableLiveData<Long>()
    val articles: LiveData<Article> get() = _articles
    val apiError: LiveData<String> get() = _apiError
    val articlesType: LiveData<ArticleType> get() = _articlesType

    val loadingState: LiveData<Boolean> get() = _loadingState

    private var searchQuery = MutableStateFlow("")

    private val articlesFlow = flowOf(_articles)

    fun resetArticles(){
        _articles.postValue(null)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getArticles(searchQ: String = "", articleType: ArticleType? = null) {
        val diffTime = (((System.currentTimeMillis() - (_lastCallDateTime.value ?:0))))
        if(diffTime <= 5000){
            return
        }
        _lastCallDateTime.postValue( System.currentTimeMillis())
        val filterQ = "news_desk:(\"${articleType?.value?: _articlesType.value?.value?:""}\")"
        val call = nytRetrofit.retrofitService
            .getArticles(
                sort="newest",
                searchQuery= searchQ,
                filterQuery = filterQ
            )
        _loadingState.postValue(true)
        Log.d("NYTViewModel", "getArticles: $searchQ $filterQ $diffTime")

        call.enqueue(object : Callback<Article> {
            override fun onResponse(
                call: Call<Article>,
                response: Response<Article>
            ) {
                _loadingState.postValue(false)
                if (response.isSuccessful) {
                    _articles.postValue(response.body())
                    articlesFlow.mapLatest { it }
                    _apiError.postValue(null)
                }else{
                    _apiError.postValue(response.message())
                    _loadingState.postValue(false)
                }
            }

            override fun onFailure(call: Call<Article>, t: Throwable) {
                _loadingState.postValue(false)
                _apiError.postValue(t.message)
            }
        })
    }

    fun onSearchQueryChange(newQuery: String) {
        searchQuery.value = newQuery
    }

    fun setCategory(articleType: ArticleType) {
        Log.d("NYTViewModel", "setCategory: ${articleType.value}")
        _articlesType.postValue(articleType)
        getArticles(articleType = articleType)
    }
}
