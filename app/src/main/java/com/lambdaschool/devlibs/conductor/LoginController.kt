package com.lambdaschool.devlibs.conductor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lambdaschool.devlibs.AUTH_STRING_KEY
import work.beltran.conductorviewmodel.ViewModelController
import android.app.ProgressDialog
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.lifecycle.ViewModelProviders
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.lambdaschool.devlibs.R
import com.lambdaschool.devlibs.database.DatabaseRepo
import com.lambdaschool.devlibs.retrofit.DevLibsAPI
import com.lambdaschool.devlibs.viewmodel.LiveDataVMFactory
import com.lambdaschool.devlibs.viewmodel.LoginActivityViewModel
import kotlinx.android.synthetic.main.login_controller_layout.view.*

/*
*
* login controller should:
* 1: ask for info
* 2: display a loading indicator showLoading()
* 3: indicate to user errors when logging in after hideLoading()
* 4: and allow the user to move on to the registration
*
*
*
*
* */
class LoginController(bundle: Bundle?) : ViewModelController(bundle) {

    val viewGroup: Group by lazy {
        view!!.findViewById<Group>(R.id.login_group)
    }
    lateinit var mProgressDialog:ProgressBar
    lateinit var viewModel:LoginActivityViewModel


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
        val view = inflater.inflate(R.layout.login_controller_layout, container, false)
        mProgressDialog=view!!.findViewById<ProgressBar>(R.id.login_progressbar)
        view.login_btn_signin.setOnClickListener { showLoading() }
        mProgressDialog.setOnClickListener { hideLoading() }
            val tvfoot=view.findViewById<TextView>(R.id.login_tv_footer)

      /*  viewModel =activity?.run {
            viewModelProvider(LiveDataVMFactory).get(SharedConductorViewModel::class.java)
        } ?: throw Exception("Invalid Activity")*/
        val viewModelFactory = LiveDataVMFactory(this.activity!!.application)
        viewModel =activity.run {
            viewModelProvider(viewModelFactory).get(LoginActivityViewModel::class.java)
        }




        tvfoot.setOnClickListener {
            router.pushController(RouterTransaction.with(RegistrationController())
                    .pushChangeHandler(HorizontalChangeHandler())
                    .popChangeHandler(HorizontalChangeHandler()))

        }
        return view
    }

}