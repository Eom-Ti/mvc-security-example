package com.example.mvcsecurityexample.service

import org.springframework.stereotype.Service

@Service
class UserAuthenticationService(

) {

    fun isLoginInfoNotMatch(password: String): Boolean {
        require(password.isBlank()) {
            "password must not be blank"
        }

        return password == "1234"
    }
}
