package com.issoft.meetup.app2.controller

import com.issoft.meetup.app2.entity.User
import com.issoft.meetup.app2.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.core.publisher.Flux
import org.springframework.web.bind.annotation.GetMapping

@RestController
@RequestMapping("/user")
class UserController
@Autowired constructor(val userRepository: UserRepository) {

    @PostMapping("add")
    fun create(@RequestBody user: User): Mono<User> {
        return userRepository.save(user)
    }

    @GetMapping("get")
    fun get(): Flux<User> {
        return userRepository.findAll()
    }

    @GetMapping("stream", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun stream(): Flux<User> {
        return userRepository.findWithTailableCursorBy()
    }

}