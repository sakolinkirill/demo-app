package com.issoft.meetup.authapp.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.io.Resource
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory

@Configuration
@ConfigurationProperties(prefix = "application.oauth")
class AuthorizationServerConfig @Autowired constructor(
        private val authenticationManager: AuthenticationManager
) : AuthorizationServerConfigurerAdapter() {

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

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer?) {
        endpoints!!
                .tokenServices(tokenServices())
                .tokenStore(tokenStore())
                .accessTokenConverter(accessTokenConverter())
                .authenticationManager(authenticationManager)
    }

    override fun configure(security: AuthorizationServerSecurityConfigurer?) {
        security!!.allowFormAuthenticationForClients()
    }

    override fun configure(clients: ClientDetailsServiceConfigurer?) {
        clients!!.inMemory()
                .withClient(browserCredentials!!.client)
                .secret(browserCredentials!!.secret)
                .scopes("api")
                .authorizedGrantTypes("refresh_token", "password")
    }

    @Bean
    fun tokenStore(): TokenStore {
        return JwtTokenStore(accessTokenConverter())
    }

    @Bean
    fun accessTokenConverter(): JwtAccessTokenConverter {
        val jwtPrivateKey = jwtPrivateKey!!

        val keyStoreKeyFactory = KeyStoreKeyFactory(
                jwtPrivateKey.keystoreResource,
                jwtPrivateKey.keystorePassword!!.toCharArray())

        val jwtAccessTokenConverter = JwtAccessTokenConverter()
        jwtAccessTokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair(jwtPrivateKey.keyAlias))

        return jwtAccessTokenConverter
    }

    @Bean
    @Primary
    fun tokenServices(): DefaultTokenServices {
        val defaultTokenServices = DefaultTokenServices()
        defaultTokenServices.setTokenStore(tokenStore())
        defaultTokenServices.setSupportRefreshToken(true)
        defaultTokenServices.setTokenEnhancer(accessTokenConverter())
        return defaultTokenServices
    }

}