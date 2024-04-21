package com.zakharenko.gatewayservice.connector

import com.zakharenko.patient.api.PatientRequest
import com.zakharenko.patient.api.PatientResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
  name = "patient-client",
  url = "\${connector.url.patient}"
)
interface PatientConnector {

  @PostMapping(
    value = ["/patients"],
    consumes = [MediaType.APPLICATION_JSON_VALUE]
  )
  fun save(
    @RequestHeader("Authorization") authorization: String,
    @RequestBody request: PatientRequest,
  )

  @GetMapping(
    value = ["/patients/{id}"],
  )
  fun getById(
    @RequestHeader("Authorization") authorization: String,
    @PathVariable("id") id: String,
  ): PatientResponse

  @GetMapping(
    value = ["/patients"],
  )
  fun getAll(
    @RequestHeader("Authorization") authorization: String,
    @RequestParam("limit") limit: Int = 100,
    @RequestParam("offset") offset: Int = 0,
    @RequestParam("asc") asc: Boolean = true,
  ): List<PatientResponse>

  @PutMapping(
    value = ["/patients"],
  )
  fun update(
    @RequestHeader("Authorization") authorization: String,
    @RequestBody request: PatientRequest,
  )

  @DeleteMapping(
    value = ["/patients/{id}"],
  )
  fun deleteById(
    @RequestHeader("Authorization") authorization: String,
    @PathVariable("id") id: String,
  )
}
