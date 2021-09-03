package com.example.projeto_003.view_model

import androidx.lifecycle.ViewModel
import com.example.projeto_003.respository.SpecialistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SpecialistViewModel @Inject constructor(private val specialistRepository: SpecialistRepository) :
    ViewModel() {

}