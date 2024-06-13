package com.example.movie.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movie.screen.DetailScreen
import com.example.movie.screen.MainScreen
import com.example.movie.screen.Screen2
import com.example.movie.screen.Screen3
import com.example.movie.viewmodel.TestViewModel
import okhttp3.Route

@Composable
fun NavigationGraph(navController: NavHostController) {

    val viewModel = TestViewModel()

    NavHost(navController = navController, startDestination = MAINSCREEN_ROUTE) {
        composable(MAINSCREEN_ROUTE){
            MainScreen(navHostController = navController, viewModel = viewModel)
        }
        composable(SCREEN2_ROUTE){
            Screen2()
        }
        composable(SCREEN3_ROUTE){
            Screen3()
        }
        composable(DETAILSCRREN_ROUTE){
            DetailScreen(viewModel = viewModel)
        }
    }
}