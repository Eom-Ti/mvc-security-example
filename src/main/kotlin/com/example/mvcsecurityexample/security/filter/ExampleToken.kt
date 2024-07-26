package com.example.mvcsecurityexample.security.filter

import java.util.Date

class ExampleToken(
    private val accessToken: String,
    private val expiredDate: Date
) {
}
