package com.coppel.apkqa001inspecciones.evidenciaFotoEmpaque.adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coppel.apkqa001inspecciones.R
import com.coppel.apkqa001inspecciones.models.FotoManual

class EvidenciaFotoManualAdapter(
    private val fotoManualList: List<FotoManual>,
    private val onClickListener: (FotoManual) -> Unit
) : RecyclerView.Adapter<EvidenciaFotoManualViewHolder>() {

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val imgTitle = itemView.findViewById<TextView>(R.id.tv_nombre_foto)
        fun bindView(fotoManual: FotoManual) {
            imgTitle.text = fotoManual.foto
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EvidenciaFotoManualViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return  EvidenciaFotoManualViewHolder(layoutInflater.inflate(R.layout.item_rv_listado_foto_manual, parent,false))
    }

    override fun onBindViewHolder(holder: EvidenciaFotoManualViewHolder, position: Int) {
        holder.bindView(fotoManualList[position],onClickListener)
    }

    override fun getItemCount(): Int = fotoManualList.size

}