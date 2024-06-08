package mia.kotlin.project2a.config

import mi.example.project2a.config.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {
    fun interceptor(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Url.baseUrl)
            .client(interceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun service() : ApiService = getRetrofit()
        .create(ApiService::class.java)
}