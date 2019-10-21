package com.lambdaschool.devlibs.conductor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lambdaschool.devlibs.R
import work.beltran.conductorviewmodel.ViewModelController

class RegistrationController () : ViewModelController()  {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.registration_controller_layout, container, false)
        return view
    }

}