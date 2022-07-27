package com.example.expresskotlin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.expresskotlin.R
import com.example.expresskotlin.helpers.MetodosUsados
import com.example.expresskotlin.models.MeusPedidos


class MeusPedidosAdapter(context: Context) : RecyclerView.Adapter<MeusPedidosAdapter.ViewHolder>() {

    var context: Context?=null

    init {
        this.context = context
    }

    var meusPedidosList= ArrayList<MeusPedidos>()


    fun setData(meusPedidosList : ArrayList<MeusPedidos>){
        this.meusPedidosList = meusPedidosList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_meus_pedidos, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var meusPedidos = meusPedidosList[position]
        // sets the text to the textview from our itemHolder class


        holder.txtTitle.text = meusPedidos.titulo
        holder.txtData.text = meusPedidos.data
        holder.txtDesc.text = meusPedidos.estado


        holder.cardView.setOnClickListener {
            context?.let { it1 ->
                MetodosUsados.mostrarMensagem(it1,meusPedidos.titulo.toString())

            }
        }





    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        if (meusPedidosList!=null && meusPedidosList.size>0)
            return meusPedidosList.size
        else
            return 0
    }



    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val cardView: CardView = itemView.findViewById(R.id.cardView)
        val txtTitle: TextView = itemView.findViewById(R.id.txtTitle)
        val txtData: TextView = itemView.findViewById(R.id.txtData)
        val txtDesc: TextView = itemView.findViewById(R.id.txtDesc)

    }

}