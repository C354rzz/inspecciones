package com.coppel.apkqa001inspecciones.repository.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.coppel.apkqa001inspecciones.repository.db.entities.PedidoDB
import com.coppel.apkqa001inspecciones.repository.db.entities.ProductoDB

@Dao
interface ProductosDAO {
    @Query("SELECT * FROM ProductoDB")
    suspend fun getAll(): List<ProductoDB>

    @Insert
    fun insertProductos(productosMock: List<ProductoDB>)

    @Query("DELETE FROM ProductoDB")
    suspend fun deleteAll()

    @Query("SELECT * FROM ProductoDB where numPedido like :numPedido")
    suspend fun buscarPeoductos(numPedido: Int): ProductoDB

    @Insert
    fun salvarDetalle(cambiarDetalleADetalleDB: ProductoDB)

    @Update
    suspend fun updateDetalle(detallePedidoDB: ProductoDB): Int
}