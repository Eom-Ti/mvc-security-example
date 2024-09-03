package com.example.mvcsecurityexample.service

import com.example.mvcsecurityexample.domain.User
import org.springframework.stereotype.Service

@Service
class UserAuthenticationService(

) {

    fun findByUserId(userId: String): User {
        return User("t", 1)
    }
}
