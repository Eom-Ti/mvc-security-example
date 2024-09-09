package com.example.mvcsecurityexample.service

import com.example.mvcsecurityexample.domain.User

class UserInfoService(

) {
    fun findByUserId(userId: String): User {
        return User("owen", 31)
    }
}
