package com.example.expresskotlin.ui.produtos

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.expresskotlin.R
import com.example.expresskotlin.databinding.FragmentProdutoDetalheBinding
import com.example.expresskotlin.helpers.MetodosUsados
import com.example.expresskotlin.models.Produtos
import de.hdodenhof.circleimageview.CircleImageView
import org.greenrobot.eventbus.EventBus


class ProdutoDetalheFragment  : Fragment() {

    private var _binding: FragmentProdutoDetalheBinding? = null
    private val binding get() = _binding!!
    private var estabTitulo: String? = null
    private lateinit var produto: Produtos
    var quantity: Int=0


    lateinit var imgProd: CircleImageView
    lateinit var imgRemoveItem: ImageView
    lateinit var txtProdQuantity:TextView
    lateinit var imgAddItem:ImageView

    lateinit var txtProdTitle:TextView
    lateinit var txtProdPrice:TextView
    lateinit var txtAddItemCart:TextView
    lateinit var relativeAddItemCart:RelativeLayout




    override fun onCreate(savedInstanceState: Bundle?) {
        arguments?.let {
            estabTitulo = it.getString("estabTitulo")
            produto = it.getSerializable("produto") as Produtos

        }

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentProdutoDetalheBinding.inflate(inflater, container, false)
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


        imgProd=binding.imgProd
        imgRemoveItem=binding.imgRemoveItem
        txtProdQuantity=binding.txtProdQuantity
        imgAddItem=binding.imgAddItem

        txtProdTitle=binding.txtProdTitle
        txtProdPrice=binding.txtProdPrice
        txtAddItemCart=binding.txtAddItemCart
        relativeAddItemCart=binding.relativeAddItemCart

        setUpViews()
    }

    private fun setUpViews() {

        context?.let {
            Glide.with(it).asBitmap()
                .load(produto.imgUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .listener(object : RequestListener<Bitmap> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        isFirstResource: Boolean
                    ): Boolean {

                        return false
                    }

                    override fun onResourceReady(
                        resource: Bitmap?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {

                        return false
                    }

                })
                .into(imgProd)

            txtProdTitle.text = produto.titulo
            txtProdPrice.text = StringBuilder(produto.preco.toString()).append(" Akz").toString()
            txtProdQuantity.text = produto.quantity.toString()
            quantity = produto.quantity
        }

        if (quantity>0){

            txtAddItemCart.text = "Adicionado"
            relativeAddItemCart.isEnabled = false
        }
        imgRemoveItem.setOnClickListener{
            context?.let { it1 ->
                quantity--
                if (quantity<=0){
                    quantity = 0
                    MetodosUsados.mostrarMensagem(it1,produto.titulo.toString()+" removido do Carrinho")

                    txtAddItemCart.text = "Adicionar"
                    relativeAddItemCart.isEnabled = true
                    txtProdQuantity.text = quantity.toString()

                }
                else{
                    txtProdQuantity.text = quantity.toString()
                }


            }

        }

        imgAddItem.setOnClickListener{
            context?.let { it1 ->
                quantity++
                if (quantity>0 && quantity >=10){

                    quantity = 10
                    txtProdQuantity.text = quantity.toString()
                    MetodosUsados.mostrarMensagem(it1,"Atingiu o limite do item")
                }
                else{
                    txtProdQuantity.text = quantity.toString()
                }
                txtAddItemCart.text = "Adicionado"
                relativeAddItemCart.isEnabled = false

            }

        }

        relativeAddItemCart.setOnClickListener {
            context?.let { it1 ->
//                val drawBkg:Drawable
//                drawBkg = context.resources.getDrawable(R.drawable.)

                if (quantity <= 0){
                    quantity = 1
                    txtProdQuantity.text = quantity.toString()
                    MetodosUsados.mostrarMensagem(it1,produto.titulo.toString()+" adicionado ao Carrinho")
                    txtAddItemCart.text = "Adicionado"
                    relativeAddItemCart.isEnabled = false





                }



            }
        }

    }



    override fun onDestroyView() {
        EventBus.getDefault().removeAllStickyEvents()
        super.onDestroyView()
        _binding = null
    }
}