package com.example.movie.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movie.screen.MainScreen
import com.example.movie.screen.Screen2
import com.example.movie.screen.Screen3

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "main") {
        composable("main"){
            MainScreen()
        }
        composable("screen2"){
            Screen2()
        }
        composable("screen3"){
            Screen3()
        }
    }
}