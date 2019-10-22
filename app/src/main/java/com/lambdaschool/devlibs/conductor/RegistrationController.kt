package com.lambdaschool.devlibs.conductor


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.constraintlayout.widget.Group
import com.lambdaschool.devlibs.AUTH_STRING_KEY
import com.lambdaschool.devlibs.R
import kotlinx.android.synthetic.main.registration_controller_layout.view.*
import work.beltran.conductorviewmodel.ViewModelController

/*
*
* login controller should:
* 1: ask for new usre info
* 2: display a loading indicator showLoading()
* 3: indicate to user errors with registration after hideLoading()
* 4: finally let user know they're registered and offer a way to click
*
*
*
* */
class RegistrationController (bundle: Bundle?) : ViewModelController(bundle)  {
    val viewGroup: Group by lazy {
        view!!.findViewById<Group>(R.id.registration_group)
    }
   /* lateinit var viewModel:SharedConductorViewModel*/
    lateinit var mProgressDialog: ProgressBar


    fun showLoading() {
        viewGroup.visibility = View.INVISIBLE
        mProgressDialog.visibility = View.VISIBLE
    }

    fun hideLoading() {
        viewGroup.visibility =View.VISIBLE
        mProgressDialog.visibility = View.GONE
        // viewGroup.visibility = View.VISIBLE
    }


    constructor(communicatedString: String? = null) : this(Bundle().apply {
        putString(AUTH_STRING_KEY, communicatedString)

    })


    val communicatedString by lazy {
        args.getString(AUTH_STRING_KEY)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.registration_controller_layout, container, false)
        view.registration_btn_submit.setOnClickListener { showLoading() }
        mProgressDialog=view.findViewById(R.id.registration_progressbar)
        mProgressDialog.setOnClickListener { hideLoading() }
/*        viewModel =activity?.run {
            viewModelProvider(LiveDataVMFactory).get(SharedConductorViewModel::class.java)
        } ?: throw Exception("Invalid Activity")*/
        return view
    }

}