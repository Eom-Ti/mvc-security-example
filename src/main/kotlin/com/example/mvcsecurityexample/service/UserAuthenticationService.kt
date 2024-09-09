package com.example.mvcsecurityexample.service

import org.springframework.stereotype.Service

@Service
class UserAuthenticationService(

) {

    fun isLoginInfoNotMatch(userId: String, password: String): Boolean {
        require(userId.isBlank() || password.isBlank()) {
            "userId or password must not be blank"
        }

        return !(userId != "owen" || password != "1234")
    }
}
