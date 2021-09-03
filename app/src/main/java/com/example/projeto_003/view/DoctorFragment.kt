package com.example.projeto_003.view

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.projeto_003.R
import com.example.projeto_003.adapter.AdapterDoctor
import com.example.projeto_003.adapter.AdapterSpecialist
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

    private var selectedSpecialist: Specialist? = null
    private var selectedDoctor: DoctorWithSpecialist? = null
    private val adapter: AdapterDoctor = AdapterDoctor {
        setValueToFields(it)
    }
    private val adapterSpecialist = AdapterSpecialist{
        selectedSpecialist = it
        binding.editSpecialistDoctor.visibility = View.VISIBLE
        binding.bottomNew.visibility = View.GONE
    }

    private val observerDoctor = Observer<List<DoctorWithSpecialist>> {
        adapter.refresh(it)
    }

    private val observerSpecialist = Observer<List<Specialist>> {
        adapterSpecialist.refresh(it)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DoctorViewModel::class.java)
        binding = DoctorFragmentBinding.bind(view)
        setupRecyclerView()
        setupForm()
        startObserver()
        startViewModelFuns()
    }

    private fun startViewModelFuns() {
        viewModel.getAllDoctor()
        viewModel.getSpecialist()
    }

    private fun startObserver() {
        viewModel.doctor.observe(viewLifecycleOwner, observerDoctor)
        viewModel.specialist.observe(viewLifecycleOwner, observerSpecialist)
    }

    private fun setupForm() {

        binding.bottomNew.setOnClickListener {
            val name = binding.editNameDoctor.text.toString()
            if (name.isNotEmpty() && selectedSpecialist != null) {
                val doctor = Doctor(
                    name = name,
                    specialityFK = selectedSpecialist!!.id
                )
                viewModel.insertDoctor(doctor = doctor)
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerViewDoctor.layoutManager = GridLayoutManager(requireContext(), 1)
        binding.recyclerViewDoctor.adapter = adapter
    }


    fun setValueToFields(doctorWithSpecialist: DoctorWithSpecialist) {
        binding.editNameDoctor?.setText(doctorWithSpecialist.doctor?.name)
        binding.bottomNew.visibility = View.GONE

        selectedDoctor = doctorWithSpecialist
        selectedSpecialist = doctorWithSpecialist.specialist
    }

}


