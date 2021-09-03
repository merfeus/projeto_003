package com.example.projeto_003.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projeto_003.model.Doctor
import com.example.projeto_003.model.DoctorWithSpecialist
import com.example.projeto_003.model.Specialist
import com.example.projeto_003.respository.DoctorRepository
import com.example.projeto_003.respository.SpecialistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DoctorViewModel @Inject constructor(
    private val doctorRepository: DoctorRepository,
    private val specialistRepository: SpecialistRepository
) :
    ViewModel() {

    private val _DOCTOR = MutableLiveData<List<DoctorWithSpecialist>>()
    val doctor: LiveData<List<DoctorWithSpecialist>> = _DOCTOR

    private val _SPECIALIST = MutableLiveData<List<Specialist>>()
    val specialist: LiveData<List<Specialist>> = _SPECIALIST

    fun getAllDoctor() {
        _DOCTOR.value = doctorRepository.getDoctor()
    }

    fun insertDoctor(doctor: Doctor) {
        doctorRepository.insertDoctor(doctor)
        getAllDoctor()
    }

    fun updateDoctor(doctor: Doctor) {
        doctorRepository.updateDoctor(doctor)
        getAllDoctor()
    }

    fun deletDoctor(doctor: Doctor) {
        doctorRepository.deletDoctor(doctor)
        getAllDoctor()
    }

    fun getSpecialist() {
        _SPECIALIST.value =specialistRepository.getSpecialist()
    }
}