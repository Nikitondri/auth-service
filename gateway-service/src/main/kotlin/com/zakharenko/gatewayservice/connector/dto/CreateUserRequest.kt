package com.zakharenko.gatewayservice.connector.dto

data class CreateUserRequest(
  val username: String,
  val enabled: Boolean = true,
  val credentials: List<Credential>,
)

data class Credential(
  val type: String = "password",
  val value: String,
  val temporary: Boolean = false,
)

