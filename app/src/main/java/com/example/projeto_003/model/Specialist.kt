package com.example.projeto_003.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Specialist(
    @PrimaryKey
    @ColumnInfo(name = "Specialist_id")
    val id: Int,
    @ColumnInfo(name = "Specialist_name")
    val name: String
)
