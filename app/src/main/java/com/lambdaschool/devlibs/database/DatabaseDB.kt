package com.lambdaschool.devlibs.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lambdaschool.devlibs.model.DevLibBackend
import com.lambdaschool.devlibs.model.DevLibLocal

@Database(
    entities = [DevLibBackend::class, DevLibLocal::class],
    version = 1, exportSchema = false
)

abstract class Database : RoomDatabase() {
    abstract fun databaseDao(): DatabaseDAO
}