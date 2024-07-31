package com.example.mvcsecurityexample.security.filter

import com.example.mvcsecurityexample.token.ExampleToken
import org.springframework.security.authentication.AbstractAuthenticationToken

class ExampleAuthenticationToken(

    val token: ExampleToken

): AbstractAuthenticationToken(null) {

    override fun getCredentials(): Any {
        throw UnsupportedOperationException()
    }

    override fun getPrincipal(): Any {
        throw UnsupportedOperationException()
    }
}
