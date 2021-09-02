package com.example.projeto_003.database.dao

import androidx.room.*
import com.example.projeto_003.model.Patient

@Dao
interface PatientDAO {

    @Query("SELECT * FROM Patient")
    fun getPatient(): List<Patient>

    @Insert
    fun insert(list: List<Patient>)

    @Delete
    fun delete(patient: Patient)

    @Update
    fun update(patient: Patient)


}