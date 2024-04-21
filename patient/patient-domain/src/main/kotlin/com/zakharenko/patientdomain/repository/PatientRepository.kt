package com.zakharenko.patientdomain.repository

import org.jooq.DSLContext
import org.jooq.generated.auth_service.Tables.PATIENT
import org.jooq.generated.auth_service.tables.pojos.Patient
import org.springframework.stereotype.Repository

@Repository
class PatientRepository(
  private val dslContext: DSLContext,
) {

  fun getById(patientId: String): Patient? {
    return dslContext.selectFrom(PATIENT)
      .where(PATIENT.ID.eq(patientId))
      .fetchOneInto(Patient::class.java)
  }

  fun getAll(
    limit: Int = 100,
    offset: Int = 0,
    asc: Boolean = true,
  ): List<Patient> {
    return dslContext.selectFrom(PATIENT)
      .orderBy(if (asc) PATIENT.ID.asc() else PATIENT.ID.desc())
      .offset(offset)
      .limit(limit)
      .fetchInto(Patient::class.java)
  }

  fun save(patient: Patient) {
    dslContext.insertInto(PATIENT)
      .set(dslContext.newRecord(PATIENT, patient))
      .execute()
  }

  fun update(patient: Patient) {
    dslContext.update(PATIENT)
      .set(dslContext.newRecord(PATIENT, patient))
      .where(PATIENT.ID.eq(patient.id))
      .execute()
  }

  fun delete(patientId: String) {
    dslContext.deleteFrom(PATIENT)
      .where(PATIENT.ID.eq(patientId))
      .execute()
  }
}
