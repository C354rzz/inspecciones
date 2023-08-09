package com.coppel.apkqa001inspecciones.listadoPedido.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.coppel.apkqa001inspecciones.R
import com.coppel.apkqa001inspecciones.databinding.ListadoPedidoFragmentBinding
import com.coppel.apkqa001inspecciones.inspeccionProducto.InspeccionProductoActivity
import com.coppel.apkqa001inspecciones.inspeccionProducto.viewModel.InspeccionProductoViewModel
import com.coppel.apkqa001inspecciones.listadoPedido.adapters.ListadoPedidoAdapter
import com.coppel.apkqa001inspecciones.listadoPedido.viewModel.ListadoPedidoViewModel
import com.coppel.apkqa001inspecciones.models.Producto
import com.coppel.apkqa001inspecciones.repository.InspeccionPedidoProvider

class ListadoPedidoFragment : Fragment() {

    private lateinit var binding: ListadoPedidoFragmentBinding
    private val viewModel: ListadoPedidoViewModel by activityViewModels()
    private val viewModelProducto: InspeccionProductoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListadoPedidoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView2()
    }

    private fun subscribeObservers() {}

    private fun initRecyclerView2() {
        val manager = LinearLayoutManager(context)
        val decoration = DividerItemDecoration(context, manager.orientation)
        binding.rvListProductos.layoutManager = manager
        binding.rvListProductos.adapter = ListadoPedidoAdapter(InspeccionPedidoProvider.pedidoList){
                producto -> onItemSelecter(producto)
        }
        binding.rvListProductos.addItemDecoration(decoration)
    }

    private fun onItemSelecter(producto: Producto) {
        val sendProducto = Bundle()
        sendProducto.putString("nombre", producto.nombre)
        sendProducto.putString("sku", producto.sku.toString())

        val intent = Intent(context, InspeccionProductoActivity::class.java)
        intent.putExtras(sendProducto)
        startActivity(intent)

        /*Toast.makeText(
            context,
            "Subalmacen: ${producto.nombre}",
            Toast.LENGTH_SHORT
        ).show()*/
    }

    private fun initRecyclerView(listaPedidos: MutableList<Producto>){
        val manager = LinearLayoutManager(context)
        val decoration = DividerItemDecoration(context, manager.orientation)
        binding.rvListProductos.layoutManager = manager
        binding.rvListProductos.adapter = ListadoPedidoAdapter(listaPedidos){ producto ->
            onItemSelected(producto)
        }
        binding.rvListProductos.addItemDecoration(decoration)
    }

    private fun onItemSelected(producto: Producto) {
        viewModel.productoData = producto
        findNavController().navigate(R.id.action_listadoPedidoFragment_to_pedidoProductoFragment)
    }

}