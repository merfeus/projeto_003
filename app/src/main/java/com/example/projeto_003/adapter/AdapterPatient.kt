package com.example.projeto_003.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projeto_003.R
import com.example.projeto_003.databinding.ItemPatienteBinding
import com.example.projeto_003.model.Patient

class AdapterPatient(val itemClick: (Patient) -> Unit) : RecyclerView.Adapter<PatientViewHolder>() {

    private var listOfPacient = mutableListOf<Patient>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.item_patiente, parent, false).let {
                PatientViewHolder(it)
            }
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        listOfPacient[position].apply {
            holder.bind(this)
            holder.itemView.setOnClickListener {
                itemClick(this)
            }
        }
    }

    override fun getItemCount(): Int = listOfPacient.size

    fun refresh(newList: List<Patient>) {
        listOfPacient = mutableListOf()
        listOfPacient.addAll(newList)
        notifyDataSetChanged()
    }
}

class PatientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemPatienteBinding.bind(itemView)

    fun bind(patient: Patient) {

        binding.idTextView.text = patient.id.toString()
        binding.nameTextView.text = patient.name
        binding.ageTextView.text = patient.age.toString()
        binding.sexTextView.text = patient.sex
    }
}