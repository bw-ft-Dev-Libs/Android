package com.lambdaschool.devlibs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.lambdaschool.devlibs.conductor.SplashController

/*
*
*  onCreate should
*  1. begin trying to get auth token from sharedprefs/keystore
*  2. immediately open the SplashController within loginChangeFrameLayout
*  3. if auth token is retrieved, begin running checkCreds Method
*
*
* */


class MainActivity : AppCompatActivity() {
    private lateinit var router: Router
    private val container: ViewGroup by lazy {
        this.findViewById<ViewGroup>(R.id.loginChangeFrameLayout)
    }
    private lateinit var authString: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getAuthStringFromFS()


        // start conductor with splash screen
        router = Conductor.attachRouter(this, container, savedInstanceState)
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(
                    SplashController()
            ))
            router.setPopsLastView(true)
        }









    }

    private fun getAuthStringFromFS() {


    }




}
