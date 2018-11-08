package com.issoft.meetup.authapp.controller

import com.issoft.meetup.authapp.config.ApplicationProperties
import com.issoft.meetup.authapp.service.OAuth2SignInService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import reactor.core.publisher.Mono

@RestController
class AuthController @Autowired constructor(private val applicationProperties: ApplicationProperties, private val oAuth2SignInService: OAuth2SignInService) {

    @GetMapping("/test")
    fun test(): Mono<String> {
        return Mono.just("hello" + applicationProperties.name)
    }

    @PostMapping("/token/sso")
    fun loginByJwt(@RequestHeader("X-UserName") userName: String): Mono<OAuth2AccessToken> {
        return oAuth2SignInService.signIn(userName)
    }
}