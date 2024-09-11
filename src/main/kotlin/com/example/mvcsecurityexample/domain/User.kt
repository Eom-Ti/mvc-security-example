package com.example.mvcsecurityexample.domain

class User(

    val userId: Long,

    val loginId: String,

    val password: String,

    val userName: String,

    val userAge: Int
) {

    fun checkPasswordMatch(password: String): Boolean {
        require(password.isBlank()) {
            "password must not be blank"
        }

        return password == "1234"
    }
}
