package com.example.projeto_003.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Specialist(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "specialist_id")
    val id: Int = 0,
    @ColumnInfo(name = "specialist_name")
    val name: String = "Other"
)
