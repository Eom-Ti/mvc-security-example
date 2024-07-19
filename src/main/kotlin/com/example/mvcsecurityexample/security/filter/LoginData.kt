package com.example.mvcsecurityexample.security.filter

class LoginData(
    val userId: String,
    val password: String
) {
    fun checkRequiredValue() {
        require(userId.isNotEmpty()) { "userId must not be empty" }
        require(password.isNotEmpty()) { "password must not be empty" }
    }
}
