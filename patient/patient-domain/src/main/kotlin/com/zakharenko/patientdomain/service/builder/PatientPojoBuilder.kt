package com.zakharenko.patientdomain.service.builder

import com.zakharenko.patient.api.PatientRequest
import org.jooq.generated.auth_service.tables.pojos.Patient
import java.util.UUID

object PatientPojoBuilder {
  fun build(request: PatientRequest): Patient {
    return Patient(
      UUID.randomUUID().toString(),
      request.keycloakId,
      request.name,
      request.gender,
      request.birthDate,
    )
  }
}
