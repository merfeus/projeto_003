package com.example.projeto_003

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projeto_003.databinding.MainActivityBinding
import com.example.projeto_003.utils.replaceFragment
import com.example.projeto_003.view.DoctorFragment
import com.example.projeto_003.view.PatientFragment
import com.example.projeto_003.view.PlannerFragment
import com.example.projeto_003.view.SpecialistFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        settingBottomBar()
        settingInitFrag()
    }

    private fun settingBottomBar() {
        binding.bottomBarNav.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.nav_patients -> {
                    replaceFragment(PatientFragment.newInstance())
                }
                R.id.nav_specialists -> {
                    replaceFragment(SpecialistFragment.newInstance())
                }
                R.id.nav_doctors -> {
                    replaceFragment(DoctorFragment.newInstance())
                }

                R.id.nav_planner -> {
                    replaceFragment(PlannerFragment.newInstance())
                }
            }
            true
        }
    }

    private fun settingInitFrag() {
        replaceFragment(PatientFragment.newInstance())
    }
}