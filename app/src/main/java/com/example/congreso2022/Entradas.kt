package com.example.congreso2022

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.BoringLayout
import android.view.View
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2
import com.example.congreso2022.Fragments.PagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class Entradas : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entradas)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewpager)

        viewPager.adapter = PagerAdapter(this)
        TabLayoutMediator(tabLayout,viewPager){ tab,index ->
            tab.text = when(index) {
                0 -> { "Scaner" }
                1 -> { "Codigo" }
                else -> { throw Resources.NotFoundException("Position Not Found")}
            }
        }.attach()




    }
}