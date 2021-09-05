package com.example.projeto_003.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.projeto_003.R
import com.example.projeto_003.adapter.AdapterSpecialist
import com.example.projeto_003.databinding.SpecialistFragmentBinding
import com.example.projeto_003.model.Specialist
import com.example.projeto_003.view_model.SpecialistViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpecialistFragment : Fragment(R.layout.specialist_fragment) {

    companion object {
        fun newInstance() = SpecialistFragment()
    }

    private lateinit var binding: SpecialistFragmentBinding
    private lateinit var viewModel: SpecialistViewModel
    private var selectedSpecialist: Specialist? = null

    private val adapter = AdapterSpecialist {
        selectedSpecialist = it
        binding.bottomNew.visibility = View.GONE
        setValueToFields(it)
    }

    private fun setValueToFields(specialist: Specialist) {
        binding.editNameSpecialist?.setText(specialist.name)
    }

    private val observerSpecialist = Observer<List<Specialist>> {
        adapter.refresh(it)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SpecialistViewModel::class.java)
        binding = SpecialistFragmentBinding.bind(view)
        viewModel.specialist.observe(viewLifecycleOwner, observerSpecialist)
        viewModel.getAllSpecialist()
        settingRecyclerView()
        settingForm()

    }

    private fun settingForm() {
        binding.bottomNew.setOnClickListener {
            val name = binding.editNameSpecialist.text.toString()
            if (name.isNotEmpty()) {
                viewModel.insertSpecialist(
                    Specialist(
                        name = name
                    )
                )
                clearFields()
            }
        }
        binding.bottomDelet.setOnClickListener {
            selectedSpecialist?.let {
                viewModel.deletSpecialist(it)
                clearFields()
            }
        }

        binding.bottomEdit.setOnClickListener {
            selectedSpecialist?.let {
                val name = binding.editNameSpecialist
                if (name.editableText.isNotEmpty()) {
                    viewModel.updateSpecialist(
                        Specialist(
                            name = name.text.toString(),
                            id = selectedSpecialist!!.id
                        )
                    )
                    clearFields()
                }
            }
        }
    }

    private fun clearFields() {
        binding.editNameSpecialist?.setText("")
        binding.bottomNew.visibility = View.VISIBLE
    }

    private fun settingRecyclerView() {
        binding.recyclerViewSpecialist.layoutManager = GridLayoutManager(requireContext(), 1)
        binding.recyclerViewSpecialist.adapter = adapter
    }
}