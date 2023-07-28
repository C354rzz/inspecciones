package com.coppel.apkqa001inspecciones.inspeccionProducto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import androidx.activity.viewModels
import com.coppel.apkqa001inspecciones.R
import com.coppel.apkqa001inspecciones.databinding.ActivityInspeccionProductoBinding
import com.coppel.apkqa001inspecciones.evidenciaFotoEmpaque.EvidenciaFotoEmpaqueActivity
import com.coppel.apkqa001inspecciones.inspeccionProducto.viewModel.InspeccionProductoViewModel
import com.coppel.apkqa001inspecciones.listadoPedido.ListadoPedidoActivity


class InspeccionProductoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInspeccionProductoBinding
    private val viewModel: InspeccionProductoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInspeccionProductoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = getString(R.string.inspeccion_loads_name_app)

        val getProducto = intent.extras
        if (getProducto != null) {
            binding.tvNombreProducto.text =
                "${getProducto.getString("sku") + " - " + getProducto.getString("nombre")}"
        }

        val btnComenzar1 = findViewById<Button>(R.id.btn_continuar1)
        btnComenzar1.setOnClickListener {
            val intent = Intent(this, EvidenciaFotoEmpaqueActivity::class.java)
            startActivity(intent)
        }
    }

    fun onRBClickedEmpaque(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.rb_manual_si ->
                    if (checked) {
                        // opcion si
                    }
                R.id.rb_manual_no ->
                    if (checked) {
                        // opcion si
                    }
                R.id.rb_manual_na ->
                    if (checked) {
                        // opcion no aplica
                    }
            }
        }
    }

    fun onRbClickedHallazgo(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.rb_hallazgo_si ->
                    if (checked) {
                        // opcion si
                    }
                R.id.rb_hallazgo_no ->
                    if (checked) {
                        // opcion si
                    }
            }
        }
    }

}