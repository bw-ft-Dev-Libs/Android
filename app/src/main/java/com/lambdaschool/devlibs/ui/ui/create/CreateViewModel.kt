package com.lambdaschool.devlibs.ui.ui.create

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lambdaschool.devlibs.tempWordNeeds
import com.lambdaschool.devlibs.viewmodel.LoginActivityViewModel
import java.util.HashMap

class CreateViewModel : ViewModel() {

    companion object {
        var text = "init"
        var vmPosition = 0
        var vmCategory = 0
        var arrayOfNeeded = arrayOf<String>()
        var arrayOfProvided = arrayOf<String>()
        var template = listOf<String>()


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

/*  private val mText: MutableLiveData<String>
    private val mInt: MutableLiveData<Int>
    lateinit var workingMadLib: MutableLiveData<Array<String>>

    val createString: LiveData<String>
        get() = mText
    val createInt: LiveData<Int>
        get() = mInt

    init {
        mText = MutableLiveData()
        mInt= MutableLiveData()
        workingMadLib =MutableLiveData()
            fun settext(string: String) {
        mText.value=string
    }
    fun setInt(int: Int) {
        mInt.value=int
        workingMadLib.value = tempWordNeeds[int]
    }
    }*/