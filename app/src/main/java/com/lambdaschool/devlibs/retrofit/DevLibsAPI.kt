package com.lambdaschool.devlibs.retrofit

import com.google.gson.Gson
import com.lambdaschool.devlibs.model.LoginSendAPI
import com.lambdaschool.devlibs.model.LoginReturnedAPI
import com.lambdaschool.devlibs.model.RegistrationReturnedAPI
import com.lambdaschool.devlibs.model.RegistrationSendAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface DevLibsAPI {

    @POST("register")
    fun registerUser(@Body registrationInfo: RegistrationSendAPI): Call<RegistrationReturnedAPI>

    @POST("login")
    fun loginUser(@Body loginInfo: LoginSendAPI): Call<LoginReturnedAPI>

    class Factory {

        companion object {
            val BASE_URL = ""
            val gson = Gson()
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
                level = HttpLoggingInterceptor.Level.BODY
            }


            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(logger)
                .retryOnConnectionFailure(false)
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build()

            fun create(): DevLibsAPI {

                return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                    .create(DevLibsAPI::class.java)
            }
        }
    }

}