package com.coppel.apkqa001inspecciones.listadoPedido.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.coppel.apkqa001inspecciones.databinding.ItemRvListadoPedidoBinding
import com.coppel.apkqa001inspecciones.models.Producto

class ListadoPedidoViewHolder (view: View): RecyclerView.ViewHolder(view){

    val binding = ItemRvListadoPedidoBinding.bind(view)

    fun render (producto: Producto, onClickListener: (Producto) -> Unit){
        binding.tvNombreProducto.text = producto.nombre
        binding.tvColorProducto.text = producto.color
        binding.tvCodigoProducto.text = producto.sku.toString()
        itemView.setOnClickListener { onClickListener(producto) }

    }
}