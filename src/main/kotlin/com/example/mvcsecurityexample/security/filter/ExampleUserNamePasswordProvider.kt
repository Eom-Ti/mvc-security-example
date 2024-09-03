package com.example.mvcsecurityexample.security.filter

import com.example.mvcsecurityexample.service.UserAuthenticationService
import com.example.mvcsecurityexample.token.TokenProvider
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication

class ExampleUserNamePasswordProvider(

    private val tokenProvider: TokenProvider,
    private val userAuthenticationService: UserAuthenticationService

): AuthenticationProvider {

    override fun authenticate(authentication: Authentication?): Authentication {
        val token = authentication as ExampleAuthenticationToken
        val accessToken = token.token.accessToken

        verifyToken(accessToken)

    }

    override fun supports(authentication: Class<*>?): Boolean {
        return authentication?.isAssignableFrom(ExampleAuthenticationToken::class.java) ?: false
    }


    private fun verifyToken(accessToken: String) {

    }
}
