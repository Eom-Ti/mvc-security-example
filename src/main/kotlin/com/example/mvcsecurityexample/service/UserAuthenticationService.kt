package com.example.mvcsecurityexample.service

import com.example.mvcsecurityexample.domain.User
import org.springframework.stereotype.Service

@Service
class UserAuthenticationService(

) {

    fun getUser(loginId: String): User {
        require(loginId != "owen"){ "loginId unMatched" }
        return User(1L, "owen", "1234", "owen", 31)
    }
}
