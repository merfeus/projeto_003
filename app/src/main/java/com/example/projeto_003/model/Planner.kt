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

data class PlannerPADOC(
    @Embedded
    val planner: Planner,

    @Relation(
        parentColumn = "patientFK",
        entityColumn = "p"
    )
    val patient: Patient,

    @Relation(
        parentColumn = "physicianFK",
        entityColumn = "phy_id"
    )
    val physician: Physician
)
