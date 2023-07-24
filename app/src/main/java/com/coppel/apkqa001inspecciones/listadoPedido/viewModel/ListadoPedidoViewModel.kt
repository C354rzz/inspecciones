package com.coppel.apkqa001inspecciones.listadoPedido.viewModel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coppel.apkqa001inspecciones.models.Producto
import com.coppel.apkqa001inspecciones.models.sesion.ServiceResponse
import com.coppel.apkqa001inspecciones.repository.db.InspeccionRepository
import com.coppel.apkqa001inspecciones.repository.db.entities.ProductoDB
import com.coppel.apkqa001inspecciones.service.PedidoResponseData
import com.coppel.apkqa001inspecciones.utils.productoToProductoDB
import com.coppel.framework.core.util.Outcome
import com.coppel.framework.core.util.toLiveData
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch

class ListadoPedidoViewModel : ViewModel() {


    lateinit var productoData: Producto
    var isLoading = MutableLiveData<Boolean>()
    private val inspeccionRepository: InspeccionRepository = InspeccionRepository()
    val isOnline = MutableLiveData<Boolean>()


    val obtenerPedidoOutcome:LiveData<Outcome<ServiceResponse<Array<PedidoResponseData>>>> by lazy {
        inspeccionRepository.obtenerPedidoOutcome.toLiveData(CompositeDisposable())
    }

    fun obtenerProductoPedido(context: Context?) {
        if (context?.let { verificaRed(it)} == true ){
            isLoading.postValue(true)
            inspeccionRepository.obtenerPedidoProducto(productoData.sku)

        }else {
            isLoading.postValue(false)
            isOnline.value = false
        }
    }

    private fun verificaRed(context: Context): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }

    fun salvarListaProductos(
        context: Context,
        listaProductos: MutableList<Producto>
    ) {
        isLoading.postValue(true)
        viewModelScope.launch {
            inspeccionRepository.deleteBackupData()
            inspeccionRepository.saveBackupData(productoToProductoDB(listaProductos))
        }
        //maxNumTimbrado.value = listaProductos.size
        for (producto in listaProductos) {
            obtenerPedidoBack(context, producto)
        }
        isLoading.postValue(false)
    }



    private fun obtenerPedidoBack(context: Context, producto: Producto) {

    }

}