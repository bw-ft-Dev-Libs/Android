package com.lambdaschool.devlibs.conductor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lambdaschool.devlibs.AUTH_STRING_KEY
import work.beltran.conductorviewmodel.ViewModelController

class SplashController (bundle: Bundle) : ViewModelController(bundle)  {

    constructor(communicatedString: String? = null) : this(Bundle().apply {
        putString(AUTH_STRING_KEY, communicatedString)
    })

    val communicatedString by lazy {
        args.getString(AUTH_STRING_KEY)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}