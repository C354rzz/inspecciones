package com.coppel.apkqa001inspecciones.models.sesion

import com.google.gson.annotations.SerializedName

data class InspeccionPedido(
    @SerializedName("idPedido") val idPedido: String,
)
