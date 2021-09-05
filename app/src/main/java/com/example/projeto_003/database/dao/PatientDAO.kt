package com.example.projeto_003.database.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.projeto_003.model.Patient

@Dao
interface PatientDAO {

    @Query("SELECT * FROM Patient order by patient_name")
    fun getPatient(): List<Patient>

    @Query("Select * from Patient where patient_id = :id")
    fun fetch(id: Int): Patient

    @Query("Select * from Patient where patient_gender = :gender")
    fun fetch(gender: String): List<Patient>

    @Insert
    fun insert(list: List<Patient>)

    @Delete
    fun delete(patient: Patient)

    @Update
    fun update(patient: Patient)


}