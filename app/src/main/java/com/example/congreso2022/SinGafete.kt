package com.example.congreso2022

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Message
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.content.contentValuesOf
import com.example.congreso2022.databinding.ActivitySinGafeteBinding
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.activity_sin_gafete.*
import java.io.File
import java.io.OutputStream

class SinGafete : AppCompatActivity() {

    private lateinit var cameraBtn: MaterialButton
    private lateinit var galleryBtn: MaterialButton
    private lateinit var imageIv: ImageView
    private lateinit var scanBtn: MaterialButton
    private lateinit var resultTv: TextView

    companion object{
        private const val CAMERA_REQUEST_CODE =100
        private const val STORAGE_REQUEST_CODE =101

        private lateinit var storegePermissions: Array<String>

        private var imageUri : Uri? = null

        private const val TAG ="MAIN_TAG"
    }

    private lateinit var cameraPermissions: Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        cameraBtn = findViewById(R.id.cameraBtn)
        galleryBtn = findViewById(R.id.galleyBtn)
        imageIv = findViewById(R.id.imageIv)
        scanBtn = findViewById(R.id.scanBtn)
        resultTv = findViewById(R.id.resultTv)

        cameraPermissions = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        storegePermissions = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

        cameraBtn.setOnClickListener(){
            if (checkCameraPermissions()){
                pickImageCamera()
            }
            else{
                requestCameraPermission()
            }
        }

        galleryBtn.setOnClickListener {

            if (checkStoragePermission()){
                pickImageGallery()
            } else {
                resquestStoragePermission()
            }
        }


    }

    private  fun pickImageGallery(){

        val intent = Intent(Intent.ACTION_PICK)

        intent.type = "image/*"
        galleryActivityResultLauncher.launch(intent)
    }

    private val galleryActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){result ->
        if (result.resultCode == Activity.RESULT_OK){
            val data = result.data

            imageUri = data?.data
            Log.d(TAG, ": imageUri: $imageUri")

            imageIv.setImageURI(imageUri)
        }
        else{
            showToast("Cancelado...")
        }
    }

    private fun pickImageCamera(){
        val contentValues = ContentValues()
        contentValues.put(MediaStore.Images.Media.TITLE,"Sample Image")
        contentValues.put(MediaStore.Images.Media.DESCRIPTION,"Sample Image Description")

        imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
    }

    private val cameraActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){result ->
        if (result.resultCode == Activity.RESULT_OK){
            val data = result.data
            Log.d(TAG,"cameraActivityResultLauncher : imageUri: $imageUri")

            imageIv.setImageURI(imageUri)
        }
    }

    private fun checkStoragePermission(): Boolean{

        val result = (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
        return result
    }

    private fun resquestStoragePermission(){
        ActivityCompat.requestPermissions(this, storegePermissions, STORAGE_REQUEST_CODE)
    }

    private fun checkCameraPermissions():Boolean{

        val resultCamera = (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED)
        val resutSore= (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED)

        return resultCamera && resutSore
    }

    private fun requestCameraPermission(){
        ActivityCompat.requestPermissions(this, cameraPermissions, CAMERA_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){
            CAMERA_REQUEST_CODE->{
                if (grantResults.isEmpty()){

                    val cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED
                    val storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED

                    if (cameraAccepted && storageAccepted){
                        pickImageCamera()
                    }
                    else{
                        showToast("Camera & Storage permission are requiered")
                    }
                }
            }
            STORAGE_REQUEST_CODE->{
                if (grantResults.isEmpty()){
                    val storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED

                    if (storageAccepted){
                        pickImageGallery()
                    }
                    else{
                        showToast("Storage permission are requiered")
                    }
                }
            }
        }
    }

    private fun showToast(message: String){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

}