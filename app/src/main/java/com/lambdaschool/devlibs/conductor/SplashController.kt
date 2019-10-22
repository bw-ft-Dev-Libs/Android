package com.lambdaschool.devlibs.conductor

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.lambdaschool.devlibs.AUTH_STRING_KEY
import com.lambdaschool.devlibs.R
import com.lambdaschool.devlibs.ui.MainActivity
import kotlinx.android.synthetic.main.splash_controller_layout.view.*
import work.beltran.conductorviewmodel.ViewModelController


/*
*
* splash controller should:
* 1: display animation  logo
* 2: wait for activity to call onAuthDecision
* 3: intent over to next activity on a positive result
*                       or
*   router to login controller on a negative
*
*
*
* */





class SplashController (bundle: Bundle) : ViewModelController(bundle)  {
    lateinit var viewModel:SharedConductorViewModel


    constructor(communicatedString: String? = null) : this(Bundle().apply {
        putString(AUTH_STRING_KEY, communicatedString)
    })

    val communicatedString by lazy {
        args.getString(AUTH_STRING_KEY)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.splash_controller_layout, container, false)
        view.splash_img_view.setOnClickListener {
            onAuthDecision(view.context,false)
        }
        viewModel =activity?.run {
            viewModelProvider(LiveDataVMFactory).get(SharedConductorViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        return view
    }




    fun onAuthDecision(context: Context, boolean:Boolean) {
            // if bool is true, redirect to activity
        if (boolean) {
            val intent = Intent(context, MainActivity::class.java).apply {
             //whatever extras   putExtra(, message)
            }
            startActivity(intent)
        }
        // if false, advance to login controller
        else {
            router.pushController(RouterTransaction.with(LoginController())
                    .pushChangeHandler(HorizontalChangeHandler())
                    .popChangeHandler(HorizontalChangeHandler()))

        }
    }
}