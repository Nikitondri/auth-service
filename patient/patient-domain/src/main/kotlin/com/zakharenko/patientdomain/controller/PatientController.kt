package com.zakharenko.patientdomain.controller

import com.zakharenko.patient.api.PatientRequest
import com.zakharenko.patient.api.PatientResponse
import com.zakharenko.patientdomain.service.PatientStoreService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/patients")
class PatientController(
  private val patientStoreService: PatientStoreService,
) {

  @GetMapping("/{id}")
  fun getById(
    @PathVariable("id") patientId: String,
  ): PatientResponse? {
    return patientStoreService.getById(patientId)
  }

  @GetMapping
  fun getAll(
    @RequestParam("limit") limit: Int = 100,
    @RequestParam("offset") offset: Int = 0,
    @RequestParam("asc") asc: Boolean = true,
  ): List<PatientResponse> {
    return patientStoreService.getAll(limit, offset, asc)
  }

  @PostMapping
  fun save(
    @RequestBody patientRequest: PatientRequest,
  ) {
    patientStoreService.save(patientRequest)
  }

  @PutMapping
  fun update(
    @RequestBody patientRequest: PatientRequest,
  ) {
    patientStoreService.update(patientRequest)
  }

  @DeleteMapping("/{id}")
  fun delete(
    @PathVariable("id") patientId: String,
  ) {
    patientStoreService.delete(patientId)
  }
}
