package com.example.projeto_003.view

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.projeto_003.R
import com.example.projeto_003.adapter.AdapterDoctor
import com.example.projeto_003.databinding.DoctorFragmentBinding
import com.example.projeto_003.model.Doctor
import com.example.projeto_003.model.DoctorWithSpecialist
import com.example.projeto_003.model.Specialist
import com.example.projeto_003.view_model.DoctorViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoctorFragment : Fragment(R.layout.doctor_fragment) {

    companion object {
        fun newInstance() = DoctorFragment()
    }

    private lateinit var viewModel: DoctorViewModel
    private lateinit var binding: DoctorFragmentBinding
    private lateinit var adapterSpinner: ArrayAdapter<String>

    private var selectedSpecialist: Specialist? = null
    private var selectedDoctor: DoctorWithSpecialist? = null


    private val adapter: AdapterDoctor = AdapterDoctor {
        setValueToFields(it)
    }

    private val observerDoctor = Observer<List<DoctorWithSpecialist>> {
        adapter.refresh(it)
    }

    private val observerSpecialist = Observer<List<Specialist>> {
        val listOf = it.map { category ->
            category.name
        }
        adapterSpinner.addAll(listOf)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DoctorViewModel::class.java)
        binding = DoctorFragmentBinding.bind(view)
        setupRecyclerView()
        startObservers()
        startViewModelFuns()
        setupForm()
        setupAutoComplete()
    }

    private fun startViewModelFuns() {
        viewModel.getAllDoctor()
        viewModel.getSpecialist()
    }

    private fun startObservers() {
        viewModel.doctor.observe(viewLifecycleOwner, observerDoctor)
        viewModel.specialist.observe(viewLifecycleOwner, observerSpecialist)
    }

    private fun setupForm() {

        binding.bottomNewDoctor.setOnClickListener {
            val name = binding.editNameDoctor.editText?.text.toString()
            if (name.isNotEmpty() && selectedSpecialist != null) {
                viewModel.insertDoctor(
                    Doctor(
                        name = name,
                        specialityFK = selectedSpecialist!!.id
                    )
                )
                clearFields()
            }
        }

        binding.bottomDeletDoctor.setOnClickListener {
            selectedDoctor?.doctor?.let {
                viewModel.deletDoctor(it)
            }
            clearFields()
        }

        binding.bottomEditDoctor.setOnClickListener {
            val name = binding.editNameDoctor.editText?.text
            if (name.toString().isNotEmpty() && selectedSpecialist != null) {
                viewModel.updateDoctor(
                    Doctor(
                        name = name.toString(),
                        specialityFK = selectedSpecialist!!.id
                    )
                )
                clearFields()
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerViewDoctor.layoutManager = GridLayoutManager(requireContext(), 1)
        binding.recyclerViewDoctor.adapter = adapter
    }


    fun setValueToFields(doctorWithSpecialist: DoctorWithSpecialist) {
        binding.editNameDoctor.editText?.setText(doctorWithSpecialist.doctor?.name)
        binding.bottomNewDoctor.visibility = View.GONE

        selectedDoctor = doctorWithSpecialist
        selectedSpecialist = doctorWithSpecialist.specialist
    }


    private fun setupAutoComplete() {
        adapterSpinner =
            ArrayAdapter<String>(requireContext(), R.layout.spinner_item_specialist)
        val autoCompleteBrand: AutoCompleteTextView? =
            binding.inputCategoryTextInputLayout.editText as? AutoCompleteTextView
        autoCompleteBrand?.setAdapter(adapterSpinner)
        autoCompleteBrand?.setOnItemClickListener { parent, view, position, id ->
            val selected = parent.getItemAtPosition(position) as String
            viewModel.specialist.value?.first { cat -> (cat.name.equals(selected, true)) }
                ?.let {
                    selectedSpecialist = it
                }
        }
    }

    fun clearFields() {
        binding.editNameDoctor.editText?.setText("")
        binding.inputCategoryTextInputLayout.editText?.setText("")
        binding.bottomNewDoctor.visibility = View.VISIBLE

        selectedSpecialist = null
        selectedDoctor = null
    }
}


