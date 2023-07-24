package com.coppel.apkqa001inspecciones.models

import com.google.gson.annotations.SerializedName

data class Pedido(
    val pedido: Int,
    val fecPedido: String,
    val proveedor: String,
    val numProveedor: Int,
    val cedis: String
)
