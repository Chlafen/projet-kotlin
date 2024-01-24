package com.example.nyt.views

import PreferencesManager
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.nyt.model.ArticleType
import com.example.nyt.viewModel.NYTViewModel

@Composable
fun BookmarksScreen(navController: NavController) {
    val viewModel: NYTViewModel = NYTViewModel()
    viewModel.getArticles( ArticleType.Science)
    val articles by viewModel.articles.observeAsState()

    val ctx = LocalContext.current
    val preferencesManager = remember { PreferencesManager(ctx) }

    var shows = false
    val apiError by viewModel.apiError.observeAsState()

    if(apiError != null) {
        Log.d("errView", "HomeScreen: $apiError")
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Error: ${apiError.toString()}")
            Button(onClick = {
                viewModel.getArticles(ArticleType.Science)
            }) {
                Text(text = "Retry")
            }
        }
    }else {
        val bookmarks = preferencesManager.getBookmarks()
        val bookmarkedArticles = with(articles) {
            this?.response?.docs?.filter {
                bookmarks.contains(it._id.replace("nyt://article/", ""))
            }
        }
        if(bookmarkedArticles.isNullOrEmpty()){
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "No Bookmarks")
            }
        }else {
            LazyColumn(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(bookmarkedArticles.size ?: 0) { index ->
                    val article = bookmarkedArticles[index]
                    ArticleCard(article, navController = navController)

                }

            }
        }
    }
}