package com.example.mvcsecurityexample.token

import io.jsonwebtoken.security.Keys
import org.springframework.boot.context.properties.ConfigurationProperties
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.*
import javax.crypto.SecretKey

@ConfigurationProperties("jwt")
class TokenProperties(
    private val secretKey: String,
    private val accessTokenExpiredMinute: Long
) {

    fun getSecretKey(): SecretKey {
        return Keys.hmacShaKeyFor(secretKey.toByteArray())
    }

    fun getAccessTokenExpiredDate(): Date {
        return getTokenExpiredDate(accessTokenExpiredMinute)

    }

    private fun getTokenExpiredDate(accessTokenExpiredMinute: Long): Date {
        val instant = ZonedDateTime.now(ZoneOffset.UTC)
            .plusMinutes(accessTokenExpiredMinute)
            .toInstant()

        return Date.from(instant)
    }
}
