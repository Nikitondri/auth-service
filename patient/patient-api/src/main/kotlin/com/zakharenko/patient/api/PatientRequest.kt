package com.zakharenko.patient.api

import java.time.LocalDate

data class PatientRequest(
  val keycloakId: String? = null,
  val email: String,
  val name: String,
  val gender: String,
  val birthDate: LocalDate,
)
