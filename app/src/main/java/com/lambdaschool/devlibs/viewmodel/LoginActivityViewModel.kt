package com.lambdaschool.devlibs.viewmodel


import android.app.Application
import androidx.lifecycle.*
import com.lambdaschool.devlibs.database.DatabaseRepo
import com.lambdaschool.devlibs.model.CallBackState
import com.lambdaschool.devlibs.model.RegistrationLoginSendAPI
import java.util.*


class LoginActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = DatabaseRepo(application)

    fun loginUser(username: String, password: String): LiveData<CallBackState> {
        return repo.loginUser(RegistrationLoginSendAPI(username, password))
    }

    fun getDevLibs(authToken: String): LiveData<CallBackState> {
        return repo.getDevLibs(authToken)
    }

    fun registerUser(username: String, password: String): LiveData<CallBackState> {
        return repo.registerUser(RegistrationLoginSendAPI(username, password))
    }

}

class LiveDataVMFactory(val application: Application) : ViewModelProvider.Factory {

    private val hashMapViewModel = HashMap<String, ViewModel>()

    private fun addViewModel(key: String, viewModel: ViewModel) {
        hashMapViewModel.put(key, viewModel)
    }

    private fun getViewModel(key: String): ViewModel? {
        return hashMapViewModel[key]
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(LoginActivityViewModel::class.java)) {
            val key = "SharedViewModel"
            return if (hashMapViewModel.containsKey(key)) {
                getViewModel(key) as T
            } else {
                addViewModel(key, LoginActivityViewModel(application))
                getViewModel(key) as T
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}