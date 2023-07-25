package com.coppel.apkqa001inspecciones.listadoPedido.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.coppel.apkqa001inspecciones.databinding.FragmentPedidoProductoBinding
import com.coppel.apkqa001inspecciones.listadoPedido.viewModel.ListadoPedidoViewModel


class PedidoProductoFragment : Fragment() {

    private lateinit var binding: FragmentPedidoProductoBinding
    private val viewModel: ListadoPedidoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPedidoProductoBinding.inflate(inflater, container, false)
        return binding.root
        //return inflater.inflate(R.layout.fragment_pedido_producto, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun obtenerProductoPedido(){
        viewModel.obtenerProductoPedido(context)
    }


}