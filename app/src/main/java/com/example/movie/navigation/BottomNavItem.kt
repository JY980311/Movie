package com.example.movie.navigation

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: Int
) {
    data object MainScreen : BottomNavItem(MAINSCREEN_ROUTE, "게시물", android.R.drawable.checkbox_on_background)
    data object Screen2 : BottomNavItem(COMMUNITYSCREEN_ROUTE, "메인", android.R.drawable.ic_dialog_info)
    data object Screen3 : BottomNavItem(SCREEN3_ROUTE, "채팅", android.R.drawable.ic_dialog_info)
}