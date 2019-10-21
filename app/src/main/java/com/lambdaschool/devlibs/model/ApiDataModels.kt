package com.lambdaschool.devlibs.model

class RegistrationSendAPI(
    val username: String,
    val password: String
)

data class RegistrationReturnedAPI(
    val message: String,
    val user_id: Int
)

class LoginSendAPI(
    val username: String,
    val password: String
)

data class LoginReturnedAPI(
    val message: String,
    val token: String,
    val user_id: Int
)