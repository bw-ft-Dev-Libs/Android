package com.lambdaschool.devlibs

import android.app.Application
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.lambdaschool.devlibs.viewmodel.LoginActivityViewModel
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class LoginActivityViewModelTests {
    @Mock
    private lateinit var mockContext: Context
    private lateinit var mockApplication: Application
    val instrumentationContext = InstrumentationRegistry.getInstrumentation().context
    val context = ApplicationProvider.getApplicationContext<Context>()
    val FAKE_STRING = "blah blah blah"
    @Test
    fun testLoginUser() {
        `when`(mockContext.getString(R.string.app_name))
                .thenReturn(FAKE_STRING)
        val myObjectUnderTest = LoginActivityViewModel(mockApplication)


        val username ="asdfg"
        val password = "asdfg"
        val token = ""
        val vm:LoginActivityViewModel = LoginActivityViewModel(ApplicationProvider.getApplicationContext())



        vm.loginUser(username, password)

        }



    @Test

    fun testGetDevLibs() {

        val username ="asdfg"
        val password = "asdfg"
        val token = ""
        val vm:LoginActivityViewModel = LoginActivityViewModel(ApplicationProvider.getApplicationContext())

        vm.getDevLibs(token)
    }
    @Test
    fun testregisterUser() {

        val username ="asdfg"
        val password = "asdfg"
        val token = ""
        val vm:LoginActivityViewModel = LoginActivityViewModel(ApplicationProvider.getApplicationContext())

       // vm.reg(token)

    }

}