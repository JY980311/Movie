package com.example.movie.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movie.screen.DetailScreen
import com.example.movie.screen.MainScreen
import com.example.movie.screen.CommunityScreen
import com.example.movie.screen.Screen3
import com.example.movie.screen.WriteScreen
import com.example.movie.viewmodel.PostViewModel
import com.example.movie.viewmodel.TestViewModel

@Composable
fun NavigationGraph(navController: NavHostController) {

    val viewModel = TestViewModel()

    NavHost(navController = navController, startDestination = MAINSCREEN_ROUTE) {
        composable(MAINSCREEN_ROUTE){
            MainScreen(navHostController = navController, viewModel = viewModel)
        }
        composable(COMMUNITYSCREEN_ROUTE){
            CommunityScreen(navHostController = navController, viewModel = PostViewModel())
        }
        composable(SCREEN3_ROUTE){
            Screen3()
        }
        composable(DETAILSCRREN_ROUTE){
            DetailScreen(viewModel = viewModel)
        }
        composable(WRITESCREEN_ROUTE){
            WriteScreen(viewModel = PostViewModel())
        }
    }
}