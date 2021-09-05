package com.example.projeto_003.view

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
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
        binding.bottomNew.visibility = GONE
        setValueToFields(it)
    }

    private fun setValueToFields(patient: Patient) {
        binding.editNamePatient?.setText(patient.name)
        binding.editSexPatient?.setText(patient.gender)
        binding.editAgePatient?.setText(patient.age.toString())
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

            val name = binding.editNamePatient
            val age = binding.editAgePatient
            val sex = binding.editSexPatient

            if (name.editableText.isNotEmpty() && age.text.toString()
                    .isNotEmpty() && sex.text.toString().isNotEmpty()
            ) {
                viewModel.insertPatient(
                    Patient(
                        name = name.text.toString(),
                        age = age.text.toString().toInt(),
                        gender = sex.text.toString()
                    )
                )
                clearFields()
            }

            binding.bottomDelet.setOnClickListener {
                selectedPatient?.let {
                    viewModel.deletPatient(it)
                    clearFields()
                }
            }

            binding.bottomEdit.setOnClickListener {
                selectedPatient?.let {

                    val name = binding.editNamePatient
                    val age = binding.editAgePatient
                    val sex = binding.editSexPatient

                    if (name.editableText.isNotEmpty() && age.text.toString()
                            .isNotEmpty() && sex.text.toString().isNotEmpty()
                    ) {
                        viewModel.updatePatient(
                            Patient(
                                id = selectedPatient!!.id,
                                name = name.text.toString(),
                                age = age.text.toString().toInt(),
                                gender = sex.text.toString()
                            )
                        )
                        clearFields()
                    }
                }
            }
        }
    }

    private fun clearFields() {
        binding.editNamePatient?.setText("")
        binding.editAgePatient?.setText("")
        binding.editSexPatient?.setText("")
        binding.editAgePatient?.setText("")
        binding.bottomNew.visibility = VISIBLE
    }

    fun settingRecyclerView() {
        binding.recyclerViewPatient.layoutManager = GridLayoutManager(requireContext(), 1)
        binding.recyclerViewPatient.adapter = adapter
    }

}