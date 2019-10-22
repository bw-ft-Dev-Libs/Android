package com.lambdaschool.devlibs.database

import android.content.Context
import android.os.AsyncTask
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
    private val TAG_CREATE = "CREATE"
    private val TAG_UPDATE = "UPDATE"
    private val TAG_DELETE = "DELETE"
    private val TAG_GET = "GET"

    private var retrofitInstance = DevLibsAPI.Factory.create()
    private val context = contxt.applicationContext

    lateinit var contents: DevLibBackend

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

    override fun createDevLib(devLibCreate: DevLibCreate, authToken: String): LiveData<CallBackState> {
        val createSuccessful = MutableLiveData<CallBackState>()

        retrofitInstance.createDevLib(devLibCreate, authToken)
            .enqueue(object : Callback<DevLibBackend> {

                override fun onFailure(call: Call<DevLibBackend>, t: Throwable) {

                    // Create the Dev Lib locally so that the user can still use the application
                    /*createDevLibLocal(DevLibLocal(
                        devLibCreate.lib,
                        devLibCreate.userId,
                        devLibCreate.categoryId
                        // TODO: enum class key CREATE
                    ))*/

                    createSuccessful.value = CallBackState.ONFAIL
                    Log.i(TAG_CREATE, "no response from backend...", t)
                }

                override fun onResponse(
                    call: Call<DevLibBackend>,
                    response: Response<DevLibBackend>
                ) {
                    val body = response.body() as DevLibBackend
                    createDevLibBackend(body)
                    createSuccessful.value = CallBackState.RESPONSE_SUCCESS
                }
            })
        return createSuccessful
    }

    override fun updateDevLib(devLibUpdate: DevLibBackend, authToken: String): LiveData<CallBackState> {
        val updateSuccessful = MutableLiveData<CallBackState>()

        retrofitInstance.updateDevLib(devLibUpdate, authToken)
            .enqueue(object : Callback<DevLibBackend> {

                override fun onFailure(call: Call<DevLibBackend>, t: Throwable) {

                    // Create the Dev Lib locally so that the user can still use the application
                    /*createDevLibLocal(DevLibUpdate (
                        devLibCreate.lib,
                        devLibCreate.userId,
                        devLibCreate.categoryId
                        // TODO: enum class key UPDATE
                    ))*/

                    updateSuccessful.value = CallBackState.ONFAIL
                    Log.i(TAG_CREATE, "no response from backend...", t)
                }

                override fun onResponse(
                    call: Call<DevLibBackend>,
                    response: Response<DevLibBackend>
                ) {
                    val body = response.body() as DevLibBackend
                    updateDevLibBackend(body)
                    updateSuccessful.value = CallBackState.RESPONSE_SUCCESS
                }
            })
        return updateSuccessful
    }

    override fun deleteDevLib(devLibDelete: DevLibDelete, authToken: String): LiveData<CallBackState> {
        val deleteSuccessful = MutableLiveData<CallBackState>()

        retrofitInstance.deleteDevLib(devLibDelete, authToken)
            .enqueue(object : Callback<DevLibBackend> {

                override fun onFailure(call: Call<DevLibBackend>, t: Throwable) {

                    // Create the Dev Lib locally so that the user can still use the application
                    /*createDevLibLocal(DevLibUpdate (
                        devLibCreate.lib,
                        devLibCreate.userId,
                        devLibCreate.categoryId
                        // TODO: enum class key DELETE
                    ))*/

                    deleteSuccessful.value = CallBackState.ONFAIL
                    Log.i(TAG_CREATE, "no response from backend...", t)
                }

                override fun onResponse(
                    call: Call<DevLibBackend>,
                    response: Response<DevLibBackend>
                ) {
                    val body = response.body() as DevLibBackend
                    deleteDevLibBackend(body)
                    deleteSuccessful.value = CallBackState.RESPONSE_SUCCESS
                }
            })
        return deleteSuccessful
    }

    override fun getDevLibs(authToken: String): LiveData<CallBackState> {
        val getSuccessful = MutableLiveData<CallBackState>()

        retrofitInstance.getDevLibs(authToken)
            .enqueue(object : Callback<List<DevLibBackend>> {

                override fun onFailure(call: Call<List<DevLibBackend>>, t: Throwable) {
                    // nothing needs to happen as all our views will be using data from our
                    //  database with Observers set on them.
                    getSuccessful.value = CallBackState.ONFAIL
                    Log.i(TAG_CREATE, "no response from backend...", t)
                }

                override fun onResponse(
                    call: Call<List<DevLibBackend>>,
                    response: Response<List<DevLibBackend>>
                ) {
                    val body = response.body()
                    // TODO: check agains dev_lib_backend schema && update appropriately
                    getSuccessful.value = CallBackState.RESPONSE_SUCCESS
                }
            })
        return getSuccessful
    }

    // Table: dev_lib_backend
    override fun createDevLibBackend(devLibBackend: DevLibBackend) {
        CreateDevLibBackendAsyncTask(database.databaseDao()).execute(devLibBackend)
    }

    override fun getAllDevLibsBackend(): LiveData<List<DevLibBackend>> {
        return database.databaseDao().getAllDevLibsBackend()
    }

    override fun getDevLibsBackendForCategory(categoryId: Int): LiveData<List<DevLibBackend>> {
        return database.databaseDao().getDevLibsBackendForCategory(categoryId)
    }

    override fun updateDevLibBackend(devLibBackend: DevLibBackend) {
        UpdateDevLibBackendAsyncTask(database.databaseDao()).execute(devLibBackend)
    }

    override fun deleteDevLibBackend(devLibBackend: DevLibBackend) {
        DeleteDevLibBackendAsyncTask(database.databaseDao()).execute(devLibBackend)
    }


    // Table: dev_lib_local
    override fun createDevLibLocal(devLibLocal: DevLibLocal) {
        CreateDevLibLocalAsyncTask(database.databaseDao()).execute(devLibLocal)
    }

    override fun getAllDevLibsLocal(): LiveData<List<DevLibLocal>> {
        return database.databaseDao().getAllDevLibsLocal()
    }

    override fun getDevLibsLocalForCategory(categoryId: Int): LiveData<List<DevLibLocal>> {
        return database.databaseDao().getDevLibsLocalForCategory(categoryId)
    }

    override fun updateDevLibLocal(devLibLocal: DevLibLocal) {
        UpdateDevLibLocalAsyncTask(database.databaseDao()).execute(devLibLocal)
    }

    override fun deleteDevLibLocal(devLibLocal: DevLibLocal) {
        DeleteDevLibLocalAsyncTask(database.databaseDao()).execute(devLibLocal)
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

        // Table: dev_lib_backend
        class CreateDevLibBackendAsyncTask(private val dbDao: DatabaseDAO)
            : AsyncTask<DevLibBackend, Unit, Unit>() {

            override fun doInBackground(vararg devLibBackend: DevLibBackend?) {
                devLibBackend[0]?.let {
                    dbDao.createDevLibBackend(it)
                }
            }
        }

        class UpdateDevLibBackendAsyncTask(private val dbDao: DatabaseDAO)
            : AsyncTask<DevLibBackend, Unit, Unit>() {

            override fun doInBackground(vararg devLibBackend: DevLibBackend?) {
                devLibBackend[0]?.let {
                    dbDao.updateDevLibBackend(it)
                }
            }
        }

        class DeleteDevLibBackendAsyncTask(private val dbDao: DatabaseDAO)
            : AsyncTask<DevLibBackend, Unit, Unit>() {

            override fun doInBackground(vararg devLibBackend: DevLibBackend?) {
                devLibBackend[0]?.let {
                    dbDao.deleteDevLibBackend(it)
                }
            }
        }

        // Table: dev_lib_local
        class CreateDevLibLocalAsyncTask(private val dbDao: DatabaseDAO)
            : AsyncTask<DevLibLocal, Unit, Unit>() {

            override fun doInBackground(vararg devLibLocal: DevLibLocal?) {
                devLibLocal[0]?.let {
                    dbDao.createDevLibLocal(it)
                }
            }
        }

        class UpdateDevLibLocalAsyncTask(private val dbDao: DatabaseDAO)
            : AsyncTask<DevLibLocal, Unit, Unit>() {

            override fun doInBackground(vararg devLibLocal: DevLibLocal?) {
                devLibLocal[0]?.let {
                    dbDao.updateDevLibLocal(it)
                }
            }
        }

        class DeleteDevLibLocalAsyncTask(private val dbDao: DatabaseDAO)
            : AsyncTask<DevLibLocal, Unit, Unit>() {

            override fun doInBackground(vararg devLibLocal: DevLibLocal?) {
                devLibLocal[0]?.let {
                    dbDao.deleteDevLibLocal(it)
                }
            }
        }

    }
}