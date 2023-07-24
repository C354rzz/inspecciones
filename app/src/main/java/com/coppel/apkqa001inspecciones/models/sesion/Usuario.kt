package com.coppel.apkqa001inspecciones.models.sesion

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Usuario(
    val firstName: String,
    val middleName: String,
    val lastName: String,
    val employeeId: String,
    val userId: String,
    val wcToken: String,
    val wcTrustedToken: String,
    val personalizationID: String
) : Parcelable