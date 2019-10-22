package com.lambdaschool.devlibs.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

class DevLibCreate(
    val lib: String,
    @SerializedName ("user_id") val userId: Int,
    @SerializedName ("category_id") val categoryId: Int
)

@Entity(tableName = "dev_lib_backend")
data class DevLibBackend(

    @PrimaryKey(autoGenerate = false) @NonNull
    var id: Int,

    var lib: String,
    @SerializedName ("user_id") val userId: Int,
    @SerializedName ("category_id") var categoryId: Int
)

/**
 * Store the Dev Lib the user creates locally if connectivity drops.
 *
 * Will be able to populate as DevLibCreate, send to the backend,
 * receive back the DevLibBackend class as a response, store it in
 * DevLibBackend schema, then delete the DevLibLocal entry.
 * */
@Entity(tableName = "dev_lib_local")
class DevLibLocal(

    @PrimaryKey(autoGenerate = true) @NonNull
    val id: Int = 0,

    var lib: String,
    val userId: Int,
    var categoryId: Int
)