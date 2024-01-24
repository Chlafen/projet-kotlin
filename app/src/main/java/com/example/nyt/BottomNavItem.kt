package com.example.nyt

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Home : BottomNavItem("home", Icons.Default.Home, "Home")
    object Bookmarks : BottomNavItem("bookmarks", Icons.Default.Favorite, "Bookmarks")
    //values
    companion object {
        val values = listOf(Home, Bookmarks)
    }
}
