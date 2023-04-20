package com.example.firebasedemo2


import com.google.firebase.cloud.FirestoreClient
import com.google.gson.Gson
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ResponseBody
import java.util.concurrent.ExecutionException


@Service
class PatientService {
    val gson = Gson()

    @Throws(InterruptedException::class, ExecutionException::class)
    fun savePatientDetails(patient: Patient): String {
        val dbFirestore = FirestoreClient.getFirestore()
        val collectionsApiFuture = dbFirestore.collection(COL_NAME).document(
            patient.getName().toString()
        ).set(patient)
        return gson.toJson(collectionsApiFuture.get().updateTime.toString())
    }

    @ResponseBody
    @Throws(InterruptedException::class, ExecutionException::class)
    fun getPatientDetails(name: String?): String {
        val dbFirestore = FirestoreClient.getFirestore()
        val documentReference = dbFirestore.collection(COL_NAME).document(
            name!!
        )
        val future = documentReference.get()
        val document = future.get()
        var patient: Patient? = null
        return if (document.exists()) {
            patient = document.toObject(Patient::class.java)
            gson.toJson(patient)
        } else {
            "Not found!"
        }
    }

    @Throws(InterruptedException::class, ExecutionException::class)
    fun updatePatientDetails(person: Patient): String {
        val dbFirestore = FirestoreClient.getFirestore()
        val collectionsApiFuture = dbFirestore.collection(COL_NAME).document(
            person.getName().toString()
        ).set(person)
        return collectionsApiFuture.get().updateTime.toString()
    }

    fun deletePatient(name: String): String {
        val dbFirestore = FirestoreClient.getFirestore()
        dbFirestore.collection(COL_NAME).document(name).delete()
        return "Document with Patient ID $name has been deleted"
    }

    companion object {
        const val COL_NAME = "users"
    }
}
