package com.example.projeto_003.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.projeto_003.R
import com.example.projeto_003.adapter.AdapterPatient
import com.example.projeto_003.databinding.PatientFragmentBinding
import com.example.projeto_003.model.Patient
import com.example.projeto_003.view_model.PatientViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PatientFragment : Fragment(R.layout.patient_fragment) {

    private lateinit var binding: PatientFragmentBinding

    companion object {
        fun newInstance() = PatientFragment()
    }

    private lateinit var viewModel: PatientViewModel
    private var selectedPatient: Patient? = null

    private val adapter = AdapterPatient {
        selectedPatient = it
        setValueToFields(it)
    }

    private val observerPatient = Observer<List<Patient>> {
        adapter.refresh(it)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(PatientViewModel::class.java)
        binding = PatientFragmentBinding.bind(view)
        viewModel.patient.observe(viewLifecycleOwner, observerPatient)
        viewModel.getAllPatient()
        settingRecyclerView()
        settingForm()


    }

    private fun settingForm() {
        binding.bottomNew.setOnClickListener {
            binding.editNamePatient.let { edit ->
                if (edit.text.isNotEmpty()) {
                    Patient(name = edit.text.toString()).let {
                        viewModel.inserPatient(it)
                    }
                }
            }
        }
        binding.bottomDelet.setOnClickListener {
            selectedPatient?.let {
                viewModel.deletPatient(it)
            }
        }
        binding.bottomEdit.setOnClickListener {
            selectedPatient?.let {

                binding.editNamePatient?.let { edit ->
                    if (edit.text.isNotEmpty()) {
                        Patient(
                            id = it.id,
                            name = edit.text.toString()
                        ).let {
                            viewModel.updatePatient(it)
                        }
                    }
                }
            }
        }
    }

    fun settingRecyclerView() {
        binding.recyclerViewPatient.layoutManager = GridLayoutManager(requireContext(), 1)
        binding.recyclerViewPatient.adapter = adapter
    }

    fun setValueToFields(patient: Patient) {
        binding.editAgePatient?.setText(patient.id)
        binding.editIdPatient?.setText(patient.name)
        binding.editNamePatient?.setText(patient.age)
        binding.editSexPatient?.setText(patient.sex)

    }

}