package com.example.projeto_003.model

import androidx.room.*

@Entity
data class Doctor(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "doctor_id")
    val id: Int = 0,
    @ColumnInfo(name = "doctor_name")
    val name: String,
    val specialityFK: Int = 0
)
data class DoctorWithSpecialist(
    @Embedded
    val doctor: Doctor?,
    @Relation(
        parentColumn = "specialityFK",
        entityColumn = "specialist_id"
    )
    val specialist: Specialist?
)
