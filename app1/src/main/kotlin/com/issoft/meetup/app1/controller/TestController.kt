package com.issoft.meetup.app1.controller

import com.issoft.meetup.app1.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.security.Principal
import reactor.core.publisher.Flux

@RestController
class TestController @Autowired constructor(val webClientBuilder: WebClient.Builder) {

    @RequestMapping("/test1/{id}")
    fun test1(@PathVariable id: String): Mono<String> {
        return Mono.just("app1 " + id + " " + System.currentTimeMillis())
    }

    @RequestMapping("/test2")
    fun test2(principal: Mono<Principal>): Mono<String> {
        return principal.map { t -> String.format("Hello %s", t.name) }
    }

    @RequestMapping("/test3")
    fun test3(): Mono<String> {
        return Mono.just(SecurityContextHolder.getContext().authentication.name)
    }

    @RequestMapping("/test4")
    fun test4(): Flux<User> {
        return webClientBuilder.build().get().uri("http://localhost:8092/user/get").retrieve().bodyToFlux(User::class.java)
    }

    @GetMapping("/test5", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun test5(): Flux<User> {
        return webClientBuilder.build().get().uri("http://localhost:8092/user/stream").retrieve().bodyToFlux(User::class.java)
    }

    @GetMapping("/test6")
    fun test6(): Mono<String> {
        return webClientBuilder.build().get().uri("https://www.tut.by/").retrieve().bodyToMono(String::class.java)
    }
}