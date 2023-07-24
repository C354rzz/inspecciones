package com.coppel.apkqa001inspecciones.models

import com.coppel.apkqa001inspecciones.models.sesion.Colaborador
import com.coppel.apkqa001inspecciones.models.sesion.Sesion

class SessionData(
    var sesion: Sesion,
    var colaborador: Colaborador,
    var numCedis: Int,
    var numCediRegional: Int,
    var tokenApigateway: String,
    var tokenSSO: String
)