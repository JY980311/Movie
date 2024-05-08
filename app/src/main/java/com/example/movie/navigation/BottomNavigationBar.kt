package com.example.movie.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun BottomNavigationBar(
    navController: NavHostController
) {
    val items = listOf(
        BottomNavItem.MainScreen,
        BottomNavItem.Screen2,
        BottomNavItem.Screen3
    )

    var isSelectedTab by remember { mutableStateOf("main") }

    NavigationBar(
        modifier = Modifier.height(50.dp),
        containerColor = Color.LightGray,
        contentColor = Color.Black
    ) {
        items.forEach {item ->
            NavigationBarItem(
                selected = item.route == navController.currentDestination?.route, // 현재 선택된 탭이 무엇인지 확인
                onClick = {
                    navController.navigate(item.route){// 현재 탭을 누르면 해당 탭으로 이동
                        navController.graph.startDestinationRoute?.let { popUpTo(it){ saveState = true } } // 현재 탭을 누르면 이전 탭으로 이동
                    }
                    isSelectedTab = item.route
                          },
                label = { Text(text = item.title) },
                icon = {},
                alwaysShowLabel = true
            )
        }
    }
}