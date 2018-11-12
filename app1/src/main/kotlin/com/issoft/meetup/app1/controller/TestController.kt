package com.issoft.meetup.app1.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
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
        return Mono.just("app1 " + id + " "+ System.currentTimeMillis())
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
    fun test4(): Mono<String> {
        /*val accounts = webClientBuilder.build().get().uri("http://localhost:8092/read").retrieve().bodyToFlux(com.issoft.meetup.app2.User::class.java)
        return accounts
                .collectList()
                .map({ a -> Customer(a) })
                .mergeWith(repository.findById(id))
                .collectList()
                .map(Function { CustomerMapper.map() })*/
        return Mono.just("qwe")
    }
}