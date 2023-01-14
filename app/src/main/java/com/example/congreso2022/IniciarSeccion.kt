package com.example.congreso2022

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class IniciarSeccion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar_seccion)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        val user= findViewById<EditText>(R.id.txt_Usuario)
        val pass= findViewById<EditText>(R.id.txt_Contraseña)

        val btn_Ingresar= findViewById<Button>(R.id.btn_Ingresar)

        btn_Ingresar.setOnClickListener {
            if(user.text.isNotEmpty() && pass.text.isNotEmpty()){
                if (user.text.toString()=="admin" && pass.text.toString() =="admin"){

                    val intent = Intent(this, MenuInicio::class.java) //Inicio rompe la app, checar eso
                    startActivity(intent)
                    Toast.makeText(this, "Autentifacion exitosa", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this, "Error de Usuario y/o Contraseña", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this, "Campos vacios", Toast.LENGTH_SHORT).show()
            }
        }
    }
}