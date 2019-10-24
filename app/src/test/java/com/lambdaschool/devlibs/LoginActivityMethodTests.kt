package com.lambdaschool.devlibs

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.lambdaschool.devlibs.conductor.SplashController
import com.lambdaschool.devlibs.model.LoginSuccess
import org.junit.Assert.assertTrue
import org.junit.Test

class LoginActivityMethodTests {

    @Test
    fun testTryTokenLogin() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val username ="asdfg"
        val password = "asdfg"
        val loginController =SplashController()
        val login = LoginSuccess(57,"awesd","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWJqZWN0Ijo1NywidXNlcm5hbWUiOiJhd2VzZCIsImlhdCI6MTU3MTkzMTIyNSwiZXhwIjoxNTcyMDE3NjI1fQ.i17cg_LSR7Z3kNIYaJlKzfnx1ymE5kWaEroscZzqfMc")
        assertTrue(loginController.tryLoginToken(login,getApplicationContext()))


    }




}