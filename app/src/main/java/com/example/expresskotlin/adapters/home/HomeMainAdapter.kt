package com.example.expresskotlin.adapters.home


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expresskotlin.R
import com.example.expresskotlin.eventbus.CategoriaEstabClick
import com.example.expresskotlin.models.MenuCategoria
import org.greenrobot.eventbus.EventBus


class HomeMainAdapter(context: Context) : RecyclerView.Adapter<HomeMainAdapter.ViewHolder>() {


    var context: Context
    init {
        this.context = context
    }
    var menuCategoriaList= ArrayList<MenuCategoria>()


    fun setData(menuCategoriaList: ArrayList<MenuCategoria>){
        this.menuCategoriaList = menuCategoriaList
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

        var section = menuCategoriaList[position]
        var sectionTitle = section.titulo
        var estabList = section.estabelecimentoList

        // sets the text to the textview from our itemHolder class
        holder.sectionNameTextView.text = sectionTitle
        val childRecyclerAdapter = context?.let { HomeMainChildAdapter(it) }
        childRecyclerAdapter?.setData(estabList)
//        holder.childRecyclerView.setHasFixedSize(true)
        holder.childRecyclerView.layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
        holder.childRecyclerView.adapter = childRecyclerAdapter

        holder.sectionNameTextView.setOnClickListener {
            context?.let { it1 ->
//                MetodosUsados.mostrarMensagem(it1,sectionTitle.toString())
                EventBus.getDefault().postSticky(CategoriaEstabClick(true, section))
            }
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        if (menuCategoriaList!=null && menuCategoriaList.size>0)
            return menuCategoriaList.size
        else
            return 0
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val sectionNameTextView: TextView = itemView.findViewById(R.id.sectionNameTextView)
        val childRecyclerView: RecyclerView = itemView.findViewById(R.id.childRecyclerView)

    }

}