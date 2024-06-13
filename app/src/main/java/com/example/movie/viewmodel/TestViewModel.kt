package com.example.movie.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.network.model.MovieData
import com.example.movie.network.model.image.ImageDatas
import com.example.movie.network.retrofit.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TestViewModel: ViewModel() {

    private val _movieResponse= MutableStateFlow(MovieData())
    val movieResponse: StateFlow<MovieData> = _movieResponse

    private val _selectedMovieIndex = MutableStateFlow(0)
    val selectedMovieIndex: StateFlow<Int> = _selectedMovieIndex

    private val _imageResponse = MutableStateFlow(ImageDatas(emptyList()))
    val imageResponse: StateFlow<ImageDatas> = _imageResponse

    private var errorMessage : String by mutableStateOf("")

    init {
        getMovieData()
    }

    private fun getMovieData() {
        viewModelScope.launch {
            val apiService = RetrofitClient.getApiService()
            try {
                val apiResponse = apiService.getNowPlaying(page = 1)
                val apiResoonse2 = apiService.getNowPlaying(page = 2)
                val combinationApi = apiResponse.results + apiResoonse2.results

                _movieResponse.value = apiResponse.copy(results = combinationApi)

            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun getImageData() {
        viewModelScope.launch {
            val apiService = RetrofitClient.getApiService()
            try {
                val apiResponse = apiService.getImages(movieId = movieResponse.value.results[_selectedMovieIndex.value].id)
                Log.d("TestViewModel", "Image Response: $apiResponse")
                _imageResponse.value = apiResponse
            } catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.d("TestViewModel", "Error: $errorMessage")
            }
        }
    }

    fun getSelectedMovieIndex(index: Int) {
        if (index >= 0 && index < _movieResponse.value.results.size) {
            _selectedMovieIndex.value = index
            Log.d("MainScreen", "Selected Index2222: $index") /** **/
        }
    }
}