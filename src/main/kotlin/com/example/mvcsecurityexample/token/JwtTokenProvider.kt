package com.example.mvcsecurityexample.token

import com.fasterxml.jackson.module.kotlin.convertValue
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

class JwtTokenProvider(

) : TokenProvider {

    private val mapper = jacksonObjectMapper()

    override fun createToken(tokenPayload: TokenPayload): ExampleToken {
        val payload = mapper.convertValue(tokenPayload, Map::class.java)
//        final Date accessTokenExpiredDate = jwtProperties.getAccessTokenExpiredDate();

        String accessToken = Jwts.builder()
            .setIssuer(ISSUER)
            .setIssuedAt(Date.from(ZonedDateTime.now(ZoneOffset.UTC).toInstant()))
            .setExpiration(jwtProperties.getAccessTokenExpiredDate())
            .setHeader(createHeader())
            .signWith(jwtProperties.getSecretKey(), SignatureAlgorithm.HS512)
            .addClaims(payload)
            .compact();

        return new Token(accessToken, accessTokenExpiredDate);
    }

    override fun accessTokenVerify(accessToken: String) {
        TODO("Not yet implemented")
    }

    override fun getPayload(accessToken: String): TokenPayload {
        TODO("Not yet implemented")
    }
}
