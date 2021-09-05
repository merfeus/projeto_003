package com.example.projeto_003.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projeto_003.R
import com.example.projeto_003.view_model.PlannerViewModel

class PlannerFragment : Fragment() {

    companion object {
        fun newInstance() = PlannerFragment()
    }

    private lateinit var viewModel: PlannerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.planner_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PlannerViewModel::class.java)
        // TODO: Use the ViewModel
    }

}