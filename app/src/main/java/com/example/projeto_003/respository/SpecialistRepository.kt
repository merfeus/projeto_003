package com.example.projeto_003.respository

import com.example.projeto_003.database.dao.SpecialistDAO
import com.example.projeto_003.model.Patient
import com.example.projeto_003.model.Specialist
import javax.inject.Inject

class SpecialistRepository @Inject constructor(private val specialistDAO: SpecialistDAO) {

    fun getSpecialist(): List<Specialist>{
        return specialistDAO.getSpecialist()
    }

    fun insertSpecialist(specialist: Specialist){
        return specialistDAO.insert(arrayListOf(specialist))
    }

    fun updateSpecialist(specialist: Specialist){
        return specialistDAO.update(specialist)
    }

    fun deletPatient(specialist: Specialist){
        return specialistDAO.delete(specialist)
    }
}