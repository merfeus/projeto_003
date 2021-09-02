package com.example.projeto_003.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Patient(
    @PrimaryKey
    @ColumnInfo(name = "patient_id")
    val id: Int = 0,
    @ColumnInfo(name = "patient_name")
    val name: String,
    @ColumnInfo(name = "patient_age")
    val age: Int = 0,
    @ColumnInfo(name = "Patient_sex")
    val sex: String = "Other"
)
