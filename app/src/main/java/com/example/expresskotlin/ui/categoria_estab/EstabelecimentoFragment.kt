package com.example.expresskotlin.ui.categoria_estab


import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.expresskotlin.R
import com.example.expresskotlin.adapters.estabelecimento.EstabMainAdapter
import com.example.expresskotlin.databinding.FragmentEstabelecimentoBinding
import com.example.expresskotlin.models.Estabelecimento
import com.facebook.shimmer.ShimmerFrameLayout
import org.greenrobot.eventbus.EventBus


class EstabelecimentoFragment : Fragment() {

    private var _binding: FragmentEstabelecimentoBinding? = null
    private val binding get() = _binding!!
    private lateinit var estabelecimento: Estabelecimento

    private lateinit var imgEstab: ImageView
    private lateinit var shimmerFrameLayout: ShimmerFrameLayout
    private lateinit var txtEstabTitle: TextView

    private lateinit var mRecyclerView: RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        arguments?.let {
            estabelecimento = it.getSerializable("estabelecimento") as Estabelecimento

        }

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEstabelecimentoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initViews()



        return root
    }

    private fun initViews() {
        val mToolBar: Toolbar = binding.toolbar
        (activity as AppCompatActivity).setSupportActionBar(mToolBar)
        (activity as AppCompatActivity).supportActionBar?.title = estabelecimento.titulo
        if (((activity as AppCompatActivity)).supportActionBar != null) {
            (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(true)
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        mToolBar.setNavigationOnClickListener {
            val navController = (activity as AppCompatActivity).findNavController(R.id.nav_host_fragment_activity_main)
            navController.navigateUp()
        }

        imgEstab = binding.imgEstab
        shimmerFrameLayout = binding.shimmerFrameLayout
        txtEstabTitle = binding.txtEstabTitle
        mRecyclerView = binding.recyclerView



        setUpTopViews()
        setUpRecyclerView()


    }

    private fun setUpTopViews() {
        txtEstabTitle.text = estabelecimento.titulo
        context?.let {
            Glide.with(it).asBitmap()
                .load(estabelecimento.imgUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.color.gray_color)
                .listener(object : RequestListener<Bitmap> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        shimmerFrameLayout.stopShimmer()
                        return false
                    }

                    override fun onResourceReady(
                        resource: Bitmap?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        shimmerFrameLayout.visibility = View.GONE
                        return false
                    }

                })
                .into(imgEstab)


        }
    }

    private fun setUpRecyclerView() {
        val estabMainAdapter = context?.let { EstabMainAdapter(it) }
//        estabelecimento?.menuCatProdutosList?.let { estabMainAdapter?.setData(it) }
        estabelecimento.menuCatProdutosList.let {
            estabMainAdapter?.setData(estabelecimento.titulo.toString(),
                it
            )
        }

        val myLinearLayoutManager = object : LinearLayoutManager(context,RecyclerView.VERTICAL,false) {
            override fun canScrollVertically(): Boolean {
                return true
            }
        }
//        mRecyclerView?.setHasFixedSize(true)
        mRecyclerView.layoutManager = myLinearLayoutManager
        mRecyclerView.adapter = estabMainAdapter
    }


    override fun onDestroyView() {
        EventBus.getDefault().removeAllStickyEvents()
        super.onDestroyView()
        _binding = null
    }
}