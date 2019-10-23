package com.lambdaschool.devlibs.retrofit

import com.google.gson.Gson
import com.lambdaschool.devlibs.model.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface DevLibsAPI {

    @POST("auth/register")
    fun registerUser(@Body registrationLoginInfo: RegistrationLoginSendAPI)
            : Call<RegistrationSuccess>

    @POST("auth/login")
    fun loginUser(@Body registrationLoginInfo: RegistrationLoginSendAPI)
            : Call<LoginSuccess>

    @POST("devLib")
    fun createDevLib(@Body devLibCreate: DevLibCreate, @Header("authorization") Value: String)
            : Call<DevLibBackend>

    @PUT("devLib")
    fun updateDevLib(@Body devLibUpdate: DevLibBackend, @Header("authorization") Value: String)
            : Call<DevLibBackend>

    @DELETE("devLib")
    fun deleteDevLib(@Body devLibDelete: DevLibDelete, @Header("authorization") Value: String)
            : Call<DevLibBackend>

    @GET("devLib")
    fun getDevLibs(@Header("authorization") Value: String)
            : Call<DevLibListDataObject>

    class Factory {

        companion object {
            val BASE_URL = "https://dev-libs-test.herokuapp.com/api/"
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