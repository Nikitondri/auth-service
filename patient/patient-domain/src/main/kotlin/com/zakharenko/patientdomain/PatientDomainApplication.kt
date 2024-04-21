package com.zakharenko.patientdomain

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PatientDomainApplication

fun main(args: Array<String>) {
  runApplication<PatientDomainApplication>(*args)
}
