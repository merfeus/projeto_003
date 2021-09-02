package com.example.projeto_003.di

import android.content.Context
import com.example.projeto_003.database.AppDatabase
import com.example.projeto_003.database.dao.PatientDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Provides
    fun provideGitDataBase(@ApplicationContext context: Context): PatientDAO {
        return AppDatabase.getDatabase(context).patientDAO()
    }
}