package com.issoft.meetup.app2.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.HandlerMapping
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import java.util.HashMap
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.beans.factory.annotation.Autowired



@Configuration
class WebSocketConfiguration {

    @Autowired
    private val webSocketHandler: WebSocketHandler? = null

    @Bean
    fun webSocketHandlerMapping(): HandlerMapping {
        val map = HashMap<String, WebSocketHandler?>()
        map["/event-emitter"] = webSocketHandler

        val handlerMapping = SimpleUrlHandlerMapping()
        handlerMapping.setOrder(1)
        handlerMapping.setUrlMap(map)
        return handlerMapping
    }
}