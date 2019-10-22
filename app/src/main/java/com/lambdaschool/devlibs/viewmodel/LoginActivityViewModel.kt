package com.lambdaschool.devlibs.viewmodel


import android.app.Application
import androidx.lifecycle.*
import com.lambdaschool.devlibs.database.Database
import com.lambdaschool.devlibs.database.DatabaseRepo
import com.lambdaschool.devlibs.model.CallBackState
import com.lambdaschool.devlibs.model.LoginSuccess
import com.lambdaschool.devlibs.model.RegistrationLoginSendAPI
import java.util.*


class LoginActivityViewModel(application: Application) : AndroidViewModel(application) {
    val repo =DatabaseRepo(application)
    val selected = MutableLiveData<String>()

   var loginLiveData =MutableLiveData<CallBackState>()

    fun select(string:String) {
        selected.value = string
    }

    private val liveData = MutableLiveData<String>()
    init {
        liveData.value = Date().toString()
    }

    fun getLiveData(): LiveData<String> {
        return selected
    }

    fun tryLogin(username:String,password:String) {
       val user = repo.loginUser(RegistrationLoginSendAPI(username,password))
        loginLiveData = user as MutableLiveData<CallBackState>
    }


}
object LiveDataVMFactory : ViewModelProvider.Factory {

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
                addViewModel(key, LoginActivityViewModel())
                return getViewModel(key) as T
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class")
        /*     return SharedViewModel() as T

    }*/
    }
}