package com.example.expresskotlin.ui.meus_pedidos


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expresskotlin.adapters.MeusPedidosAdapter
import com.example.expresskotlin.databinding.FragmentPedidosHistoricoBinding
import com.example.expresskotlin.models.MeusPedidos

class PedidosHistoricoFragment : Fragment() {

    private var _binding: FragmentPedidosHistoricoBinding?=null
    private val binding get() = _binding!!
    private lateinit var meusPedidosList : ArrayList<MeusPedidos>
    private lateinit var mRecyclerView : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPedidosHistoricoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initViews()
        return root

    }

    private fun initViews() {
        mRecyclerView = binding.recyclerView
        loadAllHistoricos()
        setUpRecyclerView()
    }

    private fun loadAllHistoricos() {
        meusPedidosList= ArrayList()
        meusPedidosList.add(MeusPedidos("2354756853128","27/07/2022","Entregue"))
        meusPedidosList.add(MeusPedidos("2354756853127","26/07/2022","Cancelado"))
        meusPedidosList.add(MeusPedidos("2354756853126","25/07/2022","Entregue"))
        meusPedidosList.add(MeusPedidos("2354756853125","24/07/2022","Cancelado"))
        meusPedidosList.add(MeusPedidos("2354756853124","23/07/2022","Entregue"))
        meusPedidosList.add(MeusPedidos("2354756853123","22/07/2022","Cancelado"))
        meusPedidosList.add(MeusPedidos("2354756853122","21/07/2022","Entregue"))
        meusPedidosList.add(MeusPedidos("2354756853121","20/07/2022","Cancelado"))
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