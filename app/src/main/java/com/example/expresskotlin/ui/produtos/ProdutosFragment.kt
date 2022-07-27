package com.example.expresskotlin.ui.produtos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expresskotlin.R
import com.example.expresskotlin.adapters.ProdutosAdapter
import com.example.expresskotlin.databinding.FragmentProdutosBinding
import com.example.expresskotlin.models.MenuCatProdutos
import org.greenrobot.eventbus.EventBus


class ProdutosFragment  : Fragment() {

    private var _binding: FragmentProdutosBinding? = null
    private val binding get() = _binding!!
    private var estabTitulo: String? = null
    private lateinit var menuCatProdutos: MenuCatProdutos
    private lateinit var mRecyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        arguments?.let {
            estabTitulo = it.getString("estabTitulo")
            menuCatProdutos = it.getSerializable("menuCatProdutos") as MenuCatProdutos

        }

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentProdutosBinding.inflate(inflater, container, false)
        val root: View = binding.root



        initViews()

        return root
    }

    private fun initViews() {
        val mToolBar: Toolbar = binding.toolbar
        (activity as AppCompatActivity).setSupportActionBar(mToolBar)
        (activity as AppCompatActivity).supportActionBar?.title = estabTitulo
        if (((activity as AppCompatActivity)).supportActionBar != null) {
            (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(true)
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        mToolBar.setNavigationOnClickListener {
            val navController = (activity as AppCompatActivity).findNavController(R.id.nav_host_fragment_activity_main)
            navController.navigateUp()
        }
        mRecyclerView = binding.recyclerView
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val produtosAdapter = context?.let { ProdutosAdapter(it) }

        menuCatProdutos.produtosList.let { produtosAdapter?.setData(estabTitulo.toString(),it) }

        val myLinearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
//        mRecyclerView?.setHasFixedSize(true)
        mRecyclerView.layoutManager = myLinearLayoutManager
        mRecyclerView.adapter = produtosAdapter
    }

    override fun onDestroyView() {
        EventBus.getDefault().removeAllStickyEvents()
        super.onDestroyView()
        _binding = null
    }
}