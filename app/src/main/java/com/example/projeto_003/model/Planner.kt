package com.example.projeto_003.model

import androidx.room.*

@Entity
data class Planner(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "planner_id")
    val id: Int = 0,
    val patientFK: Int,
    val doctorFK: Int

)

data class PlannerWithPADOC(
    @Embedded
    val planner: Planner,

    @Relation(
        parentColumn = "patientFK",
        entityColumn = "patient_id"
    )
    val patient: Patient,

    @Relation(
        parentColumn = "doctorFK",
        entityColumn = "doctor_id"
    )
    val doctor: Doctor
)
