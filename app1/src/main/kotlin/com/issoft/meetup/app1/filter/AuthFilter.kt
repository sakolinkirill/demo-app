package com.issoft.meetup.app1.filter

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

//@Component
class AuthFilter: WebFilter {

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val token = exchange.request.headers.get("Authorization")
        //println(token)
        val context = SecurityContextHolder.getContext()
        context.authentication  = UsernamePasswordAuthenticationToken("qqq", "www")
        return chain.filter(exchange)
    }
}