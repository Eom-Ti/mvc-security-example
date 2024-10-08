package com.example.mvcsecurityexample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class MvcSecurityExampleApplication

fun main(args: Array<String>) {
    runApplication<MvcSecurityExampleApplication>(*args)
}
