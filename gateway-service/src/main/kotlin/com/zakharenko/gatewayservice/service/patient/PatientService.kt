package com.zakharenko.gatewayservice.service.patient

import com.zakharenko.gatewayservice.connector.PatientConnector
import com.zakharenko.gatewayservice.model.CreatePatientRequest
import com.zakharenko.gatewayservice.service.AuthHeaderBuilder
import com.zakharenko.gatewayservice.service.patient.builder.PatientRequestBuilder
import com.zakharenko.patient.api.PatientResponse
import org.springframework.stereotype.Service

@Service
class PatientService(
  private val patientConnector: PatientConnector,
) {

  fun save(keycloakUserId: String, request: CreatePatientRequest, token: String) {
    patientConnector.save(
      authorization = AuthHeaderBuilder.build(token),
      request = PatientRequestBuilder.build(keycloakUserId, request),
    )
  }

  fun getById(token: String, patientId: String): PatientResponse {
    return patientConnector.getById(AuthHeaderBuilder.build(token), patientId)
  }

  fun getAll(token: String): List<PatientResponse> {
    return patientConnector.getAll(AuthHeaderBuilder.build(token))
  }

  fun update(request: CreatePatientRequest, token: String) {
    patientConnector.update(AuthHeaderBuilder.build(token), PatientRequestBuilder.build(null, request))
  }

  fun delete(token: String, patientId: String) {
    patientConnector.deleteById(AuthHeaderBuilder.build(token), patientId)
  }
}
