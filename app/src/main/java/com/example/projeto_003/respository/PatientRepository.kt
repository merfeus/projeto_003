package com.example.projeto_003.respository

import com.example.projeto_003.database.dao.PatientDAO
import com.example.projeto_003.model.Patient
import javax.inject.Inject

class PatientRepository @Inject constructor(private val patientDAO: PatientDAO) {

    fun getPatient(): List<Patient>{
        return patientDAO.getPatient()
    }

    fun insertPatient(patient: Patient){
        return patientDAO.insert(arrayListOf(patient))
    }

    fun updatePatient(patient: Patient){
        return patientDAO.update(patient)
    }

    fun deletPatient(patient: Patient){
        return patientDAO.delete(patient)
    }
}