package com.example.movie.network.retrofit

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitClient {
    companion object { // 객체 생성 없이 사용할 수 있도록 함
        private const val BASE_URL = "https://api.themoviedb.org/3/movie/"

        private const val SUPABASE_URL = "https://jdpoadqnbnzmmygnsadx.supabase.co/rest/v1/"

        private var apiService: IApiService? = null // IApiService 인터페이스를 사용할 수 있도록 선언

        private var supabaseService: supabaseApiService? = null // supabaseApiService 인터페이스를 사용할 수 있도록 선언

        fun getApiService(): IApiService {
            if (apiService == null) {

                val moshi = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()

                apiService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build()
                    .create(IApiService::class.java) // IApiService 인터페이스를 사용할 수 있도록 생성
            }
            return apiService!! // apiService가 null이 아닌 경우 반환
        }

        fun getSupabaseApiService(): supabaseApiService {
            if (supabaseService == null) {

                val moshi = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()

                supabaseService = Retrofit.Builder()
                    .baseUrl(SUPABASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build()
                    .create(supabaseApiService::class.java) // IApiService 인터페이스를 사용할 수 있도록 생성
            }
            return supabaseService!! // apiService가 null이 아닌 경우 반환
        }
    }
}