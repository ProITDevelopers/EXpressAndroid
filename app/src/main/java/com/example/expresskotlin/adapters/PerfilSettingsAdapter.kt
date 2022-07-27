package com.example.expresskotlin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.expresskotlin.R
import com.example.expresskotlin.eventbus.PerfilSettingsClick
import com.example.expresskotlin.models.IconTitle
import org.greenrobot.eventbus.EventBus


class PerfilSettingsAdapter(context: Context) : RecyclerView.Adapter<PerfilSettingsAdapter.ViewHolder>() {

    var context: Context?=null
    init {
        this.context = context
    }

    var iconTitleList= ArrayList<IconTitle>()


    fun setData(iconTitleList: ArrayList<IconTitle>){
        this.iconTitleList = iconTitleList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_perfil_settings, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var iconTitle = iconTitleList[position]
        // sets the text to the textview from our itemHolder class

        holder.txtTitle.text = iconTitle.titulo
        iconTitle.imgResource?.let { holder.imgIcon.setImageResource(it) }



        holder.cardView.setOnClickListener {
            context?.let { it1 ->
//                MetodosUsados.mostrarMensagem(it1,iconTitle.titulo)
                EventBus.getDefault().postSticky(PerfilSettingsClick(true, iconTitle.titulo,position))
            }
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        if (iconTitleList!=null && iconTitleList.size>0)
            return iconTitleList.size
        else
            return 0
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val cardView: CardView = itemView.findViewById(R.id.cardView)
        val imgIcon: ImageView = itemView.findViewById(R.id.imgIcon)
        val txtTitle: TextView = itemView.findViewById(R.id.txtTitle)

    }

}