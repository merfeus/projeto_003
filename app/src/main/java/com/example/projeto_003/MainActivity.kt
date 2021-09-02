package com.example.projeto_003

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projeto_003.view.PatientFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, PatientFragment.newInstance())
            .commitNow()
    }
}