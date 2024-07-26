package com.example.mvcsecurityexample.security.filter

import java.util.Date

class ExampleToken(
    val accessToken: String,
    val expiredDate: Date
) {
}
