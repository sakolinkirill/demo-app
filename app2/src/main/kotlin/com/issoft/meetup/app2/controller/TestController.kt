package com.issoft.meetup.app2.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.*
import java.util.stream.IntStream

@RestController
class TestController
@Autowired constructor() {

    @GetMapping("/test1/{id}")
    fun test1(@PathVariable("id") id: String): Mono<String> {
        return Mono.just("app2 " + id + " " + System.currentTimeMillis())
    }

    @GetMapping("/test2")
    fun test2(): Mono<String> {
        val random = Random(123)
        val arr = IntStream.range(0, 100000).map { i -> random.nextInt() }.toArray()
        for (i in arr.indices) {
            for (j in 0 until arr.size - 1) {
                if (arr[j] > arr[j + 1]) {
                    val tmp = arr[j]
                    arr[j] = arr[j + 1]
                    arr[j + 1] = tmp
                }
            }
        }
        return Mono.just("ok")
    }

}