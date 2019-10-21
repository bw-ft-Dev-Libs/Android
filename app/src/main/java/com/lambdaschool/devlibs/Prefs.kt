package com.lambdaschool.devlibs

import android.content.Context
import android.content.SharedPreferences
import com.lambdaschool.devlibs.model.LoginSuccess

class Prefs(context: Context) {

    companion object {
        private const val LOGIN_PREFERENCES = "LoginPrefs"
        private const val LOGIN_USER_ID_KEY = "LoginUserId"
        private const val LOGIN_USER_NAME_KEY = "LoginUserName"
        private const val LOGIN_TOKEN_KEY = "LoginToken"
        private const val INVALID_STRING = "INVALID"
        private const val INVALID_INT = -1
    }

    private val sharedPrefs: SharedPreferences =
        context.getSharedPreferences(LOGIN_PREFERENCES, Context.MODE_PRIVATE)

    fun createLoginCredentialEntry(entry: LoginSuccess) {
        val editor = sharedPrefs.edit()
        editor.putInt(LOGIN_USER_ID_KEY, entry.userId)
        editor.putString(LOGIN_USER_NAME_KEY, entry.username)
        editor.putString(LOGIN_TOKEN_KEY, entry.token)
        editor.apply()
    }

    /**
     * This method is for obtaining shared preferences for user login credentials
     * such that a simple null check can be made and then roll right into logging in.
     * */
    fun getLoginCredentials(): LoginSuccess? {

        val userId = sharedPrefs.getInt(LOGIN_USER_ID_KEY, INVALID_INT)

        var username = INVALID_STRING
        sharedPrefs.getString(LOGIN_USER_NAME_KEY, INVALID_STRING)?.let {
            username = it
        }

        var token = INVALID_STRING
        sharedPrefs.getString(LOGIN_TOKEN_KEY, INVALID_STRING)?.let {
            token = it
        }

        return if (userId != INVALID_INT && username != INVALID_STRING && token != INVALID_STRING) {
            LoginSuccess(userId, username, token)
        } else {
            null
        }
    }

    /**
     * Simply overwrites credentials with invalid data
     * */
    fun deleteLoginCredentials() {
        val editor = sharedPrefs.edit()
        editor.putInt(LOGIN_USER_ID_KEY, INVALID_INT)
        editor.putString(LOGIN_USER_NAME_KEY, INVALID_STRING)
        editor.putString(LOGIN_TOKEN_KEY, INVALID_STRING)
        editor.apply()
    }

}