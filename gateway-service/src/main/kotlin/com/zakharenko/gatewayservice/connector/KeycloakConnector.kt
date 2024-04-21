package com.zakharenko.gatewayservice.connector

import com.zakharenko.gatewayservice.config.FormFeignEncoderConfig
import com.zakharenko.gatewayservice.connector.dto.CreateUserRequest
import com.zakharenko.gatewayservice.connector.dto.RoleMappingRequest
import com.zakharenko.gatewayservice.connector.dto.TokenRequest
import com.zakharenko.gatewayservice.connector.dto.TokenResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(
  name = "keycloak-client",
  url = "\${keycloak.baseUrl}",
  configuration = [FormFeignEncoderConfig::class],
)
interface KeycloakConnector {

  @PostMapping(
    value = ["/realms/auth-service-keycloak/protocol/openid-connect/token"],
    consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE]
  )
  fun getToken(@RequestBody request: TokenRequest): TokenResponse

  @PostMapping(
    value = ["/admin/realms/auth-service-keycloak/users"],
    consumes = [MediaType.APPLICATION_JSON_VALUE],
  )
  fun createUser(
    @RequestHeader("Authorization") authorization: String,
    @RequestBody createUserRequest: CreateUserRequest,
  ): ResponseEntity<Void>

  @PostMapping(
    value = ["/admin/realms/auth-service-keycloak/users/{userId}/role-mappings/clients/{clientUuid}"],
    consumes = [MediaType.APPLICATION_JSON_VALUE],
  )
  fun mapRole(
    @RequestHeader("Authorization") authorization: String,
    @PathVariable("userId") userId: String,
    @PathVariable("clientUuid") clientUuid: String,
    @RequestBody roleMappingRequest: List<RoleMappingRequest>,
  )
}
