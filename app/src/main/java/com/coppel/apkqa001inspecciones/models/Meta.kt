package com.coppel.apkqa001inspecciones.models.sesion

import com.google.gson.annotations.SerializedName

data class Meta (
    @SerializedName("transactionID") val transactionID: String,
    @SerializedName("status") val status: String,
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("timestamp") val timestamp: String,
    @SerializedName("message") val message: String
)