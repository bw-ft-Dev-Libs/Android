package com.lambdaschool.devlibs.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [],
    version = 1, exportSchema = false
)

abstract class Database : RoomDatabase() {
    abstract fun databaseDao(): DatabaseDAO
}