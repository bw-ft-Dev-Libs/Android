package com.lambdaschool.devlibs.model

/**
 * Registration & Login data models.
 *
 * The POST call for both registration or login requires:
 * @param username
 * @param password
 * */
class RegistrationLoginSendAPI(
    val username: String,
    val password: String
)

/**
 * The POST call for registration returns either:
 *   Success:
 *   @param username: String
 *
 * -OR-
 *
 *   Failure:
 *   @param message: String
 * */
data class RegistrationSuccess(
    val username: String
)

data class RegistrationFail(
    val message: String
)


/**
 * The POST call for login returns either:
 *   Success:
 *   @param userId: Int
 *   @param username: String
 *   @param token: String
 *
 *   -OR-
 *
 *   Failure:
 *   @param message: String
 * */
data class LoginSuccess(
    val userId: Int,
    val username: String,
    val token: String
)

data class LoginFail(
    val message: String
)

/**
 * 3 states exist with the callback; onFailure, onResponse LoginSuccess (or RegistrationSuccess), &&
 * onResponse LoginFail (or RegistrationSuccess)...
 *
 * Because of this we cannot use booleans if returning live data, so we must use an enum
 * */
enum class CallBackState {
    ON_FAILURE, ON_RESPONSE_FAIL, ON_RESPONSE_SUCCESS
}