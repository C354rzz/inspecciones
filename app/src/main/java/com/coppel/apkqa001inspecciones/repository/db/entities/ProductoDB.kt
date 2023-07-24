package com.coppel.apkqa001inspecciones.repository.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class ProductoDB(
    @PrimaryKey(autoGenerate = true) val idProducto: Int,
    @SerializedName("numPedido") val numPedido: Int,
    @SerializedName("sku") val sku: Int,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("color") val color: String,
    @SerializedName("urlImagen") val imagen: String,
    @SerializedName("status") val status: String
)
