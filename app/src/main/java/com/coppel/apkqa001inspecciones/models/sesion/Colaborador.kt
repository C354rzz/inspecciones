package com.coppel.apkqa001inspecciones.models.sesion


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Colaborador(
    var numeroempleado: Int,
    var nombre: String,
    var apellidopaterno: String,
    var apellidomaterno: String,
    var numerocentro: Int,
    var numeropuesto: Int,
    var descripcionpuesto: String,
    var fechaalta: String,
    var fechanacimiento: String,
    var curp: String,
    var numeroempresa: Int,
    var sexo: String,
    var numerociudad: Int,
    var seccioncentro: String,
    var descripcioncentro: String,
    var numeroregionEC: Int,
    var nombreregionEC: String,
    var inicialregionEC: String,
    var nempJefe: Int,
    var puestoJefe: Int,
    var seccionJefe: String,
    var estatus: String
) : Parcelable