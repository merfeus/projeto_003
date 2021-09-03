package com.example.projeto_003.respository

import com.example.projeto_003.database.dao.DoctorDAO
import com.example.projeto_003.model.Doctor
import com.example.projeto_003.model.DoctorWithSpecialist
import com.example.projeto_003.model.Patient
import javax.inject.Inject

class DoctorRepository @Inject constructor( private val doctorDAO: DoctorDAO) {

    fun getDoctor(): List<DoctorWithSpecialist>{
        return doctorDAO.getDoctor()
    }

    fun insertDoctor(doctor: Doctor){
        return doctorDAO.insertDoctor(doctor)
    }

    fun updateDoctor(doctor: Doctor){
        return doctorDAO.updateDoctor(doctor)
    }

    fun deletDoctor(doctor: Doctor){
        return doctorDAO.deletDoctor(doctor)
    }
}