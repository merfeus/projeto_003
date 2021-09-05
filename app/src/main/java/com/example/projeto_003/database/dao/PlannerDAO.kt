package com.example.projeto_003.database.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.ABORT
import com.example.projeto_003.model.Planner
import com.example.projeto_003.model.PlannerWithPADOC

@Dao
interface PlannerDAO {

    @Transaction
    @Query("Select * from Planner")
    fun fetch(): List<PlannerWithPADOC>

    @Transaction
    @Query("Select * from Planner where planner_id = :id")
    fun fetch(id: Int): PlannerWithPADOC

    @Transaction
    @Query("Select * from Planner inner join Doctor on doctor.doctor_id = doctorFK where doctor.doctor_name like '%' || :name || '%'")
    fun fetchByDoctor(name: String): List<PlannerWithPADOC>

    @Transaction
    @Query("Select * from Planner inner join Doctor on doctor.doctor_id = doctorFK where doctor.specialityFK in (:ids)")
    fun fetchByDoctorSpecialist(ids: List<Int>): List<PlannerWithPADOC>

    @Transaction
    @Query("Select * from Planner inner join Patient on patient.patient_id = patientFK where patient_gender = :gender")
    fun fetchByGender(gender: String): List<PlannerWithPADOC>

    @Insert(onConflict = ABORT)
    fun insertPlanner(planner: Planner)

    @Delete
    fun deletePlanner(planner: Planner)

    @Update
    fun updatePlanner(planner: Planner)

}