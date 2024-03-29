package com.lambdaschool.devlibs.conductor

import android.content.Context
import android.content.Intent
import android.os.Bundle
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
import com.lambdaschool.devlibs.model.LoginSuccess
import com.lambdaschool.devlibs.ui.MainActivity
import com.lambdaschool.devlibs.viewmodel.LiveDataVMFactory
import com.lambdaschool.devlibs.viewmodel.LoginActivityViewModel
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





class SplashController() : ViewModelController() {
    /*  lateinit var viewModel:SharedConductorViewModel*/
    lateinit var viewModel: LoginActivityViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.splash_controller_layout, container, false)
        val viewModelFactory = LiveDataVMFactory(this.activity!!.application)
        viewModel = activity.run {
            viewModelProvider(viewModelFactory).get(LoginActivityViewModel::class.java)
        }


        //TESTING PLEASE DELETE OR COMMMENT OUT
      /* val fakecreds = LoginSuccess(token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWJqZWN0IjoxMywidXNlcm5hbWUiOiJ0aGlyZHRpb" +
                "WUiLCJpYXQiOjE1NzE3NzQ2MjgsImV4cCI6MTU3MTg2MTAyOH0.-Kpa9U_NxTWb-rTdlI" +
                "UCRMYMUWHHkTPkr3sOvy-d13E",

                userId = 1,
                username = "thirdtime")*/
        val prefs = Prefs(view!!.context)
        val loginCredentials = prefs.getLoginCredentials()


        view.splash_img_view.setOnClickListener {
            onAuthDecision(view.context, false)
        }



        fun tryLoginToken() {
            if (loginCredentials != null) {
                if (loginCredentials.username != "" && loginCredentials.token != "") {
                    // TODO 1: get creds, get madlibs via retro and intent over to main activity
                    //via on onAuthDecision(context,true)
                    viewModel.tryTokenLogin(loginCredentials.token).observe(this, Observer {
                        when (it) {
                            CallBackState.RESPONSE_SUCCESS -> {
                                val intent = Intent(view.context, MainActivity::class.java)
                                startActivity(intent)
                            }
                            CallBackState.RESPONSE_FAIL -> {
                                onAuthDecision(view.context, false)
                                Toast.makeText(view.context, "Failed to login with token (response fail)", Toast.LENGTH_SHORT)
                                        .show()
                            }
                            else -> {
                                onAuthDecision(view.context, false)
                                Toast.makeText(view.context, "Failed to login with token", Toast.LENGTH_SHORT)
                                        .show()
                            }
                        }
                    })
            }
        } else {
            onAuthDecision(view.context, false)
            Toast.makeText(view.context, "Welcome back!", Toast.LENGTH_SHORT)
                    .show()
        }
    }
/*
   fun fakeLoginToken() {
        viewModel.tryTokenLogin(fakecreds.token).observe(this, Observer {
            if (it == CallBackState.RESPONSE_SUCCESS) {
                val intent = Intent(view.context, MainActivity::class.java)
                startActivity(intent)
            } else if (it == CallBackState.RESPONSE_FAIL) {
                onAuthDecision(view.context, false)
                Toast.makeText(view.context, "Failed to login with toke (response fail)", Toast.LENGTH_SHORT)
                        .show()
            } else {
                onAuthDecision(view.context, false)
                Toast.makeText(view.context, "Failed to login with token", Toast.LENGTH_SHORT)
                        .show()
            }
        })
        fakeLoginToken()
        // or tryLoginToken()
    }*/



tryLoginToken()


    return view
}


fun onAuthDecision(context: Context, boolean: Boolean) {
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