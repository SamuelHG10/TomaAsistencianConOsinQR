package com.example.congreso2022

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.congreso2022.databinding.ActivityLectorQr3Binding
import com.google.zxing.integration.android.IntentIntegrator
import java.io.*
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE as WRITE_EXTERNAL_STORAGE

class LectorQr3 : AppCompatActivity() {

    private lateinit var binding: ActivityLectorQr3Binding
    private val CODIGO_PERMISO_ALMACENAMIENTO = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLectorQr3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnScanner.setOnClickListener { initScanner() }

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        val btnManual = findViewById<Button>(R.id.btnManual)
        btnManual.setOnClickListener {
            val intent = Intent(this, SinGafete2::class.java)
            startActivity(intent)
        }

    }

    private fun initScanner() {
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("Escaando el código...")
        integrator.setTorchEnabled(false)
        integrator.setBeepEnabled(true)
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "El valor escaneado es: " + result.contents, Toast.LENGTH_LONG).show()
                //implementacion(result.contents.toString())  //para guardar en notas
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

///////////////////////////////////////////////////////////////
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (grantResults.isEmpty()) {
            return
        }

        if (requestCode == CODIGO_PERMISO_ALMACENAMIENTO) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Aquí se ha concedido el permiso
            } else {
                Toast.makeText(this, "Permiso de almacenamiento requerido", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
    private fun verificarYPedirPermisosDeAlmacenamiento() {
        val estadoDePermiso =
            ContextCompat.checkSelfPermission(
                this@LectorQr3,
                WRITE_EXTERNAL_STORAGE
            )
        if (estadoDePermiso == PackageManager.PERMISSION_GRANTED) {
            // Aquí se ha concedido el permiso
        } else {
            // Si no, pedimos permisos. Ahora mira onRequestPermissionsResult
            ActivityCompat.requestPermissions(
                this@LectorQr3,
                arrayOf(WRITE_EXTERNAL_STORAGE),
                CODIGO_PERMISO_ALMACENAMIENTO
            )
        }
    }


    private fun writeCsv(result : String){
        // Definir ruta del archivo
        val nombreArchivo = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/" + "test.csv"
        val file = File(nombreArchivo)
        val fileWriter = FileWriter(file)
        val bufferedWriter = BufferedWriter(fileWriter)
        bufferedWriter.write(result)// <-- Aquí el contenido
        bufferedWriter.newLine()
        bufferedWriter.flush()
        bufferedWriter.close()
    }

    /////////////////
/*
    private fun implementacion(result : String){
        // Definir ruta del archivo
        val nombreArchivo = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/" + "test.csv"
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


    private fun implementacion2(result : String){
        // Definir ruta del archivo
        val nombreArchivo = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/" + "test.csv"
        val file = File(nombreArchivo)
        val fileWriter = FileWriter(file)
        val bufferedWriter = BufferedWriter(fileWriter)
        bufferedWriter.write(result)// <-- Aquí el contenido
        bufferedWriter.newLine()
        bufferedWriter.flush()
        bufferedWriter.close()
    }


    /////////
    private fun existeArchivo(archivos:Array<String>, archivo: String):Boolean{
        archivos.forEach {
            if (archivo == it){
                return true
            }
        }
        return false
    }

    private fun leerArchivo(){
        if(existeArchivo(fileList(),"test.csv")){
            var contenido = ""
            val archivo = InputStreamReader(openFileInput("test.csv"))
            val bf = BufferedReader(archivo)
            var linea = bf.readLine()
            while (linea!=null){
                contenido = contenido+linea+"\n"
                linea= bf.readLine()
            }
        }
    }

    private fun guardar(result: String){
        val archivo = OutputStreamWriter(openFileOutput("test.csv",Activity.MODE_PRIVATE))
        archivo.write(result)
        archivo.flush()
        archivo.close()
    }


    ////////
    private fun editarArchivo(result : String) {
        val archivo = InputStreamReader(openFileInput("test.csv"))
        val br = BufferedReader(archivo)
        var linea = br.readLine()
        while (linea!=null){
            linea = br.readLine()
        }
        val arch = OutputStreamWriter(openFileOutput("test.csv", Activity.MODE_PRIVATE))
        arch.write(result)
        arch.flush()
        arch.close()
    }*/

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