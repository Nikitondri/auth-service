package com.zakharenko.gatewayservice.connector.dto

data class RoleMappingRequest(
  val id: String,
  val name: String = "ROLE_PATIENT",
  val description: String = "ROLE_PATIENT",
  val composite: Boolean = false,
  val clientRole: Boolean = true,
  val containerId: String = "2eee29e8-73f2-4dce-8a1e-05543597be59",
)
