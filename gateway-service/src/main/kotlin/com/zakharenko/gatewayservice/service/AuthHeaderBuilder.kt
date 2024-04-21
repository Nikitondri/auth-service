package com.zakharenko.gatewayservice.service

object AuthHeaderBuilder {
  fun build(token: String): String {
    return "Bearer $token"
  }
}
