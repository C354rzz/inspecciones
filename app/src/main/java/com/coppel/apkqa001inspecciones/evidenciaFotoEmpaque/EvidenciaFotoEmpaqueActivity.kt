package com.coppel.apkqa001inspecciones.evidenciaFotoEmpaque

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.view.isVisible
import com.coppel.apkqa001inspecciones.BuildConfig
import com.coppel.apkqa001inspecciones.R
import com.coppel.apkqa001inspecciones.databinding.ActivityEvidenciaFotoEmpaqueBinding
import com.coppel.apkqa001inspecciones.evidenciaFotoEmpaque.viewModel.EvidenciaFotoEmpaqueViewModel
import com.mikepenz.iconics.Iconics
import java.io.File
import java.io.OutputStream
import java.util.*
import java.util.logging.Logger
import kotlin.collections.ArrayList

class EvidenciaFotoEmpaqueActivity : AppCompatActivity() {
    private val permissions = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.INTERNET,
        Manifest.permission.ACCESS_NETWORK_STATE
    )

    private lateinit var file: File
    private lateinit var binding: ActivityEvidenciaFotoEmpaqueBinding
    private val permissionRequestCode = 479
    private val vieModel: EvidenciaFotoEmpaqueViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEvidenciaFotoEmpaqueBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = getString(R.string.inspeccion_loads_name_app)

        /*val iv_click_me = findViewById(R.id.iv_tomar_foto) as ImageView
        iv_click_me.setOnClickListener {
            //Toast.makeText(this, "click en tomar foto.", Toast.LENGTH_SHORT).show()

            startForResult.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        }*/
        var spath: String = Environment.getExternalStorageDirectory().absolutePath
        var fullpath = File(spath + File.separator + Environment.DIRECTORY_PICTURES + File.separator + "dirInspSku")
        folderReader(fullpath)

        //initRecyclerView(list)

        //Log.e("armijoMensaje", spath)
        //Log.e("armijoMensaje", fullpath.toString())

        binding.ivTomarFoto.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
                it.resolveActivity(packageManager).also { component ->
                    createPhotoFile()
                    val photoUri: Uri =
                        FileProvider.getUriForFile(
                            Objects.requireNonNull(getApplicationContext()),
                            BuildConfig.APPLICATION_ID + ".fileprovider", file
                        )
                    it.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                }
            }
            openCamera.launch(intent)
            //openCamera.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        }

        binding.btnContinuar2.setOnClickListener{
            saveToGalery()
        }
    }

    private fun folderReader(root: File){
        val fileList: ArrayList<File> = ArrayList()
        val listAllFiles = root.listFiles()

        if (listAllFiles != null && listAllFiles.size > 0) {
            for (currentFile in listAllFiles) {
                if (currentFile.name.endsWith(".jpg")) {
                    // File absolute path
                    //Log.e("jpgPath", currentFile.getAbsolutePath())
                    // File Name
                    Log.i("armijo_jpgName", currentFile.getName())
                    fileList.add(currentFile.absoluteFile)
                }
            }
            //Log.w("fileList", "" + fileList)
            //return fileList
        }
    }


    private fun isLoading(enabled: Boolean) {
        binding.progressFotosManual.isVisible = enabled
        binding.blockFotosManual.isVisible = enabled
    }

    //metodo al obtener la imagen de la foto
    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                val imageBitmap = intent?.extras?.get("data") as Bitmap
                val imageView = findViewById<ImageView>(R.id.foto)
                imageView.setImageBitmap(imageBitmap)
            }
        }

    private val openCamera =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                // val data = result.data!!
                // val bitmap = data.extras!!.get("data") as Bitmap
                val bitmap = getBitmap()
                binding.ivFoto.setImageBitmap(bitmap)
            }
        }

    private fun createPhotoFile() {
        val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        file = File.createTempFile("IMG_${System.currentTimeMillis()}_", ".jpg", dir)
    }

    private fun saveToGalery() {
        val content = createContent()
        val uri = save(content)
        clearContents(content, uri)
        Toast.makeText(this, getString(R.string.image_saved), Toast.LENGTH_LONG).show()
    }




    private fun createContent(): ContentValues {
        val fileName = file.name
        val fileType = "image/jpg"
        createPublicDirectory("dirInspSku")
        //Logger.getLogger(EvidenciaFotoEmpaqueActivity::class.java.name).warning(dirInpFoto.toString())
        return ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
            put(MediaStore.Files.FileColumns.MIME_TYPE, fileType)
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES+"/dirInspSku")
            put(MediaStore.MediaColumns.IS_PENDING, 1)
        }
    }

    private fun createPublicDirectory(pathDirFotos: String){
        // Consultas si el usuario ya concedió el permiso
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Si el usuario no ha concedido el permiso, lo solicitas
            ActivityCompat.requestPermissions(this, permissions, permissionRequestCode)
        } else {
            // Si el usuario concedió el permiso,
            //Crear directorio publico en la carpeta Pictures
            val path = applicationContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val dirSave = File(applicationContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES),pathDirFotos)
            if (!dirSave.mkdirs())
                Logger.getLogger(EvidenciaFotoEmpaqueActivity::class.java.name).warning("Error al crear el directorio..")

        }
    }

    private fun save(content: ContentValues): Uri {
        var outputStream: OutputStream?
        var uri: Uri?
        application.contentResolver.also { resolver ->
            uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, content)
            outputStream = resolver.openOutputStream(uri!!)
        }
        outputStream.use { output ->
            getBitmap().compress(Bitmap.CompressFormat.JPEG, 100, output)
        }
        return uri!!
    }

    private fun clearContents(content: ContentValues, uri: Uri) {
        content.clear()
        content.put(MediaStore.MediaColumns.IS_PENDING, 0)
        contentResolver.update(uri, content, null, null)
    }

    private fun getBitmap(): Bitmap {
        return BitmapFactory.decodeFile(file.toString())
    }


}