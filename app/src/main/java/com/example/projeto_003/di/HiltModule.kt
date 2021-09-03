package com.example.projeto_003.di

import android.content.Context
import com.example.projeto_003.database.AppDatabase
import com.example.projeto_003.database.dao.PatientDAO
import com.example.projeto_003.database.dao.SpecialistDAO
import com.example.projeto_003.respository.PatientRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Provides
    fun providePatientRepository(@ApplicationContext context: Context): PatientRepository {
        return PatientRepository(providePatientDataBase(context))
    }

    @Provides
    fun providePatientDataBase(@ApplicationContext context: Context): PatientDAO {
        return AppDatabase.getDatabase(context).patientDAO()
    }

    @Provides
    fun provideSpecialist(@ApplicationContext context: Context): SpecialistDAO{
        return AppDatabase.getDatabase(context).specialistDAO()
    }
}