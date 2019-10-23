package com.lambdaschool.devlibs.ui.ui.create

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lambdaschool.devlibs.viewmodel.LoginActivityViewModel
import java.util.HashMap

class CreateViewModel : ViewModel() {

    private val mText: MutableLiveData<String>

    val createString: LiveData<String>
        get() = mText

    init {
        mText = MutableLiveData()
        mText.value = "This is notifications fragment"
    }
    fun settext(string: String) {
        mText.value=string
    }
}

object CreateVMFactory : ViewModelProvider.Factory {

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
        if (modelClass.isAssignableFrom(CreateViewModel::class.java)) {
            val key = "SharedViewModel"
            if (hashMapViewModel.containsKey(key)) {
                return getViewModel(key) as T
            } else {
                addViewModel(key, CreateViewModel())
                return getViewModel(key) as T
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}