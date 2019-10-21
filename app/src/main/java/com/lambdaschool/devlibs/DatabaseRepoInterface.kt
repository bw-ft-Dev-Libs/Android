package com.lambdaschool.devlibs

import androidx.lifecycle.LiveData
import com.lambdaschool.devlibs.model.LoginReturnedAPI
import com.lambdaschool.devlibs.model.RegistrationReturnedAPI

interface DatabaseRepoInterface {

    // DevLibsAPI
    fun registerUser(creds: RegistrationReturnedAPI): LiveData<Boolean>

    fun loginUser(creds: LoginReturnedAPI): LiveData<Boolean>

}