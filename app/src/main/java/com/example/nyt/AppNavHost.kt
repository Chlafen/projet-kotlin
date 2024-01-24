package com.example.nyt

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.nyt.views.ArticlePage
import com.example.nyt.views.BookmarksScreen
import com.example.nyt.views.HomeScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = BottomNavItem.Home.route,
 // other parameters
) {
    val connection by connectivityState()

    val isConnected = connection === ConnectionState.Available
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            "articles?id={articleID}",
            arguments = listOf(
                navArgument("articleID") {
                    type = NavType.StringType
                }
            )
        ){  backStackEntry ->
            val articleId = backStackEntry.arguments?.getString("articleID")
            Log.d("AppNavHost", "AppNavHost: $articleId")
            ArticlePage(navController, articleId!!)
        }
        composable("home") {
            HomeScreen(navController)
        }
        composable("bookmarks") {
            BookmarksScreen(navController)
        }
    }
}