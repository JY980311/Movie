package com.example.movie.navigation

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: Int
) {
    data object MainScreen : BottomNavItem("main", "MainScreen", android.R.drawable.checkbox_on_background)
    data object Screen2 : BottomNavItem("screen2", "Screen2", android.R.drawable.ic_dialog_info)
    data object Screen3 : BottomNavItem("screen3", "Screen3", android.R.drawable.ic_dialog_info)
}