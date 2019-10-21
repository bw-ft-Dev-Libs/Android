package com.lambdaschool.devlibs.database

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.lambdaschool.devlibs.DatabaseRepoInterface
import com.lambdaschool.devlibs.Prefs
import com.lambdaschool.devlibs.model.*
import com.lambdaschool.devlibs.prefs
import com.lambdaschool.devlibs.retrofit.DevLibsAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DatabaseRepo(contxt: Context) : DatabaseRepoInterface {

    private val TAG_REGISTRATION = "REGISTRATION"
    private val TAG_LOGIN = "LOGIN"

    var retrofitInstance = DevLibsAPI.Factory.create()
    val context = contxt.applicationContext

    override fun registerUser(registrationLoginInfo: RegistrationLoginSendAPI): LiveData<CallBackState> {
        val registrationSuccessful = MutableLiveData<CallBackState>()

        retrofitInstance.registerUser(registrationLoginInfo)
            .enqueue(object : Callback<RegistrationReturnedAPI> {

                override fun onFailure(call: Call<RegistrationReturnedAPI>, t: Throwable) {
                    registrationSuccessful.value = CallBackState.ONFAIL
                    Log.i(TAG_REGISTRATION, "no response from backend...", t)
                }

                override fun onResponse(
                    call: Call<RegistrationReturnedAPI>,
                    response: Response<RegistrationReturnedAPI>
                ) {
                    val body = response.body()
                    when (body) {
                        is RegistrationSuccess -> {
                            registrationSuccessful.value = CallBackState.RESPONSE_SUCCESS
                            prefs.createLoginCredentialEntry(
                                LoginSuccess(
                                    Prefs.INVALID_INT,
                                    body.username,
                                    Prefs.INVALID_STRING))
                        }
                        is RegistrationFail -> {
                            registrationSuccessful.value = CallBackState.RESPONSE_FAIL
                            Log.i(TAG_LOGIN, body.message)
                        }
                    }
                }
            })
        return registrationSuccessful
    }

    override fun loginUser(registrationLoginInfo: RegistrationLoginSendAPI): LiveData<CallBackState> {
        val loginSuccessful = MutableLiveData<CallBackState>()

        retrofitInstance.loginUser(registrationLoginInfo)
            .enqueue(object : Callback<LoginReturnedAPI> {
                override fun onFailure(call: Call<LoginReturnedAPI>, t: Throwable) {
                    loginSuccessful.value = CallBackState.ONFAIL
                    Log.i(TAG_LOGIN, "no response from backend...", t)
                }

                override fun onResponse(
                    call: Call<LoginReturnedAPI>,
                    response: Response<LoginReturnedAPI>
                ) {
                    val body = response.body()
                    when (body) {
                        is LoginSuccess -> {
                            prefs.createLoginCredentialEntry(
                                LoginSuccess(
                                    body.userId,
                                    body.username,
                                    body.token
                                )
                            )
                            loginSuccessful.value = CallBackState.RESPONSE_SUCCESS
                        }
                        is LoginFail -> {
                            loginSuccessful.value = CallBackState.RESPONSE_FAIL
                            Log.i(TAG_LOGIN, body.message)
                            }
                    }
                }
            })
        return loginSuccessful
    }

    private val database by lazy {
        Room.databaseBuilder(
            context,
            Database::class.java,
            "dev_lib_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    companion object {

        // AsyncTasks

    }
}