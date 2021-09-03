package com.example.projeto_003.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projeto_003.database.dao.PatientDAO
import com.example.projeto_003.model.Patient
import com.example.projeto_003.respository.PatientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class PatientViewModel @Inject constructor(private val patientRepository: PatientRepository) : ViewModel() {

    private val _PATIENT = MutableLiveData<List<Patient>>()
    val patient: LiveData<List<Patient>> = _PATIENT

    fun getAllPatient() {
        _PATIENT.value = patientRepository.getPatient()
    }

    fun insertPatient(patient: Patient) {
        patientRepository.insertPatient(patient)
        getAllPatient()
    }

    fun updatePatient(patient: Patient) {
        patientRepository.updatePatient(patient)
        getAllPatient()
    }

    fun deletPatient(patient: Patient) {
        patientRepository.deletPatient(patient)
        getAllPatient()
    }
}