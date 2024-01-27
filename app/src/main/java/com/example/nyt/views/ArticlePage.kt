package com.example.nyt.views

import PreferencesManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.nyt.model.ArticleType
import com.example.nyt.viewModel.NYTViewModel

@Composable
fun ArticlePage(articleID: String, headLineMain: String?) {
    val viewModel = NYTViewModel
    viewModel.getArticles(ArticleType.All, headLineMain?:"")
    val articles by viewModel.articles.observeAsState()
    val size = articles?.response?.docs?.size ?: 0
    val apiError by viewModel.apiError.observeAsState()

    if(apiError != null) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Error: ${apiError.toString()}")
            Button(onClick = {
                viewModel.getArticles(ArticleType.All, headLineMain?:"")
            }) {
                Text(text = "Retry")
            }
        }
    }else if(size > 0) {
        val article =
            articles!!.response.docs.find { it._id.contains(articleID) || it.headline.main.contains(headLineMain?:"") }

        if(article != null) {
            var url =
                "https://developers.elementor.com/docs/assets/img/elementor-placeholder-image.png"
            if (article.multimedia.isNotEmpty()) {
                url = "https://www.nytimes.com/${article.multimedia[0].url}"
            }
            val uriHandler = LocalUriHandler.current
            val ctx = LocalContext.current
            val preferencesManager = remember { PreferencesManager(ctx) }
            val data = remember {
                mutableStateOf(preferencesManager.isBookmarked(articleID))
            }

            LazyColumn (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(14.dp)
            ){
                item{
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = article.headline.main, style = MaterialTheme.typography.displaySmall)

                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = article.source?:"Unknown Source", style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(12.dp))
                    AsyncImage(
                        model = url,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentScale = ContentScale.FillWidth,
                        contentDescription = article.headline.main,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = article.abstract, style = MaterialTheme.typography.bodyLarge)
                    // read more
                    Spacer(modifier = Modifier.height(24.dp))
                    Row (
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth(),
                    ){
                        Button(
                            onClick = {
                                uriHandler.openUri(article.web_url)
                            }
                        ) {
                            Text("Read More...")
                        }
                        Box {
                            Button(
                                onClick = {
                                    preferencesManager.toggleBookmark(articleID)
                                    data.value = preferencesManager.isBookmarked(articleID)
                                },
                                shape = CircleShape,
                                contentPadding = PaddingValues(1.dp),
                                modifier = Modifier
                                    .size(40.dp),
                            ) {
                                // Inner content including an icon and a text label
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "Favorite",
                                    modifier = Modifier.size(20.dp),
                                    tint = if(data.value) Color.Green else Color.White
                                )
                            }
                        }
                    }
                }

            }
        }
    }
    else{
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "No Article Found")
        }
    }
}
