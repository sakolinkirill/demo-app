package com.issoft.meetup.app1.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient
import org.springframework.web.reactive.socket.client.WebSocketClient


@Configuration
class WebSocketConfig {

    @Bean
    fun webSocketClient(): WebSocketClient {
        return ReactorNettyWebSocketClient()
    }
}