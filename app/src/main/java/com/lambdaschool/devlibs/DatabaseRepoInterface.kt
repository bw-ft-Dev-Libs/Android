package com.lambdaschool.devlibs

import androidx.lifecycle.LiveData
import com.lambdaschool.devlibs.model.*

interface DatabaseRepoInterface {

    // DevLibsAPI
    fun registerUser(registrationLoginInfo: RegistrationLoginSendAPI): LiveData<CallBackState>
    fun loginUser(registrationLoginInfo: RegistrationLoginSendAPI): LiveData<CallBackState>
    fun createDevLib(devLibCreate: DevLibCreate, authToken: String): LiveData<CallBackState>
    fun updateDevLib(devLibUpdate: DevLibBackend, authToken: String): LiveData<CallBackState>
    fun deleteDevLib(devLibDelete: DevLibDelete, authToken: String): LiveData<CallBackState>
    fun getDevLibs(authToken: String): LiveData<CallBackState>

    // dev_lib_backend database table
    fun createDevLibBackend(devLibBackend: DevLibBackend)
    fun getAllDevLibsBackend(): LiveData<List<DevLibBackend>>
    fun getDevLibsBackendForCategory(categoryId: Int): LiveData<List<DevLibBackend>>
    fun updateDevLibBackend(devLibBackend: DevLibBackend)
    fun deleteDevLibBackend(devLibBackend: DevLibBackend)

    // dev_lib_local database table
    fun createDevLibLocal(devLibLocal: DevLibLocal)
    fun getAllDevLibsLocal(): LiveData<List<DevLibLocal>>
    fun getDevLibsLocalForCategory(categoryId: Int): LiveData<List<DevLibLocal>>
    fun updateDevLibLocal(devLibLocal: DevLibLocal)
    fun deleteDevLibLocal(devLibLocal: DevLibLocal)
}