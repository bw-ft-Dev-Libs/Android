package com.lambdaschool.devlibs.ui

import android.content.Intent
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

/**
 *  Login activity should
 *  1. Begin trying to get auth token from sharedprefs/keystore
 *  2. Immediately open the SplashController within loginChangeFrameLayout
 *  3. chill and let controllers do the work
 * */
class LoginActivity : AppCompatActivity() {

    private lateinit var router: Router
    private val controller: SplashController = SplashController()

    private val container: ViewGroup by lazy {
        this.findViewById<ViewGroup>(R.id.loginChangeFrameLayout)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivty_login)

        // Start conductor with splash screen
        router = Conductor.attachRouter(this, container, savedInstanceState)
        if (!router.hasRootController()) {
            router.setRoot(
                RouterTransaction.with(
                    controller
                )
            )
            router.setPopsLastView(true)
        }
    }

    override fun onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed()
        } else if (router.backstackSize < 3) {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
    }


}
