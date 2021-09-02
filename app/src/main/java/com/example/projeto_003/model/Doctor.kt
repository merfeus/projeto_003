package com.example.projeto_003.model

import androidx.room.Entity

@Entity
data class Doctor(
    val name: String,
    val speciality: Specialist
)
