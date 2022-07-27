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
import com.example.expresskotlin.helpers.MetodosUsados
import com.example.expresskotlin.models.MyAddress


class MyAddressAdapter(context: Context) : RecyclerView.Adapter<MyAddressAdapter.ViewHolder>() {

    var context: Context?=null
//    var quantity: Int = 0
    init {
        this.context = context
    }

    var myAddressList= ArrayList<MyAddress>()


    fun setData(myAddressList : ArrayList<MyAddress>){
        this.myAddressList = myAddressList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_my_address, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var myAddress = myAddressList[position]
        // sets the text to the textview from our itemHolder class


        holder.txtTitle.text = myAddress.titulo
        holder.txtDesc.text = myAddress.endereco


        holder.cardView.setOnClickListener {
            context?.let { it1 ->
                MetodosUsados.mostrarMensagem(it1,myAddress.titulo.toString())

            }
        }



        holder.imgUpdateItem.setOnClickListener{
            context?.let { it1 ->
//                var myAddressUpdate = myAddressList[position]
//                notifyDataSetChanged()
                MetodosUsados.mostrarMensagem(it1,"Update "+myAddress.titulo.toString())
            }

        }

        holder.imgDeleteItem.setOnClickListener {
            context?.let { it1 ->

                myAddressList.removeAt(position)
                notifyDataSetChanged()


            }
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        if (myAddressList!=null && myAddressList.size>0)
            return myAddressList.size
        else
            return 0
    }



    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val cardView: CardView = itemView.findViewById(R.id.cardView)
        val txtTitle: TextView = itemView.findViewById(R.id.txtTitle)
        val txtDesc: TextView = itemView.findViewById(R.id.txtDesc)
        val imgUpdateItem: ImageView = itemView.findViewById(R.id.imgUpdateItem)
        val imgDeleteItem: ImageView = itemView.findViewById(R.id.imgDeleteItem)

    }

}