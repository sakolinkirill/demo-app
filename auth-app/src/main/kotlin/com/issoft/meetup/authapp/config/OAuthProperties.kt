package com.issoft.meetup.authapp.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource

@Configuration
@ConfigurationProperties(prefix = "application.oauth")
class OAuthProperties {

    var browserCredentials: ClientCredentials? = null
    var jwtPrivateKey: JwtPrivateKey? = null

    class JwtPrivateKey {
        var keystoreResource: Resource? = null
        var keystorePassword: String? = null
        var keyAlias: String? = null
    }

    class ClientCredentials {
        var client: String? = null
        var secret: String? = null
    }
}