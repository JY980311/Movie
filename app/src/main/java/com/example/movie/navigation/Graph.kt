package com.example.movie.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movie.screen.MainScreen
import com.example.movie.screen.Screen2
import com.example.movie.screen.Screen3
import com.example.movie.viewmodel.TestViewModel
import okhttp3.Route

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = MAIN_ROUTE) {
        composable(MAIN_ROUTE){
            MainScreen(navHostController = navController, viewModel = TestViewModel())
        }
        composable(SCREEN2_ROUTE){
            Screen2()
        }
        composable(SCREEN3_ROUTE){
            Screen3()
        }
    }
}