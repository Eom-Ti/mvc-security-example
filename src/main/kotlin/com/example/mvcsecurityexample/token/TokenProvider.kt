package com.example.mvcsecurityexample.token

interface TokenProvider {

    fun createToken(tokenPayload: TokenPayload): ExampleToken

    fun accessTokenVerify(accessToken: String)

    fun getPayload(accessToken: String): TokenPayload
}
