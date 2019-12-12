package com.art4muslim.orders.remote.request

/**
 * Created by µðšţãƒâ ™ on 10/12/2019.
 *  ->
 */

data class LoginRequest(
    val mobile: String,
    val password: String,
    val access_key: String,
    val access_password: String
)