package com.example.projeto_003.model

import androidx.room.*

@Entity
data class Doctor(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "doctor_name")
    val name: String,
    val specialityFK: String
)
data class DoctorWithSpecialist(
    @Embedded
    val doctor: Doctor?,
    @Relation(
        parentColumn = "specialityFK",
        entityColumn = "Specialist_id"
    )
    val specialist: Specialist?
)
