package com.example.mvcsecurityexample.security.filter

import org.springframework.security.authentication.AbstractAuthenticationToken

class ExampleAuthenticationToken(

    private val token: ExampleToken

): AbstractAuthenticationToken(null) {

    override fun getCredentials(): Any {
        throw UnsupportedOperationException()
    }

    override fun getPrincipal(): Any {
        throw UnsupportedOperationException()
    }
}
