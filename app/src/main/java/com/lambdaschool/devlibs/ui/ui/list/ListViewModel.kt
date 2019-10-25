package com.lambdaschool.devlibs.ui.ui.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lambdaschool.devlibs.Prefs
import com.lambdaschool.devlibs.database.DatabaseRepo
import com.lambdaschool.devlibs.model.DevLibLocal
import com.lambdaschool.devlibs.model.LoginSuccess

class ListViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = DatabaseRepo(application)
    private val mText: MutableLiveData<String>
    private val prefs = Prefs(application.applicationContext)
    private val user = prefs.getLoginCredentials() as LoginSuccess
    private val mlist =MutableLiveData<List<DevLibLocal>>()
            val list:LiveData<List<DevLibLocal>>
                get() = mlist

    val text: LiveData<String>
        get() = mText

    init {
        mText = MutableLiveData()
        mText.value = "This is dashboard fragment"
        mlist.value = getDevLibs().value
    }


    fun getDevLibs():LiveData<List<DevLibLocal>>{
        return repo.getAllDevLibsLocal()
    }



}