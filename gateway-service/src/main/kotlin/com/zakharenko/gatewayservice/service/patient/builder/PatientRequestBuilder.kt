package com.zakharenko.gatewayservice.service.patient.builder

import com.zakharenko.gatewayservice.model.CreatePatientRequest
import com.zakharenko.patient.api.PatientRequest

object PatientRequestBuilder {
  fun build(keycloakId: String?, request: CreatePatientRequest): PatientRequest {
    return PatientRequest(
      keycloakId = keycloakId,
      email = request.email,
      name = request.name,
      gender = request.gender.name,
      birthDate = request.birthDate,
    )
  }
}
