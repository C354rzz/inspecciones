package com.coppel.apkqa001inspecciones.service

import com.google.gson.annotations.SerializedName

data class ListaProductosResponseData(
    @SerializedName("idProducto") val idProducto: String,
    @SerializedName("sku") val sku: Number,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("color") val color: String,
    @SerializedName("urlImagen") val imagen: String,
    @SerializedName("status") val status: String
)
