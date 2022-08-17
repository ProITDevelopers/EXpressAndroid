package com.example.expresskotlin.adapters.estabelecimento

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
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
import com.example.expresskotlin.eventbus.ProdutoClick
import com.example.expresskotlin.helpers.MetodosUsados
import com.example.expresskotlin.models.Produtos
import com.facebook.shimmer.ShimmerFrameLayout
import org.greenrobot.eventbus.EventBus


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

        var produto = produtosList[position]
        // sets the text to the textview from our itemHolder class



        context?.let {
            Glide.with(it).asBitmap()
                .load(produto.imgUrl)
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


        holder.txtProdTitle.text = produto.titulo
        holder.txtProdPrice.text = StringBuilder(produto.preco.toString()).append(" Akz").toString()


        holder.cardView.setOnClickListener {
            context?.let { it1 ->

                EventBus.getDefault().postSticky(ProdutoClick(true, estabTitulo,produto))
            }
        }

        if (produto.quantity>0){

            holder.txtProdQuantity.text = produto.quantity.toString()

            holder.relativeAddItemCart.visibility = View.GONE
            holder.linearProdCount.visibility = View.VISIBLE
        }

        holder.relativeAddItemCart.setOnClickListener {
            context?.let { it1 ->

//                MetodosUsados.mostrarMensagem(it1,produto.titulo.toString())
                if (produto.quantity <= 0){
                    produto.quantity = 1
                    holder.txtProdQuantity.text = produto.quantity.toString()

                    holder.relativeAddItemCart.visibility = View.GONE
                    holder.linearProdCount.visibility = View.VISIBLE
                    Log.d("TAG_prodCard", "onBindViewHolder: ${produto.titulo}"+" quantity: ${produto.quantity}")
                }



            }
        }

        holder.imgRemoveItem.setOnClickListener{
            context?.let { it1 ->
                produto.quantity--
                if (produto.quantity<=0){
                    produto.quantity = 0

                    holder.linearProdCount.visibility = View.GONE

                    holder.txtProdQuantity.text = produto.quantity.toString()
                    holder.relativeAddItemCart.visibility = View.VISIBLE
                }
                else{

                    holder.txtProdQuantity.text = produto.quantity.toString()
                }
                notifyDataSetChanged()
            }

        }

        holder.imgAddItem.setOnClickListener{
            context?.let { it1 ->
                produto.quantity++
                if (produto.quantity>0 && produto.quantity >=10){

                    produto.quantity = 10
                    holder.txtProdQuantity.text = produto.quantity.toString()
                    MetodosUsados.mostrarMensagem(it1,"Atingiu o limite do item")
                }
                else{
                    holder.txtProdQuantity.text = produto.quantity.toString()
                }
                notifyDataSetChanged()
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

        val relativeAddItemCart: RelativeLayout = itemView.findViewById(R.id.relativeAddItemCart)
        val linearProdCount: LinearLayout = itemView.findViewById(R.id.linearProdCount)
        val imgRemoveItem: ImageView = itemView.findViewById(R.id.imgRemoveItem)
        val txtProdQuantity: TextView = itemView.findViewById(R.id.txtProdQuantity)
        val imgAddItem: ImageView = itemView.findViewById(R.id.imgAddItem)

    }

}