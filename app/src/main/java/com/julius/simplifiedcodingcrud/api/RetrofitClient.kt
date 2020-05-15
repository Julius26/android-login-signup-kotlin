package com.julius.simplifiedcodingcrud.api

import android.util.Base64
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//SINGLE ton pattern class
object RetrofitClient {

    private val AUTH = "Basic" + Base64.encodeToString("julius26:Ntheketha26*".toByteArray(),Base64.NO_WRAP)
    private const val BASE_URL = "http://192.168.42.191/android/slim/public/index.php/"

//    defining a client
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor{chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .addHeader("Authorization", AUTH)
                .method(original.method(), original.body())

            val request = requestBuilder.build()
            chain.proceed(request)
        }.build()


//    lazy takes a lambda and returns an instance of lazy
    val instance: Api by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
//                authentication
            .client(okHttpClient)
            .build()

    retrofit.create(Api::class.java)

    }
}