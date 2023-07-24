package com.coppel.apkqa001inspecciones.models.sesion


import com.google.gson.annotations.SerializedName

data class ServiceResponse<T>(
    @SerializedName("META", alternate = ["meta"]) val meta: Meta,
    @SerializedName("DATA", alternate = ["data"]) val data: T
)