package com.example.expresskotlin.ui.perfil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expresskotlin.R
import com.example.expresskotlin.adapters.PerfilSettingsAdapter
import com.example.expresskotlin.databinding.FragmentPerfilBinding
import com.example.expresskotlin.models.IconTitle

class PerfilFragment : Fragment() {

    private var _binding: FragmentPerfilBinding? = null
    private val binding get() = _binding!!

    private lateinit var mRecyclerView: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {


        _binding = FragmentPerfilBinding.inflate(inflater, container, false)
        val root: View = binding.root


        initViews()
        return root
    }

    private fun initViews() {
        val mToolBar: Toolbar = binding.toolbar
        (activity as AppCompatActivity).setSupportActionBar(mToolBar)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_perfil)
        if (((activity as AppCompatActivity)).supportActionBar != null) {
            (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(true)
        }

        mRecyclerView = binding.recyclerView
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val perfilSettingsAdapter = context?.let { PerfilSettingsAdapter(it) }
        perfilSettingsAdapter?.setData(getAllList())

        val myLinearLayoutManager = object : LinearLayoutManager(context,RecyclerView.VERTICAL,false) {
            override fun canScrollVertically(): Boolean {
                return true
            }
        }
//        mRecyclerView?.setHasFixedSize(true)
        mRecyclerView.layoutManager = myLinearLayoutManager
        mRecyclerView.adapter = perfilSettingsAdapter
    }

    fun getAllList():ArrayList<IconTitle>{
        var iconTitleList = ArrayList<IconTitle>()

        iconTitleList.add(IconTitle("Atualizar Perfil",R.drawable.ic_baseline_account_settings_24))
        iconTitleList.add(IconTitle("Atualizar Palavra-Passe",R.drawable.ic_baseline_password_settings_24))
        iconTitleList.add(IconTitle("Meus Endereços",R.drawable.ic_baseline_add_location_settings_24))
        iconTitleList.add(IconTitle("Pedidos",R.drawable.ic_baseline_order_settings_24))
        iconTitleList.add(IconTitle("Carteira",R.drawable.ic_baseline_wallet_settings_24))

        return iconTitleList
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}