package com.zakharenko.patientdomain.service.builder

import com.zakharenko.patient.api.PatientResponse
import org.jooq.generated.auth_service.tables.pojos.Patient

object PatientResponseBuilder {
  fun build(patient: Patient): PatientResponse {
    return PatientResponse(
      id = patient.id,
      name = patient.name,
      gender = patient.gender,
      birthDate = patient.birthdate,
    )
  }
}
