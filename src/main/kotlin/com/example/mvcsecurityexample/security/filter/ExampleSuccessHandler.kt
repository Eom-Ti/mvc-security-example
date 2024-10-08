package com.example.mvcsecurityexample.security.filter

import com.example.mvcsecurityexample.controller.response.BaseResponse
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

class ExampleSuccessHandler(

): AuthenticationSuccessHandler {

    private val mapper = jacksonObjectMapper()

    override fun onAuthenticationSuccess(request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication?) {
        val exampleToken = authentication as ExampleJwtAuthenticationToken

        response.status = HttpStatus.OK.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.outputStream.write(
            mapper.writeValueAsBytes(BaseResponse.ok(exampleToken.token))
        )
    }
}
