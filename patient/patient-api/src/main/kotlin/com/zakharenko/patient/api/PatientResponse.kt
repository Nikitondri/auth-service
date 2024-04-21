package com.zakharenko.patient.api

import java.time.LocalDate

data class PatientResponse(
  val id: String,
  val name: String,
  val gender: String,
  val birthDate: LocalDate,
)
