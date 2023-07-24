package com.coppel.apkqa001inspecciones.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.coppel.apkqa001inspecciones.repository.db.dao.ProductosDAO
import com.coppel.apkqa001inspecciones.repository.db.entities.PedidoDB
import com.coppel.apkqa001inspecciones.repository.db.entities.ProductoDB

@Database(entities = [PedidoDB::class, ProductoDB::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract  fun productosDAO():ProductosDAO

}