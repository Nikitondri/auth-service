package com.zakharenko.gatewayservice.service.keycloak.builder

import com.zakharenko.gatewayservice.connector.dto.CreateUserRequest
import com.zakharenko.gatewayservice.connector.dto.Credential
import java.util.UUID

object UserRequestBuilder {
  fun build(userName: String): CreateUserRequest {
    return CreateUserRequest(
      username = userName,
      credentials = listOf(
        Credential(
          value = UUID.randomUUID().toString()
        )
      )
    )
  }
}
