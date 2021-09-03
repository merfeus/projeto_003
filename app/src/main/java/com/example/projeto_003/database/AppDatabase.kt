package com.example.projeto_003.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.projeto_003.database.dao.DoctorDAO
import com.example.projeto_003.database.dao.PatientDAO
import com.example.projeto_003.database.dao.SpecialistDAO
import com.example.projeto_003.model.Doctor
import com.example.projeto_003.model.Patient
import com.example.projeto_003.model.Specialist

@Database(
    entities = [Patient::class, Specialist::class, Doctor::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun patientDAO(): PatientDAO
    abstract fun specialistDAO(): SpecialistDAO
    abstract fun doctorDAO(): DoctorDAO

    companion object {

        fun getDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "project003_app_db")
                .allowMainThreadQueries()
                .build()
        }
    }
}