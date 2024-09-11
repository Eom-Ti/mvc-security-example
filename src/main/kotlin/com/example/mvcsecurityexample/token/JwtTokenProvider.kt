package com.example.mvcsecurityexample.token

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.*

class JwtTokenProvider(
    private val jwtProperties: TokenProperties

) : TokenProvider {

    private val mapper = jacksonObjectMapper()

    override fun createToken(tokenPayload: TokenPayload): ExampleToken {
        val payload = mapper.convertValue(tokenPayload, object: TypeReference<Map<String, String>>() {})
        val accessTokenExpiredDate = jwtProperties.getAccessTokenExpiredDate();

        val accessToken = Jwts.builder()
            .setIssuer(TokenConst.ISSUER)
            .setIssuedAt(Date.from(ZonedDateTime.now(ZoneOffset.UTC).toInstant()))
            .setExpiration(jwtProperties.getAccessTokenExpiredDate())
            .setHeader(createHeader())
            .signWith(jwtProperties.getSecretKey(), SignatureAlgorithm.HS512)
            .addClaims(payload)
            .compact();

        return ExampleToken(accessToken, accessTokenExpiredDate)
    }

    override fun accessTokenVerify(accessToken: String) {
        Jwts.parserBuilder()
            .setSigningKey(jwtProperties.getSecretKey())
            .build()
            .parseClaimsJws(accessToken)
    }

    override fun getPayload(accessToken: String): TokenPayload {
        val claims = Jwts.parserBuilder()
            .setSigningKey(jwtProperties.getSecretKey())
            .build()
            .parseClaimsJws(accessToken)
            .body

        val payload = claims.entries.associate { it.key to it.value }
        return mapper.convertValue(payload, TokenPayload::class.java)
    }

    private fun createHeader(): Map<String, String> {
        return mapOf("typ" to TokenConst.TOKEN_TYPE)
    }
}

private object TokenConst {
    const val ISSUER = "owen"
    const val TOKEN_TYPE = "JWT"
}
