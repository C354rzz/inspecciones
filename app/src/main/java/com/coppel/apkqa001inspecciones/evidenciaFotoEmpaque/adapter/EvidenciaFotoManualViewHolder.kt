package com.coppel.apkqa001inspecciones.evidenciaFotoEmpaque.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coppel.apkqa001inspecciones.R
import com.coppel.apkqa001inspecciones.databinding.ItemRvListadoFotoManualBinding
import com.coppel.apkqa001inspecciones.models.FotoManual

class EvidenciaFotoManualViewHolder (view: View): RecyclerView.ViewHolder(view) {

    val binding = ItemRvListadoFotoManualBinding.bind(view)


    fun bindView(fotoManual: FotoManual, onClickListener: (FotoManual) -> Unit) {
        binding.tvNombreFoto.text = fotoManual.foto
    }
}