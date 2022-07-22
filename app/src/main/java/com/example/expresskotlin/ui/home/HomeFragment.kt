package com.example.expresskotlin.ui.home

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.expresskotlin.R
import com.example.expresskotlin.databinding.FragmentHomeBinding
import com.example.expresskotlin.adapters.ViewPagerAdapterSlider
import com.example.expresskotlin.adapters.home.HomeMainAdapter

import com.example.expresskotlin.helpers.LoadData
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import de.hdodenhof.circleimageview.CircleImageView

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!



    lateinit var imageView_avatar_toolbar: CircleImageView
    lateinit var txtMyLocation: TextView
    lateinit var imgSearch: ImageView


    lateinit var viewPager: ViewPager
    lateinit var wormDotsIndicator: WormDotsIndicator
//    private var sliderDotspanel: LinearLayout? = null
    private var dotscount:Int = 0

    private var imagesTopList = intArrayOf(R.drawable.login, R.drawable.registrar, R.drawable.grill)
    private var slideHandler = Handler()
    private var TIME_DELAY : Long = 5000 // Slide duration 5 seconds


    private lateinit var mRecyclerView: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initViews()
        setUpViews()
        return root
    }


    private fun initViews() {
        val mToolBar: Toolbar = binding.toolbar
//        (activity as AppCompatActivity).setSupportActionBar(mToolBar)
//        if (((activity as AppCompatActivity)).supportActionBar != null) {
//            (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
//            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
//        }

        imageView_avatar_toolbar = binding.imageViewAvatarToolbar
        txtMyLocation = binding.txtMyLocation
        imgSearch = binding.imgSearch


        viewPager = binding.viewPager
        wormDotsIndicator = binding.wormDotsIndicator
//        sliderDotspanel = binding.SliderDots;
        mRecyclerView = binding.recyclerView
    }

    private fun setUpViews() {
        slideAnimate()
        setUpData()
        setUpRecyclerView()
    }

    private fun setUpData() {


    }

    private fun slideAnimate() {
//        sliderDotspanel?.removeAllViews()

        val viewPagerAdapter = context?.let { ViewPagerAdapterSlider(it, imagesTopList) }
        viewPager.adapter = viewPagerAdapter


        wormDotsIndicator.setViewPager(viewPager)

        dotscount = viewPagerAdapter!!.count


        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {

                slideHandler.removeCallbacks(sliderRunnable)
                slideHandler.postDelayed(
                    sliderRunnable,
                    TIME_DELAY
                ) // Slide duration 5 seconds

                if (position + 1 == dotscount) {
                    slideHandler.postDelayed(
                        runnable,
                        TIME_DELAY
                    ) // Slide duration 5 seconds
                }


            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })
    }



    private fun setUpRecyclerView() {
        val homeMainAdapter = context?.let { HomeMainAdapter(it) }
        homeMainAdapter?.setData(LoadData.getAllMenuCategoriaList())

        val myLinearLayoutManager = object : LinearLayoutManager(context,RecyclerView.VERTICAL,false) {
            override fun canScrollVertically(): Boolean {
                return true
            }
        }
//        mRecyclerView?.setHasFixedSize(true)
        mRecyclerView.layoutManager = myLinearLayoutManager
        mRecyclerView.adapter = homeMainAdapter
    }




    private val sliderRunnable = Runnable {
        viewPager.currentItem = viewPager.currentItem + 1
    }

    private val runnable = Runnable {
        viewPager.currentItem = 0
    }


    override fun onResume() {
        super.onResume()
        slideHandler.postDelayed(
            sliderRunnable,
            TIME_DELAY
        ) // Slide duration 5 seconds

    }

    override fun onPause() {
        slideHandler.removeCallbacks(sliderRunnable)
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}