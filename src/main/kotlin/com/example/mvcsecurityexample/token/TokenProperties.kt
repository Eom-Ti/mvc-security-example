package com.example.mvcsecurityexample.token

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("jwt")
class TokenProperties(
    val secretKey: String,
    val accessTokenExpiredMinute: Long
) {
}
