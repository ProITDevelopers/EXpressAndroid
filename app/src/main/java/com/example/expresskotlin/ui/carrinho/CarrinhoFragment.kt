package com.example.expresskotlin.ui.carrinho

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expresskotlin.R
import com.example.expresskotlin.adapters.CarrinhoAdapter
import com.example.expresskotlin.databinding.FragmentCarrinhoBinding
import com.example.expresskotlin.eventbus.CartCheckClick
import com.example.expresskotlin.helpers.MetodosUsados
import com.example.expresskotlin.models.CartItem
import com.example.expresskotlin.models.Produtos
import org.greenrobot.eventbus.EventBus


class CarrinhoFragment : Fragment() {

    private var _binding: FragmentCarrinhoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object {
        var cartItemList = ArrayList<CartItem>()
    }
    private lateinit var mRecyclerView : RecyclerView

    private lateinit var txtSub : TextView
    private lateinit var txtSubtotal : TextView

    private lateinit var txtTax : TextView
    private lateinit var txtTaxaEntrega : TextView

    private lateinit var separator_view : View
    private lateinit var txtTot : TextView
    private lateinit var txtTotal : TextView

    private lateinit var btnCheckout : Button
    private var subTotalPrice:Double = 0.0
    private var totalDeTudoPrice = subTotalPrice

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val carrinhoViewModel =
                ViewModelProvider(this).get(CarrinhoViewModel::class.java)

        _binding = FragmentCarrinhoBinding.inflate(inflater, container, false)
        val root: View = binding.root
        loadCartItems()
        val textView: TextView = binding.textCarrinho
        carrinhoViewModel.text.observe(viewLifecycleOwner) {
            textView.text = cartItemList.toString()
        }
        initViews()
        return root
    }

    private fun initViews() {
        val mToolBar: Toolbar = binding.toolbar
        (activity as AppCompatActivity).setSupportActionBar(mToolBar)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_carrinho)
        if (((activity as AppCompatActivity)).supportActionBar != null) {
            (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(true)
        }
        mRecyclerView = binding.recyclerView

        txtSub = binding.txtSub
        txtSubtotal = binding.txtSubtotal

        txtTax = binding.txtTax
        txtTaxaEntrega = binding.txtTaxaEntrega
        separator_view = binding.separatorView

        txtTot = binding.txtTot
        txtTotal = binding.txtTotal

        btnCheckout = binding.btnCheckout
        setUpRecyclerView()

        btnCheckout.setOnClickListener{
            EventBus.getDefault().postSticky(CartCheckClick(true, subTotalPrice,totalDeTudoPrice))
        }

    }

    private fun setUpRecyclerView() {
        val cartAdapter = context?.let { CarrinhoAdapter(it) }
        cartAdapter?.setData(cartItemList)

        val myLinearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
//        mRecyclerView?.setHasFixedSize(true)
        mRecyclerView.layoutManager = myLinearLayoutManager
        mRecyclerView.adapter = cartAdapter
        setCartSubTotal(cartItemList)

        cartAdapter?.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                // ...
                Log.d("TAG_cart", "onChanged: registerAdapterDataObserver")
                setCartSubTotal(cartAdapter.cartItemList)
            }
        })

    }

    private fun setCartSubTotal(cartItemList: ArrayList<CartItem>) {
        var itemCount = 0
        for (cartItem in cartItemList) {
            itemCount += cartItem.cartItemQuantity
        }
//        total_Items_Cart = itemCount;
        val subTotalCart = MetodosUsados.getCartPrice(cartItemList)

        subTotalPrice = subTotalCart.toDouble()
        totalDeTudoPrice = subTotalPrice + 500 //TaxaEntrega

        if(subTotalPrice<=0){
            hideViews()
        }else{
            showViews()
        }

        txtSubtotal.text = StringBuilder("")
            .append(MetodosUsados.formatPrice(subTotalPrice)).append(" Akz").toString()

        txtTotal.text = StringBuilder()
            .append(MetodosUsados.formatPrice(totalDeTudoPrice)).append(" Akz").toString()
    }

    private fun showViews() {
        txtSub.visibility = View.VISIBLE
        txtSubtotal.visibility = View.VISIBLE
        txtTax.visibility = View.VISIBLE
        txtTaxaEntrega.visibility = View.VISIBLE
        separator_view.visibility = View.VISIBLE
        txtTot.visibility = View.VISIBLE
        txtTotal.visibility = View.VISIBLE
        btnCheckout.visibility = View.VISIBLE
    }

    private fun hideViews() {
        txtSub.visibility = View.GONE
        txtSubtotal.visibility = View.GONE
        txtTax.visibility = View.GONE
        txtTaxaEntrega.visibility = View.GONE
        separator_view.visibility = View.GONE
        txtTot.visibility = View.GONE
        txtTotal.visibility = View.GONE
        btnCheckout.visibility = View.GONE

        Toast.makeText(context, "Carrinho vazio", Toast.LENGTH_SHORT).show()
    }

    private fun loadCartItems(){
        cartItemList.clear()
        val produtosList = ArrayList<Produtos>()

        //===============================HAMBURGERS=================================================
        produtosList.add(
            Produtos(1,"Mad Burger",4500.00,"burguer",
            "https://thumbs.dreamstime.com/b/big-grilled-chicken-burger-double-cutlet-cheese-wooden-background-side-view-close-up-208658240.jpg")
        )


        produtosList.add(
            Produtos(10,"Coca-Cola",600.00,"refrigerante",
            "https://www.drogariaminasbrasil.com.br/media/catalog/product/r/e/refrigerante_coca_cola_lata_350ml.jpg")
        )


        produtosList.add(
            Produtos(19,"Slicy",300.00,"pizza",
            "https://pizzariadesucesso.com/wp-content/uploads/2018/05/pepperoni-pizza.jpg")
        )


        produtosList.add(
            Produtos(26,"Creamberry",240.00,"gelado",
            "https://historicvirginiatravel.com/wp-content/uploads/2022/03/ice-cream-sundae.jpg")
        )


        produtosList.add(
            Produtos(30,"Frango Frito",540.00,"churrasco",
            "https://media.socialdeal.nl/bedrijf/bbq-en-grill-hoorn-20011015361090.jpg")
        )


        produtosList.add(
            Produtos(36,"Bud Light",640.00,"cerveja",
            "https://www.beeruniversestore.com/wp-content/uploads/2020/12/brew_hero-image-scaled-e1620826370837.jpg")
        )



        for (produto in produtosList){
            cartItemList.add(CartItem(produto,MetodosUsados.getRandomNumbers(1,10)))
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}