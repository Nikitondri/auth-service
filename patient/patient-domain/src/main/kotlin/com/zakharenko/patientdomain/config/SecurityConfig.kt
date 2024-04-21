package com.zakharenko.patientdomain.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
open class SecurityConfig {

  @Bean
  open fun resourceServerFilterChain(http: HttpSecurity): SecurityFilterChain {
    http.oauth2ResourceServer { oauth2 ->
      oauth2
        .jwt(Customizer.withDefaults())
    }

    return http
      .authorizeHttpRequests {
        it
          .requestMatchers("/error").permitAll()
          .requestMatchers(HttpMethod.GET, "/patients/**").hasAnyRole("PATIENT", "DOCTOR")
          .requestMatchers(HttpMethod.POST, "/patients/**").hasRole("DOCTOR")
          .requestMatchers(HttpMethod.PUT, "/patients/**").hasRole("DOCTOR")
          .requestMatchers(HttpMethod.DELETE, "/patients/**").hasRole("DOCTOR")
          .anyRequest().authenticated()
      }
      .csrf { it.disable() }
      .build()
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
