package com.example.mvcsecurityexample.security.filter

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler

class ExampleFailHandler(

) : AuthenticationFailureHandler {
    override fun onAuthenticationFailure(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        exception: AuthenticationException?
    ) {
        TODO("Not yet implemented")
    }
}
