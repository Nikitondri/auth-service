package com.zakharenko.patientdomain.service

import com.zakharenko.patient.api.PatientRequest
import com.zakharenko.patient.api.PatientResponse
import com.zakharenko.patientdomain.repository.PatientRepository
import com.zakharenko.patientdomain.service.builder.PatientPojoBuilder
import com.zakharenko.patientdomain.service.builder.PatientResponseBuilder
import org.springframework.stereotype.Service

@Service
class PatientStoreService(
  private val patientRepository: PatientRepository,
) {

  fun getById(patientId: String): PatientResponse? {
    return patientRepository.getById(patientId)
      ?.let(PatientResponseBuilder::build)
  }

  fun getAll(limit: Int, offset: Int, asc: Boolean): List<PatientResponse> {
    return patientRepository.getAll(limit, offset, asc)
      .map(PatientResponseBuilder::build)
  }

  fun save(request: PatientRequest) {
    PatientPojoBuilder.build(request)
      .let(patientRepository::save)
  }

  fun update(request: PatientRequest) {
    PatientPojoBuilder.build(request)
      .let(patientRepository::update)
  }

  fun delete(patientId: String) {
    patientRepository.delete(patientId)
  }
}
