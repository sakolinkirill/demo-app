package com.issoft.meetup.app1

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [ReactiveSecurityAutoConfiguration ::class])
class App1Application

fun main(args: Array<String>) {
    runApplication<App1Application>(*args)
}
