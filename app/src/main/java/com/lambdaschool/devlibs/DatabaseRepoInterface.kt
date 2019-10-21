package com.lambdaschool.devlibs

import androidx.lifecycle.LiveData
import com.lambdaschool.devlibs.model.CallBackState
import com.lambdaschool.devlibs.model.RegistrationLoginSendAPI

interface DatabaseRepoInterface {

    // DevLibsAPI
    fun registerUser(registrationLoginInfo: RegistrationLoginSendAPI): LiveData<CallBackState>

    fun loginUser(registrationLoginInfo: RegistrationLoginSendAPI): LiveData<CallBackState>

}