package com.example.projeto_003.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projeto_003.database.dao.PatientDAO
import com.example.projeto_003.model.Patient
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class PatientViewModel @Inject constructor(private val patientDAO: PatientDAO) : ViewModel() {

    private val _PATIENT = MutableLiveData<List<Patient>>()
    val patient: LiveData<List<Patient>> = _PATIENT

    fun getAllPatient() {
        _PATIENT.value = patientDAO.getPatient()
    }

    fun inserPatient(patient: Patient) {
        patientDAO.insert(arrayListOf(patient))
        getAllPatient()
    }

    fun updatePatient(patient: Patient) {
        patientDAO.update(patient)
        getAllPatient()
    }

    fun deletPatient(patient: Patient) {
        patientDAO.delete(patient)
        getAllPatient()
    }
}