package com.example.projeto_003.database.dao

import androidx.room.*
import com.example.projeto_003.model.Specialist

@Dao
interface SpecialistDAO {

    @Query("Select * from Specialist order by specialist_name")
    fun fetch(): List<Specialist>

    @Query("Select * from Specialist where specialist_id = :id")
    fun fetchId(id: Int): Specialist

    @Query("Select * from Specialist where specialist_name like '%' || :name || '%'")
    fun fetchName(name: String): List<Specialist>

    @Query("SELECT * FROM Specialist")
    fun getSpecialist(): List<Specialist>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: List<Specialist>)

    @Delete
    fun delete(specialist: Specialist)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(specialist: Specialist)
}