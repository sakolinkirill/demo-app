package com.issoft.meetup.app1.config

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.security.web.server.context.ServerSecurityContextRepository
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono


@Component
class SecurityContextRepository : ServerSecurityContextRepository {

    override fun save(exchange: ServerWebExchange?, context: SecurityContext?): Mono<Void> {
        TODO("not implemented")
    }

    override fun load(exchange: ServerWebExchange): Mono<SecurityContext> {
        val token = exchange.request.headers.get("Authorization")
        println(token)
        //AnonymousAuthenticationToken
        val authentication = UsernamePasswordAuthenticationToken("qqq", "www", emptyList())
        return Mono.just(SecurityContextImpl(authentication))
    }

}