package com.example.movie.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.network.model.MovieData
import com.example.movie.network.retrofit.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TestViewModel: ViewModel() {

    private val _movieResponse= MutableStateFlow(MovieData())
    val movieResponse: StateFlow<MovieData> = _movieResponse

    private var errorMessage : String by mutableStateOf("")

    init {
        getMovieData()
    }

    fun getMovieData() {
        viewModelScope.launch {
            val apiService = RetrofitClient.getApiService()
            try {
                val apiResponse = apiService.getNowPlaying()
                _movieResponse.value = apiResponse

            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}