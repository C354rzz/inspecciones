package com.coppel.apkqa001inspecciones.service

import com.coppel.framework.core.domain.NetworkService

private const val REQUEST_TIMEOUT: Long = 300

class WsInspeccionNetworkService(url: String): NetworkService<WsInspeccionApiInterface>(
    url,
    WsInspeccionApiInterface::class.java,
    listOf(),
    REQUEST_TIMEOUT
)