package com.example.projeto_003.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projeto_003.R
import com.example.projeto_003.databinding.ItemDoctorBinding
import com.example.projeto_003.model.DoctorWithSpecialist

class AdapterDoctor(private val itemClick: (DoctorWithSpecialist) -> Unit) :
    RecyclerView.Adapter<DoctorViewHolder>() {

    private var listOfDoctorWithSpecialist = mutableListOf<DoctorWithSpecialist>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.item_doctor, parent, false).let {
                DoctorViewHolder(it)
            }
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        listOfDoctorWithSpecialist[position].apply {
            holder.bind(this)
            holder.itemView.setOnClickListener {
                itemClick(this)
            }
        }
    }

    override fun getItemCount(): Int = listOfDoctorWithSpecialist.size

    fun refresh(newList: List<DoctorWithSpecialist>) {
        listOfDoctorWithSpecialist = mutableListOf()
        listOfDoctorWithSpecialist.addAll(newList)
        notifyDataSetChanged()
    }
}

class DoctorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemDoctorBinding.bind(itemView)

    fun bind(doctorWithSpecialist: DoctorWithSpecialist) {

        binding.nameTextView.text = doctorWithSpecialist.doctor?.name
        binding.specialistDoctor.text = doctorWithSpecialist.specialist?.name
    }

}