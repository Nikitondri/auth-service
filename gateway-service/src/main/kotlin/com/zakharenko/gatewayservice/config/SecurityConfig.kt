package com.zakharenko.gatewayservice.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
open class SecurityConfig(
) {

  @Bean
  open fun serverFilterChain(http: HttpSecurity): SecurityFilterChain {
    return http
      .authorizeHttpRequests {
        it
          .requestMatchers("/error").permitAll()
          .requestMatchers(HttpMethod.GET, "/patients/**").hasAnyRole("DOCTOR", "PATIENT")
          .requestMatchers(HttpMethod.POST, "/patients/**").hasRole("DOCTOR")
          .requestMatchers(HttpMethod.PUT, "/patients/**").hasRole("DOCTOR")
          .requestMatchers(HttpMethod.DELETE, "/patients/**").hasRole("DOCTOR")
          .anyRequest().authenticated()
      }
      .oauth2Login(Customizer.withDefaults())
      .csrf { it.disable() }
      .oauth2ResourceServer { oauth2 -> oauth2.jwt(Customizer.withDefaults()) }
      .build()
  }

  @Bean
  open fun oAuth2UserService(): OAuth2UserService<OidcUserRequest, OidcUser> {
    val oidcUserService = OidcUserService()
    return OAuth2UserService<OidcUserRequest, OidcUser> { userRequest ->
      val oidcUser = oidcUserService.loadUser(userRequest)
      val roles = oidcUser.getClaimAsStringList("auth_service_roles")
      val defaultAuthorities = oidcUser.authorities

      val authorities = roles
        .filter { it.startsWith("ROLE_") }
        .map(::SimpleGrantedAuthority)
        .plus(defaultAuthorities)

      DefaultOidcUser(authorities, oidcUser.idToken, oidcUser.userInfo)
    }
  }

  @Bean
  open fun jwtAuthenticationConverter(): JwtAuthenticationConverter {
    val jwtGrantedAuthoritiesConverter = JwtGrantedAuthoritiesConverter()
    return JwtAuthenticationConverter().apply {
      setPrincipalClaimName("preferred_username")
      setJwtGrantedAuthoritiesConverter { jwt ->
        val roles = jwt.getClaimAsStringList("auth_service_roles")
        val authorities = jwtGrantedAuthoritiesConverter
          .convert(jwt)
          ?.map { grantedAuthority -> grantedAuthority as SimpleGrantedAuthority }
          ?: emptyList()

        roles
          .filter { it.startsWith("ROLE_") }
          .map(::SimpleGrantedAuthority)
          .plus(authorities)
      }
    }
  }
}
