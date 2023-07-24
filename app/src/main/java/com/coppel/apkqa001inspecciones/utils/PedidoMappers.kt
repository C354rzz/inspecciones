package com.coppel.apkqa001inspecciones.utils

import com.coppel.apkqa001inspecciones.models.Producto
import com.coppel.apkqa001inspecciones.repository.db.entities.ProductoDB

fun productoToProductoDB(productosMock: List<Producto>): List<ProductoDB> {
    val listaMapped = mutableListOf<ProductoDB>()
    for (lista in productosMock) {
        listaMapped.add(
            ProductoDB(
                idProducto = 0,
                numPedido = lista.numPedido,
                sku = lista.sku,
                nombre = lista.nombre,
                color = lista.color,
                imagen = lista.imagen,
                status = lista.status
            )
        )
    }
    return listaMapped
}

