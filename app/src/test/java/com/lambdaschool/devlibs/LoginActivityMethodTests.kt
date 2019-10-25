package com.lambdaschool.devlibs

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.platform.app.InstrumentationRegistry
import com.lambdaschool.devlibs.conductor.SplashController
import com.lambdaschool.devlibs.model.LoginSuccess
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


class LoginActivityMethodTests {

        lateinit var instrumentationContext: Context
        lateinit var appcontext:Context
        @Before
        fun setup() {
            instrumentationContext = InstrumentationRegistry.getInstrumentation().context
            appcontext = InstrumentationRegistry.getInstrumentation().targetContext
        }






    @Test
    fun testTryTokenLogin() {
        val context = appcontext
        val username ="asdfg"
        val password = "asdfg"
        val loginController =SplashController()
        val login = LoginSuccess(57,"awesd","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWJqZWN0Ijo1NywidXNlcm5hbWUiOiJhd2VzZCIsImlhdCI6MTU3MTkzMTIyNSwiZXhwIjoxNTcyMDE3NjI1fQ.i17cg_LSR7Z3kNIYaJlKzfnx1ymE5kWaEroscZzqfMc")
        assertTrue(loginController.tryLoginToken(login,context))


    }




}