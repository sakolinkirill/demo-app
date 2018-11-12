package com.issoft.meetup.app2.controller

import com.issoft.meetup.app2.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisOperations
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration
import java.util.*
import java.util.Random
import java.util.stream.Stream


@RestController
class TestController
@Autowired constructor(val factory: ReactiveRedisConnectionFactory,
                       val userOps: ReactiveRedisOperations<String, User>) {

    @GetMapping("/add")
    fun add(@RequestParam("name") name: String): Mono<Boolean> {
        return factory.reactiveConnection.serverCommands().flushAll().then(
                Mono.just(name).map { value -> User(UUID.randomUUID().toString(), value) }
                        .flatMap { user -> userOps.opsForValue().set(user.id!!, user) })
    }

    @GetMapping("/read")
    fun read(): Flux<User> {
        return userOps.keys("*").flatMap { key -> userOps.opsForValue().get(key) }
    }

    @GetMapping("/test", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun test(): Flux<Int> {
        val r = Random(123)
        return Flux.fromStream(Stream.generate { r.nextInt(50) })
                .delayElements(Duration.ofSeconds(1))
    }

}