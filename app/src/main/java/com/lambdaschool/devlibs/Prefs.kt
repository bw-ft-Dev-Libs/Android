package com.lambdaschool.devlibs

import android.content.Context
import android.content.SharedPreferences
import com.lambdaschool.devlibs.model.LoginReturnedAPI

class Prefs(context: Context) {

    companion object {
        private const val LOGIN_PREFERENCES = "LoginPrefs"
        private const val LOGIN_TOKEN_KEY = "LoginToken"
        private const val LOGIN_USER_ID_KEY = "LoginUserId"
        private const val INVALID_STRING = "INVALID"
        private const val INVALID_INT = -1
    }

    private val sharedPrefs: SharedPreferences =
        context.getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE)

    fun createLoginCredentialEntry(entry: LoginReturnedAPI) {
        val editor = sharedPrefs.edit()
        editor.putString(LOGIN_TOKEN_KEY, entry.token)
        editor.putInt(LOGIN_USER_ID_KEY, entry.user_id)
        editor.apply()
    }

    fun getLoginCredentials(): LoginReturnedAPI? {
        var token = INVALID_STRING
        sharedPrefs.getString(LOGIN_TOKEN_KEY, INVALID_STRING)?.let {
            token = it
        }

        val userId = sharedPrefs.getInt(LOGIN_USER_ID_KEY, INVALID_INT)

        return if (token != INVALID_STRING && userId != INVALID_INT) {
            LoginReturnedAPI("", token, userId)
        } else {
            null
        }
    }

    fun deleteLoginCredentials() {
        val editor = sharedPrefs.edit()
        editor.putString(LOGIN_TOKEN_KEY, "")
        editor.putInt(LOGIN_USER_ID_KEY, INVALID_INT)
        editor.apply()
    }

}