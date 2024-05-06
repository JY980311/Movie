package com.example.movie.screen

import androidx.compose.foundation.layout.height
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.movie.navigation.BottomNavItem

@Composable
fun BottomNavigationBar(
    navController: NavHostController
) {
    val items = listOf<BottomNavItem>(
        BottomNavItem.MainScreen,
        BottomNavItem.Screen2,
        BottomNavItem.Screen3
    )

    var isSelectedTab by remember { mutableStateOf("main") }

    NavigationBar(
        modifier = Modifier.height(54.dp),
        containerColor = Color.Transparent,
        contentColor = Color.Black
    ) {
        items.forEach {item ->
            NavigationBarItem(
                selected = item.route == navController.currentDestination?.route,
                onClick = {
                    navController.navigate(item.route){
                        navController.graph.startDestinationRoute?.let { popUpTo(it){ saveState = true } }
                    }
                    isSelectedTab = item.route
                          },
                label = { Text(text = item.title) },
                icon = { item.icon },
                alwaysShowLabel = true
            )
        }
    }
}