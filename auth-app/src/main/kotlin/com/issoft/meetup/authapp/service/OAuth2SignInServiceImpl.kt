package com.issoft.meetup.authapp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.TokenRequest
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


@Service
class OAuth2SignInServiceImpl @Autowired constructor(
        private val clientDetailsService: ClientDetailsService,
        private val tokenServices: AuthorizationServerTokenServices,
        private val userDetailsService: UserDetailsService) : OAuth2SignInService {

    private val CLIENT_ID_BROWSER = "browser"


    override fun signIn(userName: String): Mono<OAuth2AccessToken> {
        val userDetails = userDetailsService.loadUserByUsername(userName)

        // create OAuth2 access token request
        val authenticatedClient = clientDetailsService.loadClientByClientId(CLIENT_ID_BROWSER)

        val tokenRequest = TokenRequest(null,
                authenticatedClient.clientId,
                authenticatedClient.scope, null)
        val storedOAuth2Request = tokenRequest.createOAuth2Request(authenticatedClient)

        // create OAuth2 user principal and generate access token for it
        val userAuthentication = UsernamePasswordAuthenticationToken(userDetails.username, userDetails.password, userDetails.authorities)

        return Mono.just(tokenServices.createAccessToken(OAuth2Authentication(storedOAuth2Request,
                OAuth2Authentication(storedOAuth2Request, userAuthentication))))
    }

}