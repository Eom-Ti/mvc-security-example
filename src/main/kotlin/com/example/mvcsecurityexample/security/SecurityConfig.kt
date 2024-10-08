package com.example.mvcsecurityexample.security

import com.example.mvcsecurityexample.security.filter.ExampleFailHandler
import com.example.mvcsecurityexample.security.filter.ExampleSuccessHandler
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
            addFilterAt<UsernamePasswordAuthenticationFilter>(exampleUserNamePasswordFilter())
        }
        return http.build()
    }

    @Bean
    fun exampleUserNamePasswordProvider(): AuthenticationProvider {
        return ExampleUserNamePasswordProvider()
    }

    @Bean
    fun exampleUserNamePasswordFilter(): ExampleUserNamePasswordFilter {
        val exampleUserNamePasswordFilter = ExampleUserNamePasswordFilter()
        exampleUserNamePasswordFilter.setAuthenticationManager(ProviderManager(exampleUserNamePasswordProvider()))
        exampleUserNamePasswordFilter.setAuthenticationSuccessHandler(ExampleSuccessHandler())
        exampleUserNamePasswordFilter.setAuthenticationFailureHandler(ExampleFailHandler())

        return exampleUserNamePasswordFilter
    }
}
