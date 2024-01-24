package com.example.nyt

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nyt.views.BookmarksScreen
import com.example.nyt.views.HomeScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = BottomNavItem.Home.route,
 // other parameters
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(BottomNavItem.Home.route) {
            HomeScreen(navController)
        }
        composable(BottomNavItem.Bookmarks.route) {
            BookmarksScreen(navController)
        }
        // /articles/{articleID}
//        composable("articles/{articleID}") {
//            ArticleScreen(navController, it.arguments?.getString("articleID")!!)
//        }
    }
}