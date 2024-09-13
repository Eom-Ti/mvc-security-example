package com.example.mvcsecurityexample.security.filter

import com.example.mvcsecurityexample.service.UserAuthenticationService
import com.example.mvcsecurityexample.token.TokenPayload
import com.example.mvcsecurityexample.token.TokenProvider
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.core.Authentication

class ExampleUserNamePasswordProvider(

    private val tokenProvider: TokenProvider,
    private val userAuthenticationService: UserAuthenticationService

): AuthenticationProvider {

    private val log = LoggerFactory.getLogger(ExampleUserNamePasswordProvider::class.java)

    override fun authenticate(authentication: Authentication?): Authentication {
        try {
            val token = authentication as ExampleAuthenticationToken
            val loginData = token.loginData
            val user = userAuthenticationService.getUser(loginData.userId)

            log.info("[ExampleUserNamePasswordProvider.authenticate] loginData: ${loginData.userId}")

            if (user.checkPasswordMatch(loginData.password)) {
                throw IllegalArgumentException("[userAuthenticationService.isPasswordNotMatch]")
            }

            val createToken = tokenProvider.createToken(TokenPayload(user.userId, user.userName))
            return ExampleJwtAuthenticationToken(createToken)
        } catch (e: Throwable) {
            throw AuthenticationServiceException("Authentication failed", e)
        }
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return authentication?.isAssignableFrom(ExampleAuthenticationToken::class.java) ?: false
    }
}
