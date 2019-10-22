package com.lambdaschool.devlibs.ui.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val mText: MutableLiveData<String>

    val text: LiveData<String>
        get() = mText

    init {
        mText = MutableLiveData()
        mText.value = "This is dashboard fragment"
    }
}