package com.lambdaschool.devlibs.conductor


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.constraintlayout.widget.Group
import androidx.lifecycle.Observer
import com.lambdaschool.devlibs.R
import com.lambdaschool.devlibs.model.CallBackState
import com.lambdaschool.devlibs.showToast
import com.lambdaschool.devlibs.viewmodel.LiveDataVMFactory
import com.lambdaschool.devlibs.viewmodel.LoginActivityViewModel
import kotlinx.android.synthetic.main.registration_controller_layout.view.*
import work.beltran.conductorviewmodel.ViewModelController

/**
 *
 * login controller should:
 * 1: ask for new usre info
 * 2: display a loading indicator showLoading()
 * 3: indicate to user errors with registration after hideLoading()
 * 4: finally let user know they're registered and offer a way to click
 * */
class RegistrationController : ViewModelController() {

    private val viewGroup: Group by lazy {
        view!!.findViewById<Group>(R.id.registration_group)
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val viewModelFactory = LiveDataVMFactory(this.activity!!.application)
        viewModel = activity.run {
            viewModelProvider(viewModelFactory).get(LoginActivityViewModel::class.java)
        }

        val view = inflater.inflate(R.layout.registration_controller_layout, container, false)
        val btn = view.registration_btn_submit
        mProgressDialog = view.findViewById(R.id.registration_progressbar)

        btn.setOnClickListener {

            // get username and password from edittexts and try to login
            val logUserName = view.registration_et_name.text.toString()
            val logPassword = view.registration_et_password.text.toString()
            if (logUserName.isNotEmpty() && logPassword.isNotEmpty()) {
                showLoading()
                viewModel.registerUser(logUserName, logPassword).observe(this, Observer {

                    when (it) {
                        // response is successful so notify user and return to login screen
                        CallBackState.RESPONSE_SUCCESS -> {
                            hideLoading()
                            router.popController(this)
                            (view.context).showToast("Registration Successful, please log in")
                        }
                        // response failed due to user already existing
                        CallBackState.RESPONSE_FAIL -> {
                            hideLoading()
                            (view.context).showToast("User already exists, please try again")

                        }
                        // site unreachable or other error
                        else -> {
                            hideLoading()
                            (view.context).showToast("Failed, please try again")
                        }
                    }
                })
            }
        }
        return view
    }
}


