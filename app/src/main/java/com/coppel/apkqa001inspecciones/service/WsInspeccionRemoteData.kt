package com.coppel.apkqa001inspecciones.service

import android.os.Environment
import java.io.BufferedReader
import java.io.FileReader

class WsInspeccionRemoteData {
    private var urlService: String = ""
    private var urlQrService: String = ""

    fun obtenerRutas() {
        val archivo: FileReader
        try {
            val ruta = Environment.getExternalStorageDirectory().absolutePath + "/"
            archivo = FileReader(ruta + "RutaServicio.DAT")
            val leer = BufferedReader(archivo)
            val list = leer.readLines()
            this.urlService = list[2]
            this.urlQrService = list[9]
            leer.close()
        } catch (e: Exception) {
            this.urlService   = "https://desarrollogateway.coppel.io:5674/gateway/int-tmscentrador-xapi/1.1/cargas/"
            this.urlQrService = "https://desarrollogateway.coppel.io:5674/gateway/int-tmscentrador-xapi/1.1/qrcargas/"
            e.printStackTrace()
        }
    }
}