package com.coppel.apkqa001inspecciones.repository.db

import androidx.room.Room
import com.coppel.apkqa001inspecciones.models.sesion.ServiceResponse
import com.coppel.apkqa001inspecciones.repository.db.dao.ProductosDAO
import com.coppel.apkqa001inspecciones.repository.db.entities.ProductoDB
import com.coppel.apkqa001inspecciones.service.PedidoResponseData
import com.coppel.apkqa001inspecciones.service.WsInspeccionRemoteData
import com.coppel.apkqa001inspecciones.singleton.RxApplication
import com.coppel.framework.core.util.Outcome
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InspeccionRepository {

    private var dataBase: AppDatabase
    private var remoteData: WsInspeccionRemoteData = WsInspeccionRemoteData()
    private var productosDAO: ProductosDAO

    init {
        dataBase = Room.databaseBuilder(
            RxApplication.getContext().get()!!,
            AppDatabase::class.java, "database-name"
        ).build()
        productosDAO = dataBase.productosDAO()
    }

    val obtenerPedidoOutcome: PublishSubject<Outcome<ServiceResponse<Array<PedidoResponseData>>>> by lazy {
        PublishSubject.create()
    }

    private fun obtenerRutas() {
        return remoteData.obtenerRutas()
    }

    fun obtenerPedidoProducto(sku: Number) {

    }

    suspend fun saveBackupData(productosMock:List<ProductoDB>) {
        return withContext(Dispatchers.IO){
            productosDAO.insertProductos(productosMock)
        }
    }

    suspend fun deleteBackupData() {
        return withContext(Dispatchers.IO) {
            productosDAO.deleteAll()
        }
    }
}