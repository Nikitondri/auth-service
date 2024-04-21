package com.zakharenko.gatewayservice.controller

import com.zakharenko.gatewayservice.model.CreatePatientRequest
import com.zakharenko.gatewayservice.service.keycloak.KeycloakAuthService
import com.zakharenko.gatewayservice.service.patient.PatientService
import com.zakharenko.patient.api.PatientResponse
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class PatientController(
  private val keycloakAuthService: KeycloakAuthService,
  private val patientService: PatientService,
) {

  @PostMapping
  fun saveUser(
    @RequestBody createPatientRequest: CreatePatientRequest,
    @RegisteredOAuth2AuthorizedClient("keycloak") authorizedClient: OAuth2AuthorizedClient,
  ) {
    keycloakAuthService.saveUser(createPatientRequest.email)
      .let { keycloakId ->
        patientService.save(
          keycloakUserId = keycloakId,
          request = createPatientRequest,
          token = authorizedClient.accessToken.tokenValue,
        )
      }
  }

  @GetMapping("/{id}")
  fun getPatient(
    @PathVariable("id") patientId: String,
    @RegisteredOAuth2AuthorizedClient("keycloak") authorizedClient: OAuth2AuthorizedClient,
  ): PatientResponse {
    return patientService.getById(authorizedClient.accessToken.tokenValue, patientId)
  }

  //todo other methods
}
