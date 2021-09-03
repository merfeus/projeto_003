package com.example.projeto_003.respository

import com.example.projeto_003.database.dao.SpecialistDAO
import com.example.projeto_003.model.Specialist
import javax.inject.Inject

class SpecialistRepository @Inject constructor(private val specialistDAO: SpecialistDAO) {

    fun getPatient(): List<Specialist>{
        return specialistDAO.getSpecialist()
    }

    fun insertPatient(specialist: Specialist){
        return specialistDAO.insert(arrayListOf(specialist))
    }
}