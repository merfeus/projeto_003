package com.example.projeto_003.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.projeto_003.database.dao.PatientDAO
import com.example.projeto_003.model.Patient

@Database(
    entities = [Patient::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun patientDAO(): PatientDAO

    companion object {

        fun getDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "project003_app_db")
                .allowMainThreadQueries()
                .build()
        }
    }
}