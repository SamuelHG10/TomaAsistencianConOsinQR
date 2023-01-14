package com.example.congreso2022

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        val btnInicioSeccion = findViewById<Button>(R.id.btnIniciarSeccion)

        btnInicioSeccion.setOnClickListener {
            val intent = Intent(this, IniciarSeccion::class.java)
            startActivity(intent)
        }
    }
}