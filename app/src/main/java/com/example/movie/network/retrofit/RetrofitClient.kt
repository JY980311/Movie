package com.example.movie.network.retrofit

import com.squareup.moshi.Moshi
import com.squareup.moshi.internal.NullSafeJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit

class RetrofitClient {
    companion object { // 객체 생성 없이 사용할 수 있도록 함
        private const val BASE_URL = "https://api.themoviedb.org/3/movie/"

        private const val SUPABASE_URL = "https://jdpoadqnbnzmmygnsadx.supabase.co/rest/v1/"

        private var apiService: IApiService? = null // IApiService 인터페이스를 사용할 수 있도록 선언

        private var supabaseService: SupabaseApiService? = null // supabaseApiService 인터페이스를 사용할 수 있도록 선언

        fun getApiService(): IApiService {
            if (apiService == null) {

                val moshi = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()

                apiService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build()
                    .create(IApiService::class.java)
                    // IApiService 인터페이스를 사용할 수 있도록 생성
            }
            return apiService!! // apiService가 null이 아닌 경우 반환
        }

        fun getSupabaseApiService(): SupabaseApiService {
            if (supabaseService == null) {

                val okHttpClient = OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .build()

                val moshi = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()

                supabaseService = Retrofit.Builder()
                    .baseUrl(SUPABASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .client(okHttpClient)
                    .build()
                    .create(SupabaseApiService::class.java) // IApiService 인터페이스를 사용할 수 있도록 생성
            }
            return supabaseService!! // apiService가 null이 아닌 경우 반환
        }
    }
}

/*
class NullOnEmptyConverterFactory(private val delegate: Converter.Factory) : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type, annotations: Array<Annotation>, retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        val delegateConverter = delegate.responseBodyConverter(type, annotations, retrofit)
        return Converter<ResponseBody, Any> { body ->
            if (body.contentLength() == 0L) null else delegateConverter?.convert(body)
        }
    }
}*/
