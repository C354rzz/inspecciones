package com.coppel.apkqa001inspecciones.singleton

import android.app.Application
import android.content.Context
import com.coppel.apkqa001inspecciones.service.WsInspeccionNetworkService
import java.lang.ref.WeakReference


class RxApplication : Application() {

    init {
        instance = this
    }

    companion object {
        var instance: RxApplication? = null
        private var contextReference: WeakReference<Context?>? = null

        fun getInspeccionNetwork(url: String): WsInspeccionNetworkService =
            WsInspeccionNetworkService(url)

        fun getContext(): WeakReference<Context> =
            WeakReference(instance!!.applicationContext!!)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        contextReference = WeakReference(instance?.applicationContext)
    }
}