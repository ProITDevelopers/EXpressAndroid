package com.example.expresskotlin.adapters.estabelecimento

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.expresskotlin.R
import com.facebook.shimmer.ShimmerFrameLayout

import com.example.expresskotlin.eventbus.ProdutoClick
import com.example.expresskotlin.models.Produtos
import org.greenrobot.eventbus.EventBus
import java.util.*


class EstabMainChildAdapter(context: Context) : RecyclerView.Adapter<EstabMainChildAdapter.ViewHolder>() {

    var context: Context?=null
    init {
        this.context = context
    }

    var estabTitulo : String =""
    var produtosList= ArrayList<Produtos>()


    fun setData(estabTitulo:String, produtosList: ArrayList<Produtos>){
        this.estabTitulo = estabTitulo
        this.produtosList = produtosList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cat_child_estab, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var produtos = produtosList[position]
        // sets the text to the textview from our itemHolder class



        context?.let {
            Glide.with(it).asBitmap()
                .load(produtos.imgUrl)
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
                        holder.shimmerFrameLayout.stopShimmer()
                        return false
                    }

                    override fun onResourceReady(
                        resource: Bitmap?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        holder.shimmerFrameLayout.visibility = View.GONE
                        return false
                    }

                })
                .into(holder.imgProd)


        }


        holder.txtProdTitle.text = produtos.titulo
        holder.txtProdPrice.text = StringBuilder(produtos.preco.toString()).append(" Akz").toString()


        holder.cardView.setOnClickListener {
            context?.let { it1 ->
//                MetodosUsados.mostrarMensagem(it1,produtos.titulo.toString())
                EventBus.getDefault().postSticky(ProdutoClick(true, estabTitulo,produtos))
            }
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        if (produtosList!=null && produtosList.size>0)
            return produtosList.size
        else
            return 0
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val cardView: CardView = itemView.findViewById(R.id.cardView)
        val imgProd: ImageView = itemView.findViewById(R.id.imgProd)
        val shimmerFrameLayout: ShimmerFrameLayout = itemView.findViewById(R.id.shimmerFrameLayout)
        val txtProdTitle: TextView = itemView.findViewById(R.id.txtProdTitle)
        val txtProdPrice: TextView = itemView.findViewById(R.id.txtProdPrice)

    }

}