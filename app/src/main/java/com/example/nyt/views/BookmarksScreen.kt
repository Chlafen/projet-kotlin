package com.example.nyt.views

import PreferencesManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
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
    LazyColumn (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(articles?.response?.docs?.size ?: 0) { index ->
            val article = articles!!.response.docs[index]
            val bookmarks = preferencesManager.getBookmarks()
            if(bookmarks.contains(article._id.replace("nyt://article/", ""))){
                shows = true
                ArticleCard(article,  navController = navController)
            }
        }
        item {
            if(!shows){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = "No Bookmarked Articles", style = MaterialTheme.typography.displayMedium)
                }
            }
        }
    }
}