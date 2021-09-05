package com.example.projeto_003.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.projeto_003.database.AppDatabase
import com.example.projeto_003.model.Patient
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class PatientDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: PatientDAO

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.patientDAO()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun testing_insert_patient_should_returns_true() {
        val newPatient1 = Patient(name = "Pac1", age = 10, gender = "M")
        val newPatient2 = Patient(name = "Pac2", age = 22, gender = "F")
        val listToInsert = arrayListOf(newPatient1, newPatient2)
        dao.insert(listToInsert)

        val results = dao.getPatient()
        assertThat(results).hasSize(listToInsert.size)
    }

    @Test
    fun testing_fetch_by_gender() {
        val newPatient1 = Patient(name = "Pac1", age = 10, gender = "M")
        val newPatient2 = Patient(name = "Pac2", age = 22, gender = "F")
        val newPatient3 = Patient(name = "Pac3", age = 22, gender = "Other")
        val listToInsert = arrayListOf(newPatient1, newPatient2, newPatient3)
        dao.insert(listToInsert)

        val results = dao.fetch("F")
        assertThat(results).hasSize(1)
    }

    @Test
    fun testing_fetch_by_id() {
        val newPatient1 = Patient(id = 1, name = "Pac1", age = 10, gender = "M")
        val newPatient2 = Patient(id = 2, name = "Pac2", age = 22, gender = "F")
        val newPatient3 = Patient(id = 3, name = "Pac3", age = 22, gender = "Other")
        val listToInsert = arrayListOf(newPatient1, newPatient2, newPatient3)
        dao.insert(listToInsert)

        val result = dao.fetch(2)
        assertThat(result.name).isEqualTo(newPatient2.name)
    }

    @Test
    fun testing_update() {
        val newPatient1 = Patient(id = 1, name = "Pac1", age = 10, gender = "M")
        val newPatient2 = Patient(id = 2, name = "Pac2", age = 22, gender = "F")
        val newPatient3 = Patient(id = 3, name = "Pac3", age = 22, gender = "Other")
        val listToInsert = arrayListOf(newPatient1, newPatient2, newPatient3)
        dao.insert(listToInsert)

        val patientForUpdate = Patient(
            id = newPatient2.id,
            name = "Pac2Updated",
            age = 23,
            gender = "M"
        )
        dao.update(patientForUpdate)

        val result = dao.fetch(newPatient2.id)
        assertThat(result.name).isEqualTo(patientForUpdate.name)
        assertThat(result.age).isEqualTo(patientForUpdate.age)
        assertThat(result.gender).isEqualTo(patientForUpdate.gender)
    }

    @Test
    fun testing_delete() {
        val newPatient1 = Patient(id = 1, name = "P1", age = 10, gender = "M")
        val newPatient2 = Patient(id = 2, name = "P2", age = 22, gender = "F")
        val newPatient3 = Patient(id = 3, name = "P3", age = 22, gender = "M")
        val listToInsert = arrayListOf(newPatient1, newPatient2, newPatient3)
        dao.insert(listToInsert)

        dao.delete(newPatient2)

        val result = dao.getPatient()
        assertThat(result).doesNotContain(newPatient2)
    }
}