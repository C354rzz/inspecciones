package com.coppel.apkqa001inspecciones.evidenciaFotoEmpaque.fragments

import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.coppel.apkqa001inspecciones.databinding.FragmentListadoFotoManualBinding
import com.coppel.apkqa001inspecciones.evidenciaFotoEmpaque.EvidenciaFotoEmpaqueActivity
import com.coppel.apkqa001inspecciones.evidenciaFotoEmpaque.adapter.EvidenciaFotoManualAdapter
import com.coppel.apkqa001inspecciones.evidenciaFotoEmpaque.viewModel.EvidenciaFotoEmpaqueViewModel
import com.coppel.apkqa001inspecciones.models.FotoManual
import com.mikepenz.iconics.Iconics.applicationContext
import okio.IOException
import java.io.File
import java.util.logging.Logger

class ListadoFotoManualFragment : Fragment() {

    lateinit var currentPhotoPath: String

    private lateinit var binding: FragmentListadoFotoManualBinding
    private val viewModel : EvidenciaFotoEmpaqueViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListadoFotoManualBinding.inflate( inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val gpath:String = applicationContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        //var gpath:String = Environment.getExternalStorageDirectory().absolutePath
        var spath: String = Environment.getExternalStorageDirectory().absolutePath
        var fullpath = File(spath + File.separator + Environment.DIRECTORY_PICTURES + File.separator + "dirInspSku")
        //imageReader(fullpath)

        //initRecyclerView(list)
        //Log.e("folderFotos", fullpath.toString())
        //Logger.getLogger(EvidenciaFotoEmpaqueActivity::class.java.name).warning(spath)
    }


    private fun imageReader(root: File) {
        val fileList: ArrayList<File> = ArrayList()
        val listAllFiles = root.listFiles()

        if (listAllFiles != null && listAllFiles.size > 0) {
            for (currentFile in listAllFiles) {
                if (currentFile.name.endsWith(".jpg")) {
                    // File Name
                    Log.e("jpgName", currentFile.getName())
                    fileList.add(currentFile.absoluteFile)
                }
            }
            Log.w("fileList", "" + fileList.size)
        }
    }

    private fun initRecyclerView(list: List<FotoManual>) {
        val manager = LinearLayoutManager(context)
        val decoration = DividerItemDecoration(context, manager.orientation)
        binding.rvListFotoManual.layoutManager = manager
        binding.rvListFotoManual.adapter = EvidenciaFotoManualAdapter(list){
                fotoManual -> onItemSelecter(fotoManual)
        }
        binding.rvListFotoManual.addItemDecoration(decoration)
    }

    private fun onItemSelecter(fotoManual: FotoManual) {
        val sendProducto = Bundle()
        sendProducto.putString("foto", fotoManual.foto)
        //sendProducto.putString("sku", fotoManual.sku.toString())

       /*val intent = Intent(context, InspeccionProductoActivity::class.java)
        intent.putExtras(sendProducto)
        startActivity(intent)*/

        Toast.makeText(
            context,
            "Subalmacen: ${fotoManual.foto}",
            Toast.LENGTH_SHORT
        ).show()
    }
}


