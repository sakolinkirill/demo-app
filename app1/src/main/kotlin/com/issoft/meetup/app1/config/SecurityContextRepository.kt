package com.issoft.meetup.app1.config

import com.issoft.meetup.app1.helper.AuthenticationHelper
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
        val tokenHeader = exchange.request.headers.getFirst("Authorization")
        return Mono.just(SecurityContextImpl(AuthenticationHelper.authentication(tokenHeader)))
    }

}