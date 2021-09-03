package com.example.projeto_003.utils

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.projeto_003.R

fun FragmentActivity.replaceFragment(fragment: Fragment, @IdRes containerId: Int = R.id.container){
    supportFragmentManager.beginTransaction()
        .replace(containerId, fragment)
        .commitNow()
}