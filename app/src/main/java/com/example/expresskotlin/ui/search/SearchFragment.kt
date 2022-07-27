package com.example.expresskotlin.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.expresskotlin.R
import com.example.expresskotlin.databinding.FragmentSearchBinding
import org.greenrobot.eventbus.EventBus


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private var mToolbarTitle: String? = null

    private lateinit var imgBack : ImageView
    private lateinit var img_filter_indicator : ImageView
    private lateinit var linearTopFilter : LinearLayout
    private lateinit var expandableLayout : ConstraintLayout
    private var  isExpanded:Boolean=true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mToolbarTitle = it.getString("mToolbarTitle")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root


        initViews()
        return root
    }

    private fun initViews() {
        imgBack = binding.imgBack
        img_filter_indicator = binding.imgFilterIndicator
        linearTopFilter = binding.linearTopFilter
        expandableLayout = binding.expandableLayout
        expandableLayout.visibility = View.GONE

        img_filter_indicator.setOnClickListener {
            if (isExpanded){
                Log.d("TAG_searchFrag", "initViews: linearTopFilter if : $isExpanded")
                isExpanded = false
                img_filter_indicator.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                expandableLayout.visibility = View.VISIBLE

            }else{
                Log.d("TAG_searchFrag", "initViews: linearTopFilter else : $isExpanded")
                isExpanded = true
                img_filter_indicator.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                expandableLayout.visibility = View.GONE

            }
        }
        linearTopFilter.setOnClickListener {
            if (isExpanded){
                Log.d("TAG_searchFrag", "initViews: linearTopFilter if : $isExpanded")
                isExpanded = false
                img_filter_indicator.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                expandableLayout.visibility = View.VISIBLE

            }else{
                Log.d("TAG_searchFrag", "initViews: linearTopFilter else : $isExpanded")
                isExpanded = true
                img_filter_indicator.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                expandableLayout.visibility = View.GONE

            }
        }



//        val mToolBar: Toolbar = binding.toolbar
//        (activity as AppCompatActivity).setSupportActionBar(mToolBar)
//        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_editar_perfil)
//        if (((activity as AppCompatActivity)).supportActionBar != null) {
//            (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(true)
//            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        }
        imgBack.setOnClickListener {
            val navController = (activity as AppCompatActivity).findNavController(R.id.nav_host_fragment_activity_main)
            navController.navigateUp()
        }
    }

    override fun onDestroyView() {
        EventBus.getDefault().removeAllStickyEvents()
        super.onDestroyView()
        _binding = null
    }
}