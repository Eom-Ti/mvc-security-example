package com.example.mvcsecurityexample.security.filter

import com.example.mvcsecurityexample.service.UserAuthenticationService
import com.example.mvcsecurityexample.token.TokenProvider
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication

class ExampleUserNamePasswordProvider(

    private val tokenProvider: TokenProvider,
    private val userAuthenticationService: UserAuthenticationService

): AuthenticationProvider {

    private val log = LoggerFactory.getLogger(ExampleUserNamePasswordProvider::class.java)

    override fun authenticate(authentication: Authentication?): Authentication {
        val token = authentication as ExampleAuthenticationToken
        val loginData = authentication.loginData

        log.info("[ExampleUserNamePasswordProvider.authenticate] loginData: $loginData")

        if (userAuthenticationService.isLoginInfoNotMatch(loginData.userId, loginData.password)) {
            throw IllegalArgumentException("[userAuthenticationService.isPasswordNotMatch]")
        }


    }

    override fun supports(authentication: Class<*>?): Boolean {
        return authentication?.isAssignableFrom(ExampleAuthenticationToken::class.java) ?: false
    }


    private fun verifyToken(accessToken: String) {

    }
}
