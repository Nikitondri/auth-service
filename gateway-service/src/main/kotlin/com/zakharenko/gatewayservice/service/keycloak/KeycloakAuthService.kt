package com.zakharenko.gatewayservice.service.keycloak

import com.zakharenko.gatewayservice.connector.KeycloakConnector
import com.zakharenko.gatewayservice.connector.dto.RoleMappingRequest
import com.zakharenko.gatewayservice.connector.dto.TokenResponse
import com.zakharenko.gatewayservice.service.AuthHeaderBuilder
import com.zakharenko.gatewayservice.service.keycloak.builder.TokenRequestBuilder
import com.zakharenko.gatewayservice.service.keycloak.builder.UserRequestBuilder
import com.zakharenko.gatewayservice.service.keycloak.util.UserIdExtractor
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class KeycloakAuthService(
  private val tokenRequestBuilder: TokenRequestBuilder,
  private val keycloakConnector: KeycloakConnector,
  @Value("\${keycloak.clientUuid}") private val clientUuid: String,
  @Value("\${keycloak.patientRoleId}") private val patientRoleId: String,
) {

  fun saveUser(userName: String): String {
    return tokenRequestBuilder.build()
      .let(keycloakConnector::getToken)
      .let(TokenResponse::accessToken)
      .let(AuthHeaderBuilder::build)
      .let { token -> save(userName, token) }
  }

  private fun save(userName: String, token: String): String {
    //TODO: Here you can add logic to send the generated password via email to the user's email address
    return keycloakConnector.createUser(token, UserRequestBuilder.build(userName))
      .let(UserIdExtractor::extract)
      ?.also { userId ->
        keycloakConnector.mapRole(token, userId, clientUuid, listOf(RoleMappingRequest(patientRoleId)))
      }
      ?: error("saveUser with username=${userName} finished with empty response")
  }
}
