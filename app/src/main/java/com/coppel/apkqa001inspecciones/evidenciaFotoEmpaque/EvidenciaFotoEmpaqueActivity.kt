package com.coppel.apkqa001inspecciones.evidenciaFotoEmpaque

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.coppel.apkqa001inspecciones.R
import com.coppel.apkqa001inspecciones.databinding.ActivityEncabezadoBinding
import com.coppel.apkqa001inspecciones.databinding.ActivityEvidenciaFotoEmpaqueBinding

class EvidenciaFotoEmpaqueActivity : AppCompatActivity() {
    //private lateinit var imageView: ImageView

    private lateinit var binding: ActivityEvidenciaFotoEmpaqueBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEvidenciaFotoEmpaqueBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = getString(R.string.inspeccion_loads_name_app)

        //imageView = findViewById(R.id.foto)

        val iv_click_me = findViewById(R.id.evidencia_foto) as ImageView
        iv_click_me.setOnClickListener {
            //Toast.makeText(this, "You clicked on ImageView.", Toast.LENGTH_SHORT).show()
            startForResult.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        }
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK){
            val intent = result.data
            val imageBitmap = intent?.extras?.get("data") as Bitmap
            val imageView = findViewById<ImageView>(R.id.foto_img)
            imageView.setImageBitmap(imageBitmap)
        }
    }

}