package com.example.projeto_003.respository

import com.example.projeto_003.database.dao.PlannerDAO
import com.example.projeto_003.model.Doctor
import com.example.projeto_003.model.Planner
import com.example.projeto_003.model.PlannerWithPADOC
import javax.inject.Inject

class PlannerRepository @Inject constructor(private val plannerDAO: PlannerDAO) {

    fun getPlanner(): List<PlannerWithPADOC>{
        return plannerDAO.fetch()
    }

    fun insertPlanner(planner: Planner){
        return plannerDAO.insertPlanner(planner)
    }

    fun updateDoctor(planner: Planner){
        return plannerDAO.updatePlanner(planner)
    }

    fun deletDoctor(planner: Planner){
        return plannerDAO.updatePlanner(planner)
    }
}