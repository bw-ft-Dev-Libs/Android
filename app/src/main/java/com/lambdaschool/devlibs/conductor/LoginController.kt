package com.lambdaschool.devlibs.conductor

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.Group
import androidx.lifecycle.Observer
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.lambdaschool.devlibs.AUTH_STRING_KEY
import com.lambdaschool.devlibs.Prefs
import com.lambdaschool.devlibs.R
import com.lambdaschool.devlibs.model.CallBackState
import com.lambdaschool.devlibs.model.LoginSuccess
import com.lambdaschool.devlibs.ui.MainActivity
import com.lambdaschool.devlibs.viewmodel.LiveDataVMFactory
import com.lambdaschool.devlibs.viewmodel.LoginActivityViewModel
import com.lambdaschool.devlibs.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.login_controller_layout.view.*
import work.beltran.conductorviewmodel.ViewModelController

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
    lateinit var mProgressDialog: ProgressBar
    lateinit var viewModel: LoginActivityViewModel


    fun showLoading() {
        viewGroup.visibility = View.INVISIBLE
        mProgressDialog.visibility = View.VISIBLE
    }

    fun hideLoading() {
        viewGroup.visibility = View.VISIBLE
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
        //delete this TODO:


        mProgressDialog = view!!.findViewById<ProgressBar>(R.id.login_progressbar)
        view.login_btn_signin.setOnClickListener { showLoading() }
        mProgressDialog.setOnClickListener { hideLoading() }


        val btn = view.login_btn_signin
        val editTextUserName = view.login_et_username
        val editTextPassword = view.login_et_password


        val tvfoot = view.findViewById<TextView>(R.id.login_tv_footer)


        val viewModelFactory = LiveDataVMFactory(this.activity!!.application)
        viewModel = activity.run {
            viewModelProvider(viewModelFactory).get(LoginActivityViewModel::class.java)
        }


        //get preferences and try to login,
        val prefs = Prefs(view!!.context)
        val loginCredentials = prefs.getLoginCredentials()

        if (loginCredentials != null) {
            if (loginCredentials.username != "") {
                editTextUserName.hint = loginCredentials.username
            }
        }
            btn.setOnClickListener {

                // get username and password from edittexts and try to login
                val logUserName = view.login_et_username.text.toString()
                val logPassword = view.login_et_password.text.toString()
                if (logUserName.isNotEmpty() && logPassword.isNotEmpty()) {

                    viewModel.tryLogin(logUserName, logPassword).observe(this, Observer {

                        if (it == CallBackState.RESPONSE_SUCCESS) {
                            val intent = Intent(view.context, MainActivity::class.java)
                            startActivity(intent)
                            Toast.makeText(view.context, "Login Success", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            Toast.makeText(view.context, "Failed", Toast.LENGTH_SHORT)
                                    .show()
                        }
                    })
                }
            }

        tvfoot.setOnClickListener {
            router.pushController(RouterTransaction.with(RegistrationController())
                    .pushChangeHandler(HorizontalChangeHandler())
                    .popChangeHandler(HorizontalChangeHandler()))

        }
        return view
    }

}     /*val loginObserver = Observer<CallBackState> { state ->
            if (state == null){
                        Toast.makeText(view.context,"null",Toast.LENGTH_SHORT).show()
                        Log.i("Jackdebug","state=null")
                    }
            if(state == CallBackState.ONFAIL) {
                Toast.makeText(view.context,"fail",Toast.LENGTH_SHORT).show()
                Log.i("Jackdebug","state=fail")
            }
            else if(state==CallBackState.RESPONSE_SUCCESS){
                Toast.makeText(view.context,"success",Toast.LENGTH_SHORT).show()
                Log.i("Jackdebug","state=success")
            }


        }*/