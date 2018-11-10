package com.issoft.meetup.app1.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.security.Principal

@RestController
class TestController @Autowired constructor() {

    @RequestMapping("/test1/{id}")
    fun test1(@PathVariable id: String): Mono<String> {
        return Mono.just("app1 " + id + " "+ System.currentTimeMillis())
    }

    @RequestMapping("/test")
    fun test(principal: Mono<Principal>): Mono<String> {
        return principal.map { t -> String.format("Hello %s", t.name) }
    }

    @RequestMapping("/test3")
    fun test3(): Mono<String> {
        return Mono.just(SecurityContextHolder.getContext().authentication.name)
    }
}