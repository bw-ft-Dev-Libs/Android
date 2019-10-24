package com.lambdaschool.devlibs.conductor

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.lifecycle.Observer
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.lambdaschool.devlibs.*
import com.lambdaschool.devlibs.model.CallBackState
import com.lambdaschool.devlibs.ui.MainActivity
import com.lambdaschool.devlibs.viewmodel.LiveDataVMFactory
import com.lambdaschool.devlibs.viewmodel.LoginActivityViewModel
import kotlinx.android.synthetic.main.login_controller_layout.view.*
import work.beltran.conductorviewmodel.ViewModelController

/**
* login controller should:
* 1: ask for info
* 2: display a loading indicator showLoading()
* 3: indicate to user errors when logging in after hideLoading()
* 4: and allow the user to move on to the registration
* */
class LoginController : ViewModelController() {

    private val viewGroup: Group by lazy {
        view!!.findViewById<Group>(R.id.login_group)
    }

    private lateinit var mProgressDialog: ProgressBar
    private lateinit var viewModel: LoginActivityViewModel


    private fun showLoading() {
        viewGroup.visibility = View.INVISIBLE
        mProgressDialog.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        viewGroup.visibility = View.VISIBLE
        mProgressDialog.visibility = View.GONE
        // viewGroup.visibility = View.VISIBLE
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.login_controller_layout, container, false)

        mProgressDialog = view!!.findViewById(R.id.login_progressbar)

        val loginButton = view.login_btn_signin
        val editTextUserName = view.login_et_username
        val editTextPassword = view.login_et_password

        val textViewFooter = view.findViewById<TextView>(R.id.login_tv_footer)

        val viewModelFactory = LiveDataVMFactory(this.activity!!.application)
        viewModel = activity.run {
            viewModelProvider(viewModelFactory).get(LoginActivityViewModel::class.java)
        }

        // Get preferences and try to login,
        val prefs = Prefs(view.context)
        val loginCredentials = prefs.getLoginCredentials()

        if (loginCredentials != null) {
            if (loginCredentials.username != Prefs.INVALID_STRING) {
                editTextUserName.setText(loginCredentials.username)
                this.applicationContext?.openSoftKeyboard(editTextPassword)
            }
        } else {
            this.applicationContext?.openSoftKeyboard(editTextUserName)
        }
            loginButton.setOnClickListener {

                // get username and password from edittexts and try to login
                val logUserName = view.login_et_username.text.toString()
                val logPassword = view.login_et_password.text.toString()
                if (logUserName.isNotEmpty() && logPassword.isNotEmpty()) {

                    viewModel.loginUser(logUserName, logPassword).observe(this, Observer {

                        if (it == CallBackState.ON_RESPONSE_SUCCESS) {
                            val intent = Intent(view.context, MainActivity::class.java)
                            startActivity(intent)
                            this.applicationContext?.showToast("Login Success")
                        }
                        else {
                            this.applicationContext?.showToast("Login Failed")
                        }
                    })
                }
            }

        textViewFooter.setOnClickListener {
            router.pushController(RouterTransaction.with(RegistrationController())
                    .pushChangeHandler(HorizontalChangeHandler())
                    .popChangeHandler(HorizontalChangeHandler()))

        }

        return view
    }
}