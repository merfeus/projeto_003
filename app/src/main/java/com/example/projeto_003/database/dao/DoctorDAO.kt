package com.example.projeto_003.database.dao

import androidx.room.*
import com.example.projeto_003.model.Doctor
import com.example.projeto_003.model.DoctorWithSpecialist
import com.example.projeto_003.model.Specialist

@Dao
interface DoctorDAO {

    @Transaction
    @Query("SELECT * FROM Doctor")
    fun getDoctor(): List<DoctorWithSpecialist>

    @Insert
    fun insertDoctor (doctor: Doctor)

    @Delete
    fun deletDoctor (doctor: Doctor)

    @Update
    fun updateDoctor(doctor: Doctor)
}