package com.example.mvcsecurityexample.security.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.HttpMediaTypeNotSupportedException


class ExampleUserNamePasswordFilter(
    authenticationManager: AuthenticationManager,
    private val mapper: ObjectMapper = jacksonObjectMapper()

): UsernamePasswordAuthenticationFilter(authenticationManager) {

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        requireNotNull(request)

        if(isNotSupportHttpMethod(request)){
            throw AuthenticationServiceException("Authentication service not support method : ${request.method}")
        }

        if (isNotSupportMediaType(request)) {
            throw HttpMediaTypeNotSupportedException("Media type not support method : ${request.method}")
        }

        val loginData = mapper.readValue<LoginData>(request.inputStream)
        loginData.checkRequiredValue()

        return authenticationManager.authenticate(UsernamePasswordAuthenticationToken(loginData.userId, loginData.password))
    }

    private fun isNotSupportHttpMethod(request: HttpServletRequest): Boolean {
        return !request.method.equals(HttpMethod.POST.name(), ignoreCase = true)
    }

    private fun isNotSupportMediaType(request: HttpServletRequest): Boolean {
        if (request.contentType == null) {
            return false
        }

        return request.contentType.equals(MediaType.APPLICATION_JSON_VALUE, ignoreCase = true)
    }
}
