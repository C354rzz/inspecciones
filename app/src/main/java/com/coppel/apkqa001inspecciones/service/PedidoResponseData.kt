package com.coppel.apkqa001inspecciones.service

import com.google.gson.annotations.SerializedName

data class PedidoResponseData(
    @SerializedName("pedido") val pedido: String,
    @SerializedName("fechaPedido") val fecPedido: String,
    @SerializedName("proveedor") val proveedor: Int,
    @SerializedName("numeroProveedor") val numProveedor: Int,
    @SerializedName("cedis") val cedis: Int

)
