package com.coppel.apkqa001inspecciones.repository.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class PedidoDB(
    @PrimaryKey(autoGenerate = true) val idPedido: Int,
    @SerializedName("numPedido") val numPedido: Int,
    @SerializedName("fechaPedido") val fecPedido: String,
    @SerializedName("proveedor") val proveedor: String,
    @SerializedName("numProveedor") val numProveedor: Int,
    @SerializedName("cedis") val cedis: String
)
