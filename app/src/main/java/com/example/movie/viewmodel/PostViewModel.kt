package com.example.movie.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.network.model.post.PostDataItem
import com.example.movie.network.model.post.SendPostData
import com.example.movie.network.retrofit.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class PostViewModel : ViewModel() {

    private val _postResponse = MutableStateFlow<List<PostDataItem>>(emptyList())
    val postResponse: StateFlow<List<PostDataItem>> = _postResponse.asStateFlow()

    private val _postData = MutableStateFlow(PostDataItem("", "", 12, "", ""))
    val postData: StateFlow<PostDataItem> = _postData.asStateFlow()

    private val _sendPostData = MutableStateFlow(SendPostData("","",""))
    val sendPostData: StateFlow<SendPostData> = _sendPostData.asStateFlow()

    init{
        getAllPostData()
    }

    fun getAllPostData() {
        viewModelScope.launch {
            val apiService = RetrofitClient.getSupabaseApiService()
            try {
                val apiResponse: List<PostDataItem> = apiService.getAllPost()
                Log.d("PostViewModel", "getAllPostData: 성공")
                _postResponse.value = apiResponse
                Log.d("PostViewModel", "getAllPostData: ${postResponse.value}")
            } catch (e: Exception) {
                Log.d("PostViewModel", "error: ${e}")
            }
        }
    }

    fun inputTitle(title: String) {
        _sendPostData.update { currentTitle ->
            currentTitle.copy(title = title)
        }
    }

    fun inputContent(content: String) {
        _sendPostData.update { currentTitle ->
            currentTitle.copy(content = content)
        }
    }

    private fun getCurrentTimestamp(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        return sdf.format(Date())
    }

    fun createPostData(sendPostData: SendPostData) {
        viewModelScope.launch {
            val apiService = RetrofitClient.getSupabaseApiService()
            try {
                val testMovie = sendPostData.copy(movie = "testMovie")
                val apiResponse: Response<SendPostData> = apiService.createPost(sendPostData = testMovie)

                if (apiResponse.isSuccessful) {
                    Log.d("PostViewModel", "createPostData: 성공")
                    Log.d("PostViewModel", "createPostData: ${apiResponse.body()}")
                } else {
                    Log.d("PostViewModel", "createPostData: 실패 - ${apiResponse.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.d("PostViewModel", "error: ${e.message}")
            }
        }
    }
 }