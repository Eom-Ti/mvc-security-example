package com.example.mvcsecurityexample.security

import com.example.mvcsecurityexample.security.filter.ExampleUserNamePasswordFilter
import com.example.mvcsecurityexample.security.filter.ExampleUserNamePasswordProvider
import org.apache.tomcat.util.net.openssl.ciphers.Authentication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy

import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
) {

    @Bean
    fun filerChain(http: HttpSecurity): SecurityFilterChain {
        http {
            securityMatcher("/login")
            csrf { disable() }
            httpBasic { disable() }
            formLogin { disable() }
            sessionManagement { sessionCreationPolicy = SessionCreationPolicy.STATELESS }
            addFilterAt<UsernamePasswordAuthenticationFilter>(ExampleUserNamePasswordFilter(ProviderManager(exampleUserNamePasswordProvider())))
        }
        return http.build()
    }

    @Bean
    fun exampleUserNamePasswordProvider(): AuthenticationProvider {
        return ExampleUserNamePasswordProvider()
    }
}
