package com.example.projeto_003.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    companion object{
        fun getDatabase(){

        }
    }
}