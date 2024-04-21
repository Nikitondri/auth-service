package com.zakharenko.gatewayservice.service.keycloak.builder

import com.zakharenko.gatewayservice.connector.dto.TokenRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class TokenRequestBuilder(
  @Value("\${keycloak.clientId}") private val clientId: String,
  @Value("\${keycloak.clientSecret}") private val clientSecret: String,
) {
  fun build(): TokenRequest {
    return TokenRequest(
      clientId = clientId,
      clientSecret = clientSecret,
    )
  }
}
