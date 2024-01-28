package com.example.nyt.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.nyt.data.Doc
import com.example.nyt.model.ArticleType
import com.example.nyt.viewModel.NYTViewModel
import com.example.nyt.widgets.MenuDropDown
import kotlin.math.min

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel = NYTViewModel
    viewModel.getArticles()
    val articles by viewModel.articles.observeAsState()
    val apiError by viewModel.apiError.observeAsState()
    val loadingState by viewModel.loadingState.observeAsState()
    val articlesType by viewModel.articlesType.observeAsState()

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
                viewModel.getArticles()
            }) {
                Text(text = "Retry")
            }
        }
    }else if(loadingState == true) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }else{
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            MenuDropDown(
                onChanged = { newType -> run {
                    Log.d("HomeScreen", "category changed to ${newType.value}")
                }},
                initialValue = articlesType ?: ArticleType.All
            )
            LazyColumn (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(articles?.response?.docs?.size ?: 0) { index ->
                    ArticleCard(article = articles!!.response.docs[index], navController = navController)
                }
            }
        }

    }
}

@Composable
fun ArticleCard(article: Doc, navController: NavController) {

    Button (
        onClick = {
            val articleId= article._id.split('/')[3]
            navController.navigate("articles?id=$articleId&headLineMain=${article.headline.main}")
        },
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(0.dp)
            .background(Color.Transparent)
            .shadow(0.dp, RectangleShape, clip = false)
            .border(0.dp, Color.Transparent, RectangleShape),
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .shadow(0.dp, RectangleShape, clip = false)
                .heightIn(min = 100.dp, max = 200.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            )
        ) {
            var url =
                "https://developers.elementor.com/docs/assets/img/elementor-placeholder-image.png"
            if (article.multimedia.isNotEmpty()) {
                url = "https://www.nytimes.com/${article.multimedia[0].url}"
            }

            Row {
                AsyncImage(
                    model = url,
                    modifier = Modifier
                        .width(180.dp) ,
                    contentScale = ContentScale.FillHeight,
                    contentDescription = article.headline.main,
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(
                        text = article.headline.main.substring(
                            0,
                            min(25, article.headline.main.length)
                        ) + "...", style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = article.abstract.substring(
                            0,
                            min(70, article.abstract.length)
                        ) + "...", style = MaterialTheme.typography.bodyMedium
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            text = article.pub_date.substring(0, min(10, article.pub_date.length)),
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(text = " | ", style = MaterialTheme.typography.bodySmall)
                        Text(text = article.source?:"Unknown Source", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}