package com.coppel.apkqa001inspecciones.models.sesion

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Sesion(
    val token: String,
    val user: @RawValue Usuario
) : Parcelable