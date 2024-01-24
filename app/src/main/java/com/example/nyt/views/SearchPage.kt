package com.example.nyt.views

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nyt.model.ArticleType
import com.example.nyt.viewModel.NYTViewModel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@ExperimentalMaterial3Api
@Composable
fun SearchPage(navController: NavController) {
    val viewModel: NYTViewModel = NYTViewModel()

    viewModel.getArticles( ArticleType.All, "")
    val searchText by viewModel.searchText.collectAsState()
    val searchResults by viewModel.searchResults.collectAsStateWithLifecycle()
    Log.d("searchResults", searchResults.toString())
//    Column {
    SearchBar(
        query = searchText,
        onQueryChange = viewModel::onSearchQueryChange,
        onSearch = {},
        placeholder = {
            Text(text = "Search Articles")
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = null
            )
        },
        trailingIcon = {},
        active = true,
        onActiveChange = {},
    ){
        LazyColumn {
            items(searchResults.size ) { index ->
                Text(
                    searchResults[index].headline.main,
                    modifier = Modifier.padding(
                        start = 8.dp,
                        top = 4.dp,
                        end = 8.dp,
                        bottom = 4.dp
                    )
                )
            }
        }
    }
}