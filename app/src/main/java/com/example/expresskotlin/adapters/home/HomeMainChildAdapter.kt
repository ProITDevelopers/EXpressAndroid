package com.example.expresskotlin.adapters.home

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
import com.example.expresskotlin.eventbus.EstabClick
import com.example.expresskotlin.helpers.MetodosUsados
import com.example.expresskotlin.models.Estabelecimento
import com.facebook.shimmer.ShimmerFrameLayout
import org.greenrobot.eventbus.EventBus


class HomeMainChildAdapter(context: Context) : RecyclerView.Adapter<HomeMainChildAdapter.ViewHolder>() {

    var context: Context?=null
    init {
        this.context = context
    }

    var estabelecimentoList= ArrayList<Estabelecimento>()


    fun setData(estabelecimentoList: ArrayList<Estabelecimento>){
        this.estabelecimentoList = estabelecimentoList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_categoria_child_home, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var estabelecimento = estabelecimentoList[position]
        // sets the text to the textview from our itemHolder class



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
                .into(holder.imgEstab)


        }


        holder.txtEstabTitle.text = estabelecimento.titulo
        holder.txtEstabCat.text = estabelecimento.categoria
        holder.txtEstabAddress.text = StringBuilder(estabelecimento.endereco.toString())
            .append(" | ").append(MetodosUsados.getRandomNumbers(1,5)).append(",")
            .append(MetodosUsados.getRandomNumbers(1,5)).toString()

        holder.cardView.setOnClickListener {
            context?.let { it1 ->
//                MetodosUsados.mostrarMensagem(it1,estabelecimento.titulo.toString())
                EventBus.getDefault().postSticky(EstabClick(true, estabelecimento))
            }
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        if (estabelecimentoList!=null && estabelecimentoList.size>0)
            return estabelecimentoList.size
        else
            return 0
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val cardView: CardView = itemView.findViewById(R.id.cardView)
        val imgEstab: ImageView = itemView.findViewById(R.id.imgEstab)
        val shimmerFrameLayout: ShimmerFrameLayout = itemView.findViewById(R.id.shimmerFrameLayout)
        val txtEstabTitle: TextView = itemView.findViewById(R.id.txtEstabTitle)
        val txtEstabCat: TextView = itemView.findViewById(R.id.txtEstabCat)
        val txtEstabAddress: TextView = itemView.findViewById(R.id.txtEstabAddress)
    }

}