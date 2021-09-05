package com.example.projeto_003.di

import android.content.Context
import com.example.projeto_003.database.AppDatabase
import com.example.projeto_003.database.dao.DoctorDAO
import com.example.projeto_003.database.dao.PatientDAO
import com.example.projeto_003.database.dao.PlannerDAO
import com.example.projeto_003.database.dao.SpecialistDAO
import com.example.projeto_003.respository.DoctorRepository
import com.example.projeto_003.respository.PatientRepository
import com.example.projeto_003.respository.SpecialistRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    //Injected Repository

    @Provides
    fun providePatientRepository(@ApplicationContext context: Context): PatientRepository {
        return PatientRepository(providePatientDataBase(context))
    }

    @Provides
    fun provideSpecialistRepository(@ApplicationContext context: Context) : SpecialistRepository{
        return SpecialistRepository(provideSpecialistDataBase(context))
    }

    @Provides
    fun providesDoctorRepository(@ApplicationContext context: Context) : DoctorRepository{
        return DoctorRepository(provideDoctorDataBase(context))
    }

    //Injected DAO

    @Provides
    fun providePatientDataBase(@ApplicationContext context: Context): PatientDAO {
        return AppDatabase.getDatabase(context).patientDAO()
    }

    @Provides
    fun provideSpecialistDataBase(@ApplicationContext context: Context): SpecialistDAO{
        return AppDatabase.getDatabase(context).specialistDAO()
    }

    @Provides
    fun provideDoctorDataBase(@ApplicationContext context: Context) : DoctorDAO{
        return AppDatabase.getDatabase(context).doctorDAO()
    }

    @Provides
    fun providePlannerDataBase(@ApplicationContext context: Context): PlannerDAO{
        return AppDatabase.getDatabase(context).plannerDAO()
    }
}