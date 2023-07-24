package com.coppel.apkqa001inspecciones.listadoPedido.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coppel.apkqa001inspecciones.R
import com.coppel.apkqa001inspecciones.models.Producto

class ListadoPedidoAdapter(
    private val pedidoList:List<Producto>,
    private val onClickListener:(Producto)-> Unit
    ): RecyclerView.Adapter<ListadoPedidoViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListadoPedidoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ListadoPedidoViewHolder(layoutInflater.inflate(R.layout.item_rv_listado_pedido, parent, false))
    }

    override fun onBindViewHolder(holder: ListadoPedidoViewHolder, position: Int) {
        val item = pedidoList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = pedidoList.size
}