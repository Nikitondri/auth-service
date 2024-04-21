package com.zakharenko.gatewayservice.model

import java.time.LocalDate

data class CreatePatientRequest(
  val email: String,
  val name: String,
  val gender: Gender,
  val birthDate: LocalDate,
)
