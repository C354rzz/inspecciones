package com.coppel.apkqa001inspecciones.encabezadoInspeccion

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.coppel.apkqa001inspecciones.R
import com.coppel.apkqa001inspecciones.databinding.ActivityEncabezadoBinding
import com.coppel.apkqa001inspecciones.listadoPedido.ListadoPedidoActivity
import com.coppel.apkqa001inspecciones.models.sesion.Colaborador
import com.coppel.apkqa001inspecciones.models.sesion.Sesion
import kotlinx.android.synthetic.main.activity_encabezado.*

class EncabezadoActivity : AppCompatActivity() {
    private val permissions = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.INTERNET,
        Manifest.permission.ACCESS_NETWORK_STATE
    )

    private val permissionRequestCode = 479

    private lateinit var binding: ActivityEncabezadoBinding

    private lateinit var sesion: Sesion
    private lateinit var colaborador: Colaborador
    private lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_encabezado)
        binding = ActivityEncabezadoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = getString(R.string.inspeccion_loads_name_app)
        btn_comenzar.text = getString(R.string.btn_comenzar_insp)

        val btnComenzar = findViewById<Button>(R.id.btn_comenzar)
        btnComenzar.setOnClickListener {
            val intent = Intent(this, ListadoPedidoActivity::class.java)
            startActivity(intent)
        }

    }

    private fun obtenerPermisos(savedInstanceState: Bundle?) {
        if (savedInstanceState == null &&
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, permissions, permissionRequestCode)
        }
    }
}