package com.zakharenko.gatewayservice.connector.dto

import feign.form.FormProperty

data class TokenRequest(
  @FormProperty("client_id")
  var clientId: String? = null,
  @FormProperty("client_secret")
  var clientSecret: String? = null,
  @FormProperty("grant_type")
  var grantType: String = "client_credentials",
)
