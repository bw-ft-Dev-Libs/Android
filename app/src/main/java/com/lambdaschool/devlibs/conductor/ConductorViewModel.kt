package com.lambdaschool.devlibs.conductor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.util.*

/*

class SharedConductorViewModel : ViewModel() {
    val selected = MutableLiveData<String>()

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
        if (modelClass.isAssignableFrom(SharedConductorViewModel::class.java)) {
            val key = "SharedViewModel"
            if (hashMapViewModel.containsKey(key)) {
                return getViewModel(key) as T
            } else {
                addViewModel(key, SharedConductorViewModel())
                return getViewModel(key) as T
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class")
        */
/*     return SharedViewModel() as T

    }*//*

    }
}*/
