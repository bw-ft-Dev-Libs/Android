package com.lambdaschool.devlibs.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lambdaschool.devlibs.model.DevLibBackend
import com.lambdaschool.devlibs.model.DevLibLocal

@Dao
interface DatabaseDAO {

    // Dev Libs that are sync'd with the backend server
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createDevLibBackend(devLibBackend: DevLibBackend)

    @Query("SELECT * FROM dev_lib_backend")
    fun getAllDevLibsBackend(): LiveData<List<DevLibBackend>>

    @Query("SELECT * FROM dev_lib_backend")
    fun getAllDevLibsBackendList(): List<DevLibBackend>

    @Query("SELECT * FROM dev_lib_backend WHERE categoryId = :categoryId")
    fun getDevLibsBackendForCategory(categoryId: Int): LiveData<List<DevLibBackend>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateDevLibBackend(devLibBackend: DevLibBackend)

    @Delete
    fun deleteDevLibBackend(devLibBackend: DevLibBackend)

    // Dev Libs that are stored locally on the device awaiting for sync
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createDevLibLocal(devLibLocal: DevLibLocal)

    @Query("SELECT * FROM dev_lib_local")
    fun getAllDevLibsLocal(): LiveData<List<DevLibLocal>>

    @Query("SELECT * FROM dev_lib_local WHERE categoryId = :categoryId")
    fun getDevLibsLocalForCategory(categoryId: Int): LiveData<List<DevLibLocal>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateDevLibLocal(devLibLocal: DevLibLocal)

    @Delete
    fun deleteDevLibLocal(devLibBackend: DevLibLocal)
}