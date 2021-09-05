package com.example.projeto_003.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projeto_003.R
import com.example.projeto_003.databinding.ItemSpecialistBinding
import com.example.projeto_003.model.Specialist

class AdapterSpecialist(val itemClick: (Specialist) -> Unit) :
    RecyclerView.Adapter<SpecialistViewHolder>() {

    private var listOfSpecialist = mutableListOf<Specialist>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialistViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.item_specialist, parent, false).let {
                SpecialistViewHolder(it)
            }
    }

    override fun onBindViewHolder(holder: SpecialistViewHolder, position: Int) {
        listOfSpecialist[position].apply {
            holder.bind(this)

            holder.itemView.setOnClickListener {
                itemClick(this)
            }
        }
    }

    override fun getItemCount(): Int = listOfSpecialist.size

    fun refresh(newList: List<Specialist>) {
        listOfSpecialist = mutableListOf()
        listOfSpecialist.addAll(newList)
        notifyDataSetChanged()
    }
}

class SpecialistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemSpecialistBinding.bind(itemView)

    fun bind(specialist: Specialist) {

        binding.idTextView.text = "ID: ${specialist.id.toString()}"
        binding.nameTextView.text ="Name: ${specialist.name}"
    }
}