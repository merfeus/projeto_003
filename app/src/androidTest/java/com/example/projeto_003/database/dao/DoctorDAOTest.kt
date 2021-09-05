package com.example.projeto_003.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.projeto_003.database.AppDatabase
import com.example.projeto_003.model.Doctor
import com.example.projeto_003.model.Specialist
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@SmallTest
class DoctorDAOTest {

    private lateinit var database: AppDatabase
    private lateinit var daoPhysician: DoctorDAO
    private lateinit var daoSpeciality: SpecialistDAO

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        daoSpeciality = database.specialistDAO()
        daoPhysician = database.doctorDAO()
    }

    @After
    fun teardown() {
        database.close()
    }


    @Test
    fun testing_insert() {
        val s1 = Specialist(1, "s1")
        val s2 = Specialist(2, "s2")
        daoSpeciality.insert(listOf(s1, s2))

        val p1 = Doctor(name = "Jaime", specialityFK = s2.id)
        val listToInsert = arrayListOf(p1)
        daoPhysician.insertDoctor(
            Doctor(
                name = "Jaime", specialityFK = s2.id,
            )
        )
        val results = daoPhysician.getDoctor()
        assertThat(results).hasSize(listToInsert.size)
    }

    @Test
    fun testing_delete() {
        val s1 = Specialist(1, "s1")
        val s2 = Specialist(2, "s2")
        daoSpeciality.insert(listOf(s1, s2))

        val p1 = Doctor(id = 1, name = "Jaime", specialityFK = s2.id)

        daoPhysician.insertDoctor(p1)

        daoPhysician.deletDoctor(p1)

        val result = daoPhysician.getDoctor()
        assertThat(result).doesNotContain(p1)
    }

    @Test
    fun testing_update(){
        val s1 = Specialist(1, "s1")
        val s2 = Specialist(2, "s2")
        daoSpeciality.insert(listOf(s1, s2))

        val p1 = Doctor(id = 1, name = "Paulo", specialityFK = s1.id)
        daoPhysician.insertDoctor(p1)

        val result = daoPhysician.getDoctor()
        assertThat(result)
    }
}