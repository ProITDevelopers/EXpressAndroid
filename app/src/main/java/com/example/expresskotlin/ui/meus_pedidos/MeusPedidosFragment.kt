package com.example.expresskotlin.ui.meus_pedidos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.expresskotlin.R
import com.example.expresskotlin.adapters.viewpager.ViewPagerFragmentsAdapter
import com.example.expresskotlin.databinding.FragmentMeusPedidosBinding
import com.google.android.material.tabs.TabLayout
import org.greenrobot.eventbus.EventBus


class MeusPedidosFragment : Fragment() {

    private var _binding: FragmentMeusPedidosBinding? = null
    private val binding get() = _binding!!
    private lateinit var mToolbarTitle:String
    private lateinit var tabLayout: TabLayout
    private lateinit var mViewPager: ViewPager
    private var tabIcons = intArrayOf(R.drawable.ic_baseline_pending_24, R.drawable.ic_baseline_history_24)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mToolbarTitle = it.getString("mToolbarTitle").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentMeusPedidosBinding.inflate(inflater, container, false)
        val root: View = binding.root



        initViews()

        return root
    }

    private fun initViews() {
        val mToolBar: Toolbar = binding.toolbar
        (activity as AppCompatActivity).setSupportActionBar(mToolBar)
        (activity as AppCompatActivity).supportActionBar?.title = mToolbarTitle
        if (((activity as AppCompatActivity)).supportActionBar != null) {
            (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(true)
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        mToolBar.setNavigationOnClickListener {
            val navController = (activity as AppCompatActivity).findNavController(R.id.nav_host_fragment_activity_main)
            navController.navigateUp()
        }
        tabLayout = binding.tabs
        mViewPager = binding.viewPager
        showFrags()
    }

    private fun showFrags() {



        tabLayout.setupWithViewPager(mViewPager)
        val adapter = ViewPagerFragmentsAdapter(childFragmentManager)

        // add your fragments
        adapter.addFrag(PedidosAtivosFragment(), getString(R.string.pedidos_tab_ativos))
        adapter.addFrag(PedidosHistoricoFragment(), getString(R.string.pedidos_tab_historicos))

        // set adapter on viewpager
        mViewPager.adapter = adapter



        for (icon in tabIcons.indices){
            tabLayout.getTabAt(icon)?.setIcon(tabIcons[icon])
        }



    }

    override fun onDestroyView() {
        EventBus.getDefault().removeAllStickyEvents()
        super.onDestroyView()
        _binding = null
    }
}