package com.example.nyt

import android.net.Uri
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.nyt.viewModel.NYTViewModel
import com.example.nyt.views.ArticlePage
import com.example.nyt.views.BookmarksScreen
import com.example.nyt.views.HomeScreen
import com.example.nyt.views.SearchPage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = BottomNavItem.Home.route,
 // other parameters
) {
    val viewModel = NYTViewModel
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            "articles?id={articleID}&headLineMain={headLineMain}",
            arguments = listOf(
                navArgument("articleID") {
                    type = NavType.StringType
                },
                navArgument("headLineMain") {
                    type = NavType.StringType
                }
            )
        ){  backStackEntry ->
            val articleId = backStackEntry.arguments?.getString("articleID")
            val headLineMain = Uri.decode(backStackEntry.arguments?.getString("headLineMain")?:"")
            viewModel.resetArticles()

            ArticlePage(articleId!!, headLineMain)
        }
        composable("home") {
            HomeScreen(navController)
        }
        composable("search") {
            SearchPage(navController)
        }
        composable("bookmarks") {
            BookmarksScreen(navController)
        }
    }
}