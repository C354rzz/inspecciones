package com.coppel.apkqa001inspecciones.listadoPedido

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coppel.apkqa001inspecciones.R
import com.coppel.apkqa001inspecciones.databinding.ActivityListadoPedidoBinding
import com.coppel.apkqa001inspecciones.listadoPedido.viewModel.ListadoPedidoViewModel
import com.coppel.apkqa001inspecciones.models.sesion.ServiceResponse
import com.coppel.apkqa001inspecciones.service.PedidoResponseData
import com.coppel.framework.core.util.Outcome
import com.coppel.framework.core.view.messagedialog.MessageDialog
import kotlinx.android.synthetic.main.activity_listado_pedido.*

class ListadoPedidoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListadoPedidoBinding
    private val viewModel: ListadoPedidoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_listado_pedido)

        binding = ActivityListadoPedidoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //subscribeObservers()

        supportActionBar?.title = getString(R.string.inspeccion_loads_name_app)
        btn_finalizar.text = getString(R.string.btn_finalizar_insp)
    }


    private fun isLoading(enabled: Boolean) {
        binding.progress.isVisible = enabled
        binding.block.isVisible = enabled
    }

    private fun subscribeObservers() {
        viewModel.obtenerPedidoOutcome.observe(this){outcome ->
            if (outcome is Outcome.Success<ServiceResponse<Array<PedidoResponseData>>>){
                val response = outcome.data
                if (response.data.size==0){
                    MessageDialog.showDialogWithAction(
                        supportFragmentManager,
                        getString(R.string.title_advertencia),
                        R.drawable.ic_baseline_warning_24,
                        response.meta.message
                    ){
                        finish()
                    }
                }else{
                    MessageDialog.showDialogWithAction(
                        supportFragmentManager,
                        getString(R.string.title_advertencia),
                        R.drawable.ic_baseline_warning_24,
                        response.meta.message
                    ){
                        finish()
                    }
                }
            }else if (outcome is Outcome.Failure<ServiceResponse<Array<PedidoResponseData>>>) {
                isLoading(false)
                MessageDialog.showDialogWithAction(
                    supportFragmentManager,
                    getString(R.string.title_advertencia),
                    R.drawable.ic_baseline_warning_24,
                    getString(R.string.error_obtener_listado_pedido)
                ) {
                    finish()
                }
            }
        }
    }


}