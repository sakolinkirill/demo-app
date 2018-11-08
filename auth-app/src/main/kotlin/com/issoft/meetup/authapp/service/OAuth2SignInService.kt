package com.issoft.meetup.authapp.service

import org.springframework.security.oauth2.common.OAuth2AccessToken
import reactor.core.publisher.Mono

interface OAuth2SignInService {

    fun signIn(userName: String): Mono<OAuth2AccessToken>

}