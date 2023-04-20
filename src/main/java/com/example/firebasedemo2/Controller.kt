package com.example.firebasedemo2

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.concurrent.ExecutionException


@RestController
class Controller {
    @Autowired
    var patientService: PatientService? = null
    @GetMapping(value = ["/create"],  produces= arrayOf("application/json"))
    @Throws(InterruptedException::class, ExecutionException::class)
    fun createPatient(
        @RequestParam name: String,
        @RequestParam age: Int,
        @RequestParam city: String
    ): String {
        val patient = Patient(name, age, city)
        return patientService!!.savePatientDetails(patient)
    }

    @GetMapping(value = ["/details"],  produces= arrayOf("application/json"))
    @ResponseBody
    @Throws(InterruptedException::class, ExecutionException::class)
    fun getPatient(@RequestParam name: String?): String {
        return patientService!!.getPatientDetails(name)
    }

    @PutMapping("/update")
    @Throws(InterruptedException::class, ExecutionException::class)
    fun updatePatient(@RequestBody patient: Patient): String {
        return patientService!!.updatePatientDetails(patient)
    }

    @DeleteMapping("/delete")
    fun deletePatient(@RequestParam name: String): String {
        return patientService!!.deletePatient(name)
    }
}