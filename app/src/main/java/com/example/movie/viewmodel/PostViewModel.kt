package com.example.movie.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.network.model.post.PostDataItem
import com.example.movie.network.model.post.SendPostData
import com.example.movie.network.retrofit.RetrofitClient
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.concurrent.TimeUnit

class PostViewModel : ViewModel() {

    private val _postResponse = MutableStateFlow<List<PostDataItem>>(emptyList())
    val postResponse: StateFlow<List<PostDataItem>> = _postResponse.asStateFlow()

    private val _postData = MutableStateFlow(PostDataItem("", "", 12, "", ""))
    val postData: StateFlow<PostDataItem> = _postData.asStateFlow()

    private val _sendPostData = MutableStateFlow(SendPostData("", "", "", ""))
    val sendPostData: StateFlow<SendPostData> = _sendPostData.asStateFlow()

    private val _state = MutableStateFlow(State.EMPTY)
    val state:StateFlow<State> = _state.asStateFlow()

    init {
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

    /**
     * 현재 시간을 UTC로 변환하여 반환! -> 한국 즉 우리 로컬시간으로 할려면 +9시간 해줘야 함
     * */
    private fun getCurrentTimestamp(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        return sdf.format(Date())
    }

    /**
     * UTC 시간으로 하면 예를 들어 2024-06-18T02:10:52+00:00 이런식으로 나오는데
     * 이걸 2024-06-18 11:10:52 이런식으로 변환해주는 함수
     * */
    fun convertToLocalTime(utcTime: String): String {
        val sdfUTC = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())
        sdfUTC.timeZone = TimeZone.getTimeZone("UTC")
        val date = sdfUTC.parse(utcTime)

        val sdfLocal = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        sdfLocal.timeZone = TimeZone.getDefault()
        return sdfLocal.format(date)
    }

    /**
     * 자정(0시)를 기준으로 지났으면 하루로 측정하고 그에 맞는 시간을 반환
     * */
    fun getFormattedTimeDifference(utcTime: String): String {
        val sdfUTC = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())
        sdfUTC.timeZone = TimeZone.getTimeZone("UTC")
        val date = sdfUTC.parse(utcTime)

        val now = Calendar.getInstance().time

        /**
         * Calendar = 날짜와 시간을 조작하고 다루는 데 사용되는 추상 클래스,
         * getInstance() = 현재 날짜와 시간을 가진 Calendar 객체를 반환
         * apply() = Calendar 객체의 필드를 설정 -> 초기화와 설정을 간결하게 수행
         *  set(Calendar.HOUR_OF_DAY, 0) = 자정 0시를 의미
         *  set(Calendar.MINUTE, 0) = 0분
         * */
        val midnight = Calendar.getInstance().apply {
            time = now
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.time

        val localDate = Calendar.getInstance().apply {
            time = date
            add(Calendar.HOUR, 9) // 한국 시간으로 변환
        }.time

        val diffMillis = now.time - (date?.time ?: 0)
        val diffMin = TimeUnit.MILLISECONDS.toMinutes(diffMillis)

        return when {
            diffMin < 60 -> {
                // 60분 이내 작성한 글은 X분전 표시
                "${diffMin}분 전"
            }

            date.after(midnight) -> {
                // 하루 이내 작성한 글은 몇시 몇분에 작성했는지 표시
                val sdfTime = SimpleDateFormat("HH:mm", Locale.getDefault())
                sdfTime.format(localDate)
            }

            else -> {
                // 하루 이상 지난 글은 MM/dd 표시
                val sdfDate = SimpleDateFormat("MM/dd", Locale.getDefault())
                sdfDate.format(localDate)
            }
        }
    }

    /**
     * 게시글 작성 후 입력한 데이터를 초기화
     * */
    fun clearPostData() {
        _sendPostData.update { currentData ->
            currentData.copy(title = "", content = "")
        }
    }

    /**
     * 제목, 내용 작성 후 게시글 작성 버튼을 누르면 해당 데이터를 서버로 전송
     * */
    fun createPostData(sendPostData: SendPostData) {
        viewModelScope.launch {
            val apiService = RetrofitClient.getSupabaseApiService()
            try {
                _state.value = State.LOADING
                val testMovie = sendPostData.copy(created_at = getCurrentTimestamp())
                val apiResponse: Response<List<SendPostData>> =
                    apiService.createPost(sendPostData = testMovie)

                if (apiResponse.isSuccessful) {
                    delay(1500)
                    _state.value = State.SUCCESS
                    val responseBody = apiResponse.body()
                    if (!responseBody.isNullOrEmpty()) {
                        Log.d("PostViewModel", "createPostData: 성공 - ${responseBody}")
                    } else {
                        Log.d("PostViewModel", "createPostData: 성공 - 응답 본문이 비어 있음")
                    }
                    clearPostData()
                } else {
                    _state.value = State.ERROR
                    Log.d(
                        "PostViewModel",
                        "createPostData: 실패 - ${apiResponse.errorBody()?.string()}"
                    )
                }
            } catch (e: Exception) {
                _state.value = State.ERROR
                Log.d("PostViewModel", "error: ${e.message}")
                e.printStackTrace() // 전체 스택 트레이스를 로그에 출력
            }
        }
    }
}

/**
 * 상태값 관리
 * */
enum class State {
    SUCCESS,
    ERROR, // ERROR는 사용 안 할 예정
    LOADING,
    EMPTY
}
