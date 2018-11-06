package com.issoft.meetup.authapp.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import reactor.core.publisher.Mono

@RestController
class AuthController @Autowired constructor() {

    @GetMapping("/test")
    fun test(): Mono<String> {
        return Mono.just("hello")
    }

}