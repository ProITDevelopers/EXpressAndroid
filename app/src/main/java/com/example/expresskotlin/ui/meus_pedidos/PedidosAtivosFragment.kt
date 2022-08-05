package com.example.expresskotlin.ui.meus_pedidos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expresskotlin.adapters.MeusPedidosAdapter
import com.example.expresskotlin.databinding.FragmentPedidosAtivosBinding
import com.example.expresskotlin.models.MeusPedidos


class PedidosAtivosFragment : Fragment() {

    private var _binding: FragmentPedidosAtivosBinding?=null
    private val binding get() = _binding!!
    private lateinit var meusPedidosList : ArrayList<MeusPedidos>
    private lateinit var mRecyclerView : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPedidosAtivosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initViews()
        return root

    }

    private fun initViews() {
        mRecyclerView = binding.recyclerView
        loadAllAtivos()
        setUpRecyclerView()
    }

    private fun loadAllAtivos() {
        meusPedidosList= ArrayList()
        meusPedidosList.add(MeusPedidos("2354756853121","27/07/2022","Em Espera"))
        meusPedidosList.add(MeusPedidos("2354756853122","27/07/2022","Em Espera"))
        meusPedidosList.add(MeusPedidos("2354756853123","27/07/2022","Em Espera"))
        meusPedidosList.add(MeusPedidos("2354756853124","27/07/2022","Em Espera"))
        meusPedidosList.add(MeusPedidos("2354756853125","27/07/2022","Em Espera"))
        meusPedidosList.add(MeusPedidos("2354756853126","27/07/2022","Em Espera"))
        meusPedidosList.add(MeusPedidos("2354756853127","27/07/2022","Em Espera"))
        meusPedidosList.add(MeusPedidos("2354756853128","27/07/2022","Em Espera"))
    }

    private fun setUpRecyclerView() {
        val meusPedidosAdapter = context?.let { MeusPedidosAdapter(it) }

        meusPedidosList.let { meusPedidosAdapter?.setData(it) }

        val myLinearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
//        mRecyclerView?.setHasFixedSize(true)
        mRecyclerView.layoutManager = myLinearLayoutManager
        mRecyclerView.adapter = meusPedidosAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}