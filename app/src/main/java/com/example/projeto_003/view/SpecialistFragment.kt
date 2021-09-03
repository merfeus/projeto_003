package com.example.projeto_003.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projeto_003.R
import com.example.projeto_003.view_model.SpecialistViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpecialistFragment : Fragment(R.layout.specialist_fragment) {

    companion object {
        fun newInstance() = SpecialistFragment()
    }

    private lateinit var viewModel: SpecialistViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SpecialistViewModel::class.java)

    }
}