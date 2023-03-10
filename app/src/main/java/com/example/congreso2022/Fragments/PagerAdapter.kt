package com.example.congreso2022.Fragments


import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> { Scaner() }
            1 -> { Codigo() }
            else -> { throw Resources.NotFoundException("Position Not Found")}
        }
    }
}