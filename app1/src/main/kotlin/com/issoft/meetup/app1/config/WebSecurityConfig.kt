package com.issoft.meetup.app1.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers.anyExchange
import org.springframework.security.authorization.AuthenticatedReactiveAuthorizationManager.authenticated
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.context.ServerSecurityContextRepository


@Configuration
@EnableWebFluxSecurity
class WebSecurityConfig {

    @Autowired
    private val authenticationManager: ReactiveAuthenticationManager? = null

    @Autowired
    private val securityContextRepository: ServerSecurityContextRepository? = null

    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        // Disable default security.
        http.httpBasic().disable()
        http.formLogin().disable()
        http.csrf().disable()
        http.logout().disable()

        // Add custom security.
        http.authenticationManager(authenticationManager)
        http.securityContextRepository(securityContextRepository)

        http.authorizeExchange().anyExchange().authenticated()

        return http.build()
    }

    /*@Bean
    fun userDetailsService(): MapReactiveUserDetailsService {
        val user = User
                .withUsername("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .build()
        return MapReactiveUserDetailsService(user)
    }*/

    /*@Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http
                .authorizeExchange()
                .anyExchange().authenticated()
                .and()
                .httpBasic()
        return http.build()
    }*/

    /*@Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }*/

}