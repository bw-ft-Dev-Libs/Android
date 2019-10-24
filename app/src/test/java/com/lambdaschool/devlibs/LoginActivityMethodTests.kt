package com.lambdaschool.devlibs

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.lambdaschool.devlibs.conductor.LoginController
import org.junit.Test

class LoginActivityMethodTests {
    val context = ApplicationProvider.getApplicationContext<Context>()
    @Test
    fun testTryLogin() {

        val username ="asdfg"
        val password = "asdfg"
        val loginController =LoginController()

        loginController.trylogin(username,password,context)


    }




}