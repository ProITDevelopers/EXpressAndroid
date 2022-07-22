package com.example.expresskotlin.ui.categoria_estab

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
import com.example.expresskotlin.databinding.FragmentCategoriaEstabBinding
import com.example.expresskotlin.adapters.CatEstabAdapter

import com.example.expresskotlin.models.MenuCategoria
import org.greenrobot.eventbus.EventBus


class CategoriaEstabFragment : Fragment() {

    private var _binding: FragmentCategoriaEstabBinding? = null
    private val binding get() = _binding!!
    private lateinit var menuCategoria: MenuCategoria
    lateinit var mRecyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        arguments?.let {
            menuCategoria = it.getSerializable("menucategoria") as MenuCategoria

        }

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCategoriaEstabBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initViews()



        return root
    }

    private fun initViews() {
        val mToolBar: Toolbar = binding.toolbar
        (activity as AppCompatActivity).setSupportActionBar(mToolBar)
        (activity as AppCompatActivity).supportActionBar?.title = menuCategoria.titulo
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
        val catEstabAdapter = context?.let { CatEstabAdapter(it) }

        menuCategoria.estabelecimentoList.let { catEstabAdapter?.setData(it) }

        val myLinearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
//        mRecyclerView?.setHasFixedSize(true)
        mRecyclerView.layoutManager = myLinearLayoutManager
        mRecyclerView.adapter = catEstabAdapter
    }



    override fun onDestroyView() {
        EventBus.getDefault().removeAllStickyEvents()
        super.onDestroyView()
        _binding = null
    }
}