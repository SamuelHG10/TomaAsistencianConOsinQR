package com.example.congreso2022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

class SinGafete2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sin_gafete2)

        val btn_Guardar = findViewById<Button>(R.id.btn_GuardarNoControl)
        val txt_NoControl = findViewById<EditText>(R.id.txt_NoControl)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        btn_Guardar.setOnClickListener(){
            if (txt_NoControl.text.isNotEmpty()){
                implementacion(txt_NoControl.text.toString())
            }
        }
    }

    private fun implementacion(result : String){
        // Definir ruta del archivo
        val nombreArchivo = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/" + "$result.txt"
        Toast.makeText(this, "Guardando en $nombreArchivo", Toast.LENGTH_SHORT).show()
        val file = File(nombreArchivo)
        if (!file.exists()) {
            file.createNewFile()
        }
        val fileWriter = FileWriter(file)
        val bufferedWriter = BufferedWriter(fileWriter)
        bufferedWriter.write(result)// <-- Aquí el contenido
        bufferedWriter.newLine()
        bufferedWriter.flush()
        bufferedWriter.close()
    }
}