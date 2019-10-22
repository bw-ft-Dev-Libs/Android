package com.lambdaschool.devlibs.viewmodel


import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.*
import com.lambdaschool.devlibs.database.Database
import com.lambdaschool.devlibs.database.DatabaseRepo
import com.lambdaschool.devlibs.model.CallBackState
import com.lambdaschool.devlibs.model.LoginSuccess
import com.lambdaschool.devlibs.model.RegistrationLoginSendAPI
import java.util.*


class LoginActivityViewModel(application: Application) : AndroidViewModel(application) {
    val repo = DatabaseRepo(application)


    fun tryLogin(username: String, password: String): LiveData<CallBackState> {
        return repo.loginUser(RegistrationLoginSendAPI(username=username, password=password))
    }

    fun tryTokenLogin(authToken: String): LiveData<CallBackState> {
        return repo.getDevLibs(authToken)
}
    fun tryToRegister(username: String,password: String): LiveData<CallBackState> {
        return repo.registerUser(RegistrationLoginSendAPI(username, password))
    }

}

class LiveDataVMFactory(val application: Application) : ViewModelProvider.Factory {

    //  private val dataSource = DefaultDataSource(Dispatchers.IO)
    val hashMapViewModel = HashMap<String, ViewModel>()
    fun addViewModel(key: String, viewModel: ViewModel){
        hashMapViewModel.put(key, viewModel)
    }
    fun getViewModel(key: String): ViewModel? {
        return hashMapViewModel[key]
    }
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(LoginActivityViewModel::class.java)) {
            val key = "SharedViewModel"
            if (hashMapViewModel.containsKey(key)) {
                return getViewModel(key) as T
            } else {
                addViewModel(key, LoginActivityViewModel(application))
                return getViewModel(key) as T
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}