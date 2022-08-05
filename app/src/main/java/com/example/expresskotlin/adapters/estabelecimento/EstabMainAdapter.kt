package com.example.expresskotlin.adapters.estabelecimento


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expresskotlin.R
import com.example.expresskotlin.eventbus.EstabCatClick
import com.example.expresskotlin.models.MenuCatProdutos
import org.greenrobot.eventbus.EventBus


class EstabMainAdapter(context: Context) : RecyclerView.Adapter<EstabMainAdapter.ViewHolder>() {


    var context: Context
    init {
        this.context = context

    }

    var estabTitulo : String=""
    var menuCatProdutosList= ArrayList<MenuCatProdutos>()


    fun setData(estabTitulo:String,menuCatProdutosList: ArrayList<MenuCatProdutos>){
        this.estabTitulo = estabTitulo
        this.menuCatProdutosList = menuCatProdutosList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_categoria_home, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val section = menuCatProdutosList[position]
        val sectionTitle = section.titulo
        val produtosList = section.produtosList

        // sets the text to the textview from our itemHolder class
        holder.sectionNameTextView.text = sectionTitle
        val childRecyclerAdapter = EstabMainChildAdapter(this.context)
        childRecyclerAdapter.setData(estabTitulo,produtosList)
//        holder.childRecyclerView.setHasFixedSize(true)
        holder.childRecyclerView.layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
        holder.childRecyclerView.adapter = childRecyclerAdapter

        holder.sectionNameTextView.setOnClickListener {
            EventBus.getDefault().postSticky(EstabCatClick(true, estabTitulo,section))
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        if (menuCatProdutosList!=null && menuCatProdutosList.size>0)
            return menuCatProdutosList.size
        else
            return 0
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val sectionNameTextView: TextView = itemView.findViewById(R.id.sectionNameTextView)
        val childRecyclerView: RecyclerView = itemView.findViewById(R.id.childRecyclerView)

    }

}