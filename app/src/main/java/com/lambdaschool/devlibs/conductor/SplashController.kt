package com.lambdaschool.devlibs.conductor

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.lambdaschool.devlibs.Prefs
import com.lambdaschool.devlibs.R
import com.lambdaschool.devlibs.model.CallBackState
import com.lambdaschool.devlibs.openSoftKeyboard
import com.lambdaschool.devlibs.showToast
import com.lambdaschool.devlibs.ui.MainActivity
import com.lambdaschool.devlibs.viewmodel.LiveDataVMFactory
import com.lambdaschool.devlibs.viewmodel.LoginActivityViewModel
import kotlinx.android.synthetic.main.splash_controller_layout.view.*
import work.beltran.conductorviewmodel.ViewModelController


/**
 * splash controller should:
 * 1: display animation  logo
 * 2: wait for activity to call onAuthDecision
 * 3: intent over to next activity on a positive result
 *                       or
 *   router to login controller on a negative
 * */
class SplashController : ViewModelController() {

    private lateinit var viewModel: LoginActivityViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {

        val view = inflater.inflate(R.layout.splash_controller_layout, container, false)

        val viewModelFactory = LiveDataVMFactory(this.activity!!.application)
        viewModel = activity.run {
            viewModelProvider(viewModelFactory).get(LoginActivityViewModel::class.java)
        }

        val prefs = Prefs(view!!.context)
        val loginCredentials = prefs.getLoginCredentials()

        view.splash_img_view.setOnClickListener {
            onAuthDecision(view.context, false)
        }

        fun tryLoginToken() {
            if (loginCredentials != null) {

                if (loginCredentials.userId != Prefs.INVALID_INT &&
                    loginCredentials.token != Prefs.INVALID_STRING
                ) {
                    viewModel.getDevLibs(loginCredentials.token).observe(this, Observer {
                        when (it) {
                            CallBackState.RESPONSE_SUCCESS -> {
                                onAuthDecision(view.context, true)
                                (view.context).showToast("Welcome!")
                            }

                            /*CallBackState.RESPONSE_FAIL -> {
                                onAuthDecision(view.context, false)
                                (view.context).showToast("Invalid credentials, please login")
                            }*/

                            else -> {
                                onAuthDecision(view.context, false)
                                (view.context).showToast("Invalid credentials, please login")
                            }
                        }
                    })
                }
            } else {
                onAuthDecision(view.context, false)
            }
        }

        tryLoginToken()

        return view
    }

    private fun onAuthDecision(context: Context, tokenValid: Boolean) {
        if (tokenValid) {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        } else {
            sendToLoginController()
        }
    }

    private fun sendToLoginController() {
        router.pushController((
                RouterTransaction.with(LoginController())
                    .pushChangeHandler(HorizontalChangeHandler())
                    .popChangeHandler(HorizontalChangeHandler())
                ))
    }
}