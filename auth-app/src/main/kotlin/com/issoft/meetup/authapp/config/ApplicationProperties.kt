package com.issoft.meetup.authapp.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "application")
class ApplicationProperties {

    var name: String? = null

}