package com.lambdaschool.devlibs.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.lambdaschool.devlibs.DatabaseRepoInterface
import com.lambdaschool.devlibs.model.LoginSendAPI
import com.lambdaschool.devlibs.model.LoginReturnedAPI
import com.lambdaschool.devlibs.model.RegistrationReturnedAPI
import com.lambdaschool.devlibs.model.RegistrationSendAPI
import com.lambdaschool.devlibs.prefs
import com.lambdaschool.devlibs.retrofit.DevLibsAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DatabaseRepo(contxt: Context) : DatabaseRepoInterface {

    override fun registerUser(creds: RegistrationReturnedAPI): LiveData<Boolean> {
        val registrationSuccessful = MutableLiveData<Boolean>()

        retrofitInstance.registerUser(creds)
            .enqueue(object : Callback<RegistrationReturnedAPI> {

                override fun onFailure(call: Call<RegistrationReturnedAPI>, t: Throwable) {
                    registrationSuccessful.value = false
                }

                override fun onResponse(
                    call: Call<RegistrationReturnedAPI>,
                    response: Response<RegistrationReturnedAPI>
                ) {
                    registrationSuccessful.value = true
                }
            })
        return registrationSuccessful
    }

    override fun loginUser(creds: LoginReturnedAPI): LiveData<Boolean> {
        val loginSuccessful = MutableLiveData<Boolean>()

        retrofitInstance.loginUser(creds).enqueue(object : Callback<LoginReturnedAPI> {
            override fun onFailure(call: Call<LoginReturnedAPI>, t: Throwable) {
                loginSuccessful.value = false
            }

            override fun onResponse(
                call: Call<LoginReturnedAPI>,
                response: Response<LoginReturnedAPI>
            ) {
                var token = ""
                response.body()?.token?.let {
                    token = it
                }

                var userId = -1
                response.body()?.user_id?.let {
                    userId = it
                }

                prefs.createLoginCredentialEntry(LoginReturnedAPI("", token, userId))

                loginSuccessful.value = true
            }

        })
        return loginSuccessful
    }

    var retrofitInstance = DevLibsAPI.Factory.create()
    val context = contxt.applicationContext

    private val database by lazy {
        Room.databaseBuilder(
            context,
            Database::class.java,
            "chore_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    companion object {

    }
}