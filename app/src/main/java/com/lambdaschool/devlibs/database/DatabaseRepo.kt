package com.lambdaschool.devlibs.database

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.google.gson.Gson
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

/*    suspend fun  altcreateDevLib (devLibCreate: DevLibCreate,
                                authToken: String):Boolean {
        retrofitInstance.createDevLib(devLibCreate,authToken).enqueue(object :Call<DevLibCreate>, Callback<DevLibBackend> {
            override fun onFailure(call: Call<DevLibBackend>, t: Throwable) {
                Log.i("failure", "onfailure retrofit altcreate devlib")
            }

            override fun onResponse(call: Call<DevLibBackend>, response: Response<DevLibBackend>) {
                return true
            }

        })
        return false
    }*/

    override fun registerUser(registrationLoginInfo: RegistrationLoginSendAPI)
            : LiveData<CallBackState> {
        val registrationSuccessful = MutableLiveData<CallBackState>()

        retrofitInstance.registerUser(registrationLoginInfo)
            .enqueue(object : Callback<RegistrationSuccess> {

                override fun onFailure(call: Call<RegistrationSuccess>, t: Throwable) {
                    registrationSuccessful.value = CallBackState.ON_FAILURE
                    Log.i(TAG_REGISTRATION, "no response from backend...", t)
                }

                override fun onResponse(
                    call: Call<RegistrationSuccess>,
                    response: Response<RegistrationSuccess>
                ) {
                    val gsonBuilder = Gson()

                    if (response.body() is RegistrationSuccess) {
                        val body = response.body() as RegistrationSuccess
                        registrationSuccessful.value = CallBackState.ON_RESPONSE_SUCCESS
                        prefs.createLoginCredentialEntry(
                            LoginSuccess(
                                Prefs.INVALID_INT,
                                body.username,
                                Prefs.INVALID_STRING
                            )
                        )
                    } else {
                        if (response.body() != null) {
                            val body = gsonBuilder.fromJson(
                                response.body().toString(),
                                RegistrationFail::class.java
                            )
                            Log.i(TAG_REGISTRATION, body.message)
                        }
                        registrationSuccessful.value = CallBackState.ON_RESPONSE_FAIL
                    }
                }
            })
        return registrationSuccessful
    }

    override fun loginUser(registrationLoginInfo: RegistrationLoginSendAPI)
            : MutableLiveData<CallBackState> {
        val loginSuccessful = MutableLiveData<CallBackState>()

        retrofitInstance.loginUser(registrationLoginInfo)
            .enqueue(object : Callback<LoginSuccess> {
                override fun onFailure(call: Call<LoginSuccess>, t: Throwable) {
                    loginSuccessful.value = CallBackState.ON_FAILURE
                    Log.i(TAG_LOGIN, "no response from backend...", t)
                }

                override fun onResponse(
                    call: Call<LoginSuccess>,
                    response: Response<LoginSuccess>
                ) {
                    val gsonBuilder = Gson()

                    if (response.body() is LoginSuccess) {
                        val body = response.body() as LoginSuccess
                        loginSuccessful.value = CallBackState.ON_RESPONSE_SUCCESS
                        prefs.createLoginCredentialEntry(
                            LoginSuccess(
                                body.userId,
                                body.username,
                                body.token
                            )
                        )
                        getDevLibs(body.token)
                    } else {
                        if (response.body() != null) {
                            val body = gsonBuilder.fromJson(
                                response.body().toString(),
                                LoginFail::class.java
                            )
                            Log.i(TAG_LOGIN, body.message)
                        }
                        loginSuccessful.value = CallBackState.ON_RESPONSE_FAIL
                    }
                }
            })
        return loginSuccessful
    }

    override fun createDevLib(
        devLibCreate: DevLibCreate,
        authToken: String
    ): LiveData<CallBackState> {
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

                    createSuccessful.value = CallBackState.ON_FAILURE
                    Log.i(TAG_CREATE, "no response from backend...", t)
                }

                override fun onResponse(
                    call: Call<DevLibBackend>,
                    response: Response<DevLibBackend>
                ) {try {
                    val body = response.body()
                    createDevLibBackend(body as DevLibBackend)
                    createSuccessful.value = CallBackState.ON_RESPONSE_SUCCESS
                }catch (e:Error){
                    Log.i("error logging","error on response")
                }


                }
            })
        return createSuccessful
    }

    override fun updateDevLib(
        devLibUpdate: DevLibBackend,
        authToken: String
    ): LiveData<CallBackState> {
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

                    updateSuccessful.value = CallBackState.ON_FAILURE
                    Log.i(TAG_UPDATE, "no response from backend...", t)
                }

                override fun onResponse(
                    call: Call<DevLibBackend>,
                    response: Response<DevLibBackend>
                ) {
                    val body = response.body() as DevLibBackend
                    updateDevLibBackend(body)
                    updateSuccessful.value = CallBackState.ON_RESPONSE_SUCCESS
                }
            })
        return updateSuccessful
    }

    override fun deleteDevLib(
        devLibDelete: DevLibDelete,
        authToken: String
    ): LiveData<CallBackState> {
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

                    deleteSuccessful.value = CallBackState.ON_FAILURE
                    Log.i(TAG_DELETE, "no response from backend...", t)
                }

                override fun onResponse(
                    call: Call<DevLibBackend>,
                    response: Response<DevLibBackend>
                ) {
                    val body = response.body() as DevLibBackend
                    deleteDevLibBackend(body)
                    deleteSuccessful.value = CallBackState.ON_RESPONSE_SUCCESS
                }
            })
        return deleteSuccessful
    }

    override fun getDevLibs(authToken: String): MutableLiveData<CallBackState> {
        val getSuccessful = MutableLiveData<CallBackState>()

        retrofitInstance.getDevLibs(authToken)
            .enqueue(object : Callback<DevLibListDataObject> {

                override fun onFailure(call: Call<DevLibListDataObject>, t: Throwable) {
                    // nothing needs to happen as all our views will be using data from our
                    //  database with Observers set on them.
                    getSuccessful.value = CallBackState.ON_FAILURE
                    Log.i(TAG_GET, "no response from backend...", t)
                }

                override fun onResponse(
                    call: Call<DevLibListDataObject>,
                    response: Response<DevLibListDataObject>
                ) {

                    val body = response.body()?.data as List<DevLibBackend>

                    GetDevLibListBackendAsyncTask(
                        database.databaseDao(), object: ReturnGetDevLibList {

                            override fun returnGetDevLibList(list: List<DevLibBackend>?) {
                            list?.forEach {
                                deleteDevLibBackend(it)
                                Log.i(TAG_DELETE, "Deleted Dev Lib ${it.id}")
                            }

                            body.forEach {
                                createDevLibBackend(it)
                                Log.i(TAG_CREATE, "Created Dev Lib ${it.id}")
                            }
                        }

                    }).execute()

                    getSuccessful.value = CallBackState.ON_RESPONSE_SUCCESS
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
        class CreateDevLibBackendAsyncTask(private val dbDao: DatabaseDAO) :
            AsyncTask<DevLibBackend, Unit, Unit>() {

            override fun doInBackground(vararg devLibBackend: DevLibBackend?) {
                devLibBackend[0]?.let {
                    dbDao.createDevLibBackend(it)
                }
            }
        }

        class GetDevLibListBackendAsyncTask(
            private val dbDao: DatabaseDAO,
            private val returnList: ReturnGetDevLibList
        ) :
            AsyncTask<Unit, Unit, List<DevLibBackend>>() {

            override fun doInBackground(vararg p0: Unit?): List<DevLibBackend>? {
                return dbDao.getAllDevLibsBackendList()
            }

            override fun onPostExecute(result: List<DevLibBackend>?) {
                super.onPostExecute(result)
                returnList.returnGetDevLibList(result)
            }
        }

        interface ReturnGetDevLibList {
            fun returnGetDevLibList(list: List<DevLibBackend>?)
        }

        class UpdateDevLibBackendAsyncTask(private val dbDao: DatabaseDAO) :
            AsyncTask<DevLibBackend, Unit, Unit>() {

            override fun doInBackground(vararg devLibBackend: DevLibBackend?) {
                devLibBackend[0]?.let {
                    dbDao.updateDevLibBackend(it)
                }
            }
        }

        class DeleteDevLibBackendAsyncTask(private val dbDao: DatabaseDAO) :
            AsyncTask<DevLibBackend, Unit, Unit>() {

            override fun doInBackground(vararg devLibBackend: DevLibBackend?) {
                devLibBackend[0]?.let {
                    dbDao.deleteDevLibBackend(it)
                }
            }
        }

        // Table: dev_lib_local
        class CreateDevLibLocalAsyncTask(private val dbDao: DatabaseDAO) :
            AsyncTask<DevLibLocal, Unit, Unit>() {

            override fun doInBackground(vararg devLibLocal: DevLibLocal?) {
                devLibLocal[0]?.let {
                    dbDao.createDevLibLocal(it)
                }
            }
        }

        class UpdateDevLibLocalAsyncTask(private val dbDao: DatabaseDAO) :
            AsyncTask<DevLibLocal, Unit, Unit>() {

            override fun doInBackground(vararg devLibLocal: DevLibLocal?) {
                devLibLocal[0]?.let {
                    dbDao.updateDevLibLocal(it)
                }
            }
        }

        class DeleteDevLibLocalAsyncTask(private val dbDao: DatabaseDAO) :
            AsyncTask<DevLibLocal, Unit, Unit>() {

            override fun doInBackground(vararg devLibLocal: DevLibLocal?) {
                devLibLocal[0]?.let {
                    dbDao.deleteDevLibLocal(it)
                }
            }
        }

    }
}