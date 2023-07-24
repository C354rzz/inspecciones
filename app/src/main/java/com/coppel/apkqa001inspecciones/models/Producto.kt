package com.coppel.apkqa001inspecciones.models

import com.google.gson.annotations.SerializedName

data class Producto(
    @SerializedName("numPedido") val numPedido: Int,
    @SerializedName("idProducto") val idProducto: Int,
    @SerializedName("sku") val sku: Int,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("color") val color: String,
    @SerializedName("urlImagen") val imagen: String,
    @SerializedName("status") val status: String
)
