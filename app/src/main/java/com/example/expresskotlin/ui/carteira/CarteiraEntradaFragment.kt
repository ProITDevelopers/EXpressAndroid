package com.example.expresskotlin.ui.carteira

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expresskotlin.adapters.MeusPedidosAdapter
import com.example.expresskotlin.databinding.FragmentCarteiraEntradaBinding
import com.example.expresskotlin.models.MeusPedidos


class CarteiraEntradaFragment : Fragment() {

    private var _binding: FragmentCarteiraEntradaBinding?=null
    private val binding get() = _binding!!
    private lateinit var meusPedidosList : ArrayList<MeusPedidos>
    private lateinit var mRecyclerView : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCarteiraEntradaBinding.inflate(inflater, container, false)
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
        meusPedidosList.add(MeusPedidos("Recarregamento 1","27/07/2022","14000 Akz"))
        meusPedidosList.add(MeusPedidos("Recarregamento 2","26/07/2022","24000 Akz"))
        meusPedidosList.add(MeusPedidos("Recarregamento 3","25/07/2022","34000 Akz"))
        meusPedidosList.add(MeusPedidos("Recarregamento 4","24/07/2022","44000 Akz"))
        meusPedidosList.add(MeusPedidos("Recarregamento 5","23/07/2022","54000 Akz"))
        meusPedidosList.add(MeusPedidos("Recarregamento 6","22/07/2022","64000 Akz"))
        meusPedidosList.add(MeusPedidos("Recarregamento 7","21/07/2022","74000 Akz"))
        meusPedidosList.add(MeusPedidos("Recarregamento 8","20/07/2022","84000 Akz"))
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