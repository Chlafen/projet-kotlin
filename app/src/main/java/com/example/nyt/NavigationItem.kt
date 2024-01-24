package com.example.nyt

enum class Screen {
    HOME,
    BOOKMARKS,
}
sealed class NavigationItem(val route: String) {
    object Home : NavigationItem(Screen.HOME.name)
    object Bookmarks : NavigationItem(Screen.BOOKMARKS.name)
}