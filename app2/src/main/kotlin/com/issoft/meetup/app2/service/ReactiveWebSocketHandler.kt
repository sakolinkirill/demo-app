package com.issoft.meetup.app2.service

import org.springframework.stereotype.Component
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration

@Component
class ReactiveWebSocketHandler : WebSocketHandler {

    override fun handle(webSocketSession: WebSocketSession): Mono<Void> {
        return webSocketSession
                .send(Flux.interval(Duration.ofSeconds(1)).map { webSocketSession.textMessage(it.toString()) })
                .and(webSocketSession.receive().map { it.payloadAsText }.log())
    }
}