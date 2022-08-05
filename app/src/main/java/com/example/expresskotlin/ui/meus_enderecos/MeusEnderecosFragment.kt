package com.example.expresskotlin.ui.meus_enderecos

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
import com.example.expresskotlin.adapters.MyAddressAdapter
import com.example.expresskotlin.databinding.FragmentMeusEnderecosBinding
import com.example.expresskotlin.models.MyAddress
import org.greenrobot.eventbus.EventBus


class MeusEnderecosFragment : Fragment() {

    private var _binding: FragmentMeusEnderecosBinding? = null
    private val binding get() = _binding!!
    private lateinit var mToolbarTitle:String
    private lateinit var mRecyclerView : RecyclerView
    private lateinit var myAddressList : ArrayList<MyAddress>

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


        _binding = FragmentMeusEnderecosBinding.inflate(inflater, container, false)
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
        mRecyclerView = binding.recyclerView
        loadMyAddress()
        setUpRecyclerView()
    }

    private fun loadMyAddress() {
        myAddressList= ArrayList()
        myAddressList.add(MyAddress("Casa","Rua OmegaX, Casa Y, Luanda"))
        myAddressList.add(MyAddress("Trabalho","Rua OmegaX, Casa Y, Luanda"))
        myAddressList.add(MyAddress("Favorito 1","Rua OmegaX, Casa Y, Luanda"))
        myAddressList.add(MyAddress("Favorito 2","Rua OmegaX, Casa Y, Luanda"))
        myAddressList.add(MyAddress("Favorito 3","Rua OmegaX, Casa Y, Luanda"))

    }

    private fun setUpRecyclerView() {
        val myAddressAdapter = context?.let { MyAddressAdapter(it) }

        myAddressList.let { myAddressAdapter?.setData(it) }

        val myLinearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
//        mRecyclerView?.setHasFixedSize(true)
        mRecyclerView.layoutManager = myLinearLayoutManager
        mRecyclerView.adapter = myAddressAdapter
    }

    override fun onDestroyView() {
        EventBus.getDefault().removeAllStickyEvents()
        super.onDestroyView()
        _binding = null
    }
}