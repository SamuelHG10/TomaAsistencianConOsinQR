package com.example.congreso2022

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MenuInicio : AppCompatActivity() {
    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_inicio)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        val btn_Entrada = findViewById<Button>(R.id.btn_Entradas)
        val btn_Salir = findViewById<Button>(R.id.btn_Salir)

        btn_Entrada.setOnClickListener {
            val intent1 = Intent(this, LectorQr3::class.java)
            startActivity(intent1)
        }

        btn_Salir.setOnClickListener {
            val intent = Intent(this, IniciarSeccion::class.java)
            startActivity(intent)
        }

    }
    override fun onBackPressed() {
        return
    }
}