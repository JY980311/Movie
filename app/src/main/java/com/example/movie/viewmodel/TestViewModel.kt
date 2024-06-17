package com.example.movie.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.network.model.credit.CreditData
import com.example.movie.network.model.detail.DetailMovieData
import com.example.movie.network.model.movie.MovieData
import com.example.movie.network.model.image.ImageData
import com.example.movie.network.retrofit.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TestViewModel: ViewModel() {

    private val _movieResponse= MutableStateFlow(MovieData())
    val movieResponse: StateFlow<MovieData> = _movieResponse.asStateFlow()

    private val _imageResponse = MutableStateFlow(ImageData(emptyList(), 0, emptyList(), emptyList()))
    val imageResponse: StateFlow<ImageData> = _imageResponse.asStateFlow()

    private val _selectedMovieIndex = MutableStateFlow(0)
    val selectedMovieIndex: StateFlow<Int> = _selectedMovieIndex.asStateFlow()

    private val _castResponse = MutableStateFlow(CreditData(emptyList(), emptyList(), 0))
    val castResponse: StateFlow<CreditData> = _castResponse.asStateFlow()

    private val _genresResponse = MutableStateFlow(DetailMovieData())
    val genresResponse: StateFlow<DetailMovieData> = _genresResponse.asStateFlow()

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
                _imageResponse.value = apiResponse
                Log.d("ImageData", imageResponse.value.backdrops.toString())
                Log.d("movie_id", movieResponse.value.results[_selectedMovieIndex.value].id.toString())
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

    fun getCreditData() {
        viewModelScope.launch {
            val apiService = RetrofitClient.getApiService()
            try {
                val apiResponse = apiService.getCredits(movieId = movieResponse.value.results[_selectedMovieIndex.value].id)
                Log.d("CreditData", apiResponse.toString())
                _castResponse.value = apiResponse
            } catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.d("TestViewModel", "Error: $errorMessage")
            }
        }
    }

    fun getGenresData() {
        viewModelScope.launch {
            val apiService = RetrofitClient.getApiService()
            try {
                val apiResponse = apiService.getDetail(movieId = movieResponse.value.results[_selectedMovieIndex.value].id)
                Log.d("DetailData", apiResponse.toString())
                _genresResponse.value = apiResponse
            } catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.d("TestViewModel", "Error: $errorMessage")
            }
        }
    }
}