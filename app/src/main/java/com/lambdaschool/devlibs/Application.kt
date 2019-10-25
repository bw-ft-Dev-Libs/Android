package com.lambdaschool.devlibs

import android.app.Application
import com.lambdaschool.devlibs.database.DatabaseRepo

val repo by lazy {

    App.repo!!

}

val prefs: Prefs by lazy {

    App.prefs!!

}

class App : Application() {

    companion object {
        var repo: DatabaseRepo? = null
        var prefs: Prefs? = null
    }

    override fun onCreate() {
        super.onCreate()

        repo = DatabaseRepo(applicationContext)
        prefs = Prefs(applicationContext)
    }
}