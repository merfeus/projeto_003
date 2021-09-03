package com.example.projeto_003.database.dao

import androidx.room.*
import com.example.projeto_003.model.Patient
import com.example.projeto_003.model.Specialist

@Dao
interface SpecialistDAO {

    @Query("SELECT * FROM Specialist")
    fun getSpecialist(): List<Specialist>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: List<Specialist>)

    @Delete
    fun delete(patient: Specialist)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(patient: Specialist)
}