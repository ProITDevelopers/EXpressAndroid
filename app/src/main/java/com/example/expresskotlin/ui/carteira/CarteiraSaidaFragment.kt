package com.example.expresskotlin.ui.carteira

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expresskotlin.adapters.MeusPedidosAdapter
import com.example.expresskotlin.databinding.FragmentCarteiraSaidaBinding
import com.example.expresskotlin.models.MeusPedidos


class CarteiraSaidaFragment : Fragment() {

    private var _binding: FragmentCarteiraSaidaBinding?=null
    private val binding get() = _binding!!
    private lateinit var meusPedidosList : ArrayList<MeusPedidos>
    private lateinit var mRecyclerView : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCarteiraSaidaBinding.inflate(inflater, container, false)
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
        meusPedidosList.add(MeusPedidos("Compra 1","27/07/2022","100 Akz"))
        meusPedidosList.add(MeusPedidos("Compra 2","26/07/2022","200 Akz"))
        meusPedidosList.add(MeusPedidos("Compra 3","25/07/2022","300 Akz"))
        meusPedidosList.add(MeusPedidos("Compra 4","24/07/2022","400 Akz"))
        meusPedidosList.add(MeusPedidos("Compra 5","23/07/2022","500 Akz"))
        meusPedidosList.add(MeusPedidos("Compra 6","22/07/2022","600 Akz"))
        meusPedidosList.add(MeusPedidos("Compra 7","21/07/2022","700 Akz"))
        meusPedidosList.add(MeusPedidos("Compra 8","20/07/2022","800 Akz"))
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