package com.zakharenko.gatewayservice.service.keycloak.util

import org.springframework.http.ResponseEntity

object UserIdExtractor {
  fun extract(response: ResponseEntity<Void>): String? {
    return response
      .headers
      .get("Location")
      ?.get(0)
      ?.split("/")
      ?.lastOrNull()
  }
}
