package com.example.expresskotlin.adapters

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
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
import com.example.expresskotlin.helpers.MetodosUsados
import com.example.expresskotlin.models.CartItem
import com.facebook.shimmer.ShimmerFrameLayout


class CarrinhoAdapter(context: Context) : RecyclerView.Adapter<CarrinhoAdapter.ViewHolder>() {

    var context: Context?=null
//    var quantity: Int = 0
    init {
        this.context = context
    }

    var cartItemList= ArrayList<CartItem>()


    fun setData(cartItemList: ArrayList<CartItem>){
        this.cartItemList = cartItemList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var cartItem = cartItemList[position]
        // sets the text to the textview from our itemHolder class




        context?.let {
            Glide.with(it).asBitmap()
                .load(cartItem.produto.imgUrl)
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


        holder.txtProdTitle.text = cartItem.produto.titulo
        holder.txtProdPrice.text = StringBuilder(cartItem.produto.preco.toString()).append(" Akz").toString()
        holder.txtProdQuantity.text = cartItem.cartItemQuantity.toString()

        holder.cardView.setOnClickListener {
            context?.let { it1 ->
                MetodosUsados.mostrarMensagem(it1,cartItem.produto.titulo.toString())

            }
        }

        holder.imgRemoveItem.setOnClickListener{
            context?.let { it1 ->
                cartItem.cartItemQuantity--
                if (cartItem.cartItemQuantity<=0){
                    cartItem.cartItemQuantity = 0


                    cartItemList.removeAt(position)


                }
                else{

                    holder.txtProdQuantity.text = cartItem.cartItemQuantity.toString()
                }
                notifyDataSetChanged()
            }

        }

        holder.imgAddItem.setOnClickListener{
            context?.let { it1 ->
                cartItem.cartItemQuantity++
                if (cartItem.cartItemQuantity>0 && cartItem.cartItemQuantity >=10){

                    cartItem.cartItemQuantity = 10
                    holder.txtProdQuantity.text = cartItem.cartItemQuantity.toString()
                    MetodosUsados.mostrarMensagem(it1,"Atingiu o limite do item")
                }
                else{
                    holder.txtProdQuantity.text = cartItem.cartItemQuantity.toString()
                }
                notifyDataSetChanged()
            }

        }

        holder.txtRemoveItemCart.setOnClickListener {
            context?.let { it1 ->

                cartItemList.removeAt(position)
                notifyDataSetChanged()


            }
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        if (cartItemList!=null && cartItemList.size>0)
            return cartItemList.size
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
        val txtProdDesc: TextView = itemView.findViewById(R.id.txtProdDesc)

        val linearProdCount: LinearLayout = itemView.findViewById(R.id.linearProdCount)
        val imgRemoveItem: ImageView = itemView.findViewById(R.id.imgRemoveItem)
        val txtProdQuantity: TextView = itemView.findViewById(R.id.txtProdQuantity)
        val imgAddItem: ImageView = itemView.findViewById(R.id.imgAddItem)
        val txtRemoveItemCart: TextView = itemView.findViewById(R.id.txtRemoveItemCart)
    }

}