package com.lambdaschool.devlibs

import androidx.lifecycle.LiveData
import com.lambdaschool.devlibs.model.CallBackState
import com.lambdaschool.devlibs.model.DevLibBackend
import com.lambdaschool.devlibs.model.DevLibLocal
import com.lambdaschool.devlibs.model.RegistrationLoginSendAPI

interface DatabaseRepoInterface {

    // DevLibsAPI
    fun registerUser(registrationLoginInfo: RegistrationLoginSendAPI): LiveData<CallBackState>
    fun loginUser(registrationLoginInfo: RegistrationLoginSendAPI): LiveData<CallBackState>

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