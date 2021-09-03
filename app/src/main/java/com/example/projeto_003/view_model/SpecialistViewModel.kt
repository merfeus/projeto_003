package com.example.projeto_003.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projeto_003.model.Specialist
import com.example.projeto_003.respository.SpecialistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SpecialistViewModel @Inject constructor(private val specialistRepository: SpecialistRepository) :
    ViewModel() {

    private val _SPECI = MutableLiveData<List<Specialist>>()
    val specialist: LiveData<List<Specialist>> = _SPECI

    fun getAllSpecialist() {
        _SPECI.value = specialistRepository.getSpecialist()
    }

    fun insertSpecialist(specialist: Specialist) {
        specialistRepository.insertSpecialist(specialist)
        getAllSpecialist()
    }

    fun updateSpecialist(specialist: Specialist) {
        specialistRepository.updateSpecialist(specialist)
        getAllSpecialist()
    }

    fun deletSpecialist(specialist: Specialist) {
        specialistRepository.deletPatient(specialist)
        getAllSpecialist()
    }
}
