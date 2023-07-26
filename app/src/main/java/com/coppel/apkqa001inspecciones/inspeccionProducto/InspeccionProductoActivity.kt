package com.coppel.apkqa001inspecciones.inspeccionProducto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.activity.viewModels
import com.coppel.apkqa001inspecciones.R
import com.coppel.apkqa001inspecciones.databinding.ActivityInspeccionProductoBinding
import com.coppel.apkqa001inspecciones.inspeccionProducto.viewModel.InspeccionProductoViewModel


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