package com.lambdaschool.devlibs.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.lambdaschool.devlibs.R
import com.lambdaschool.devlibs.conductor.SplashController
import com.lambdaschool.devlibs.model.LoginSuccess
import com.lambdaschool.devlibs.prefs
import java.lang.Thread.sleep

/*
*
*  Login activity should
*  1. begin trying to get auth token from sharedprefs/keystore
*  2. immediately open the SplashController within loginChangeFrameLayout
   3. chill and let controllers do the work
* */


class LoginActivity : AppCompatActivity() {
     val login: LoginSuccess? =prefs.getLoginCredentials()

    private lateinit var router: Router
    val controller:SplashController = SplashController()
    private val container: ViewGroup by lazy {
        this.findViewById<ViewGroup>(R.id.loginChangeFrameLayout)
    }
    private lateinit var authString: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivty_login)




        // start conductor with splash screen
        router = Conductor.attachRouter(this, container, savedInstanceState)
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(
                    controller
            ))
            router.setPopsLastView(true)
        }








    }

 /*   private fun checkAuthStringFromFS() {
       // if user has local stored data, try to use that, otherwise send to login
        if (login != null){
            if(login.token !="")
            {}

        }
        else if (login?.username == ""){

        }
        else {

        }
        // try to get key local
        val key = ""

        // call retrofit and check if auth token is valid
        //TODO(0)
        if (!key.isNullOrEmpty()) {

            getAuthResultFromRetro(key)
        }
        else {

            // if key is empty let the controller know
            // so it can advance to registration/

            // login controller.onAuthDecision(this,false)
        }

    }
    private fun getAuthResultFromRetro(key:String) {

        //call retrofit with key as auth string

        val retrofitCall = null

        if (retrofitCall != null) {
            //if it's good call
            sleep(500)
            controller.onAuthDecision(this,true)

        }
        else {
            //let controller know so it can progress to registration/login controller
            controller.onAuthDecision(this,false)
        }





    }*/

    override fun onBackPressed() {
        if(!router.handleBack()) {
            super.onBackPressed()
        }
    }


}
