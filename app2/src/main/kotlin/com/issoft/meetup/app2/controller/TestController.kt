package com.issoft.meetup.app2.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class TestController @Autowired constructor() {

    @RequestMapping("/test1/{id}")
    fun test1(@PathVariable id: String): Mono<String> {
        return Mono.just("app2 " + id + " "+ System.currentTimeMillis())
    }

}