package com.example.expresskotlin.ui.checkout

import android.app.Dialog
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.expresskotlin.R
import com.example.expresskotlin.databinding.FragmentCheckoutBinding
import com.example.expresskotlin.helpers.MetodosUsados
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import org.greenrobot.eventbus.EventBus


class CheckoutFragment : Fragment(), OnMapReadyCallback{

    private var TAG = "TAG_CheckoutFragment"
    private var _binding: FragmentCheckoutBinding? = null
    private val binding get() = _binding!!

    private lateinit var mapFragment: SupportMapFragment
    private lateinit var mMap: GoogleMap
    private var mDestinationMarker: Marker?=null
    var endereco: String?=null
    var latitude: Double?=0.0
    var longitude: Double?=0.0

    private lateinit var txtTPA: TextView
    private lateinit var txtCarteira: TextView

    private lateinit var radioBtnTPA: AppCompatRadioButton
    private lateinit var radioBtnCarteira: AppCompatRadioButton

    private lateinit var txtSub : TextView
    private lateinit var txtSubtotal : TextView

    private lateinit var txtTax : TextView
    private lateinit var txtTaxaEntrega : TextView

    private lateinit var txtDesc : TextView
    private lateinit var txtDesconto : TextView

    private lateinit var separator_view : View
    private lateinit var txtTot : TextView
    private lateinit var txtTotal : TextView

    private lateinit var btnEnviar : Button
    private var subTotalPrice:Double= 0.0
    private var totalDeTudoPrice:Double= 0.0


    //DIALOG_LAYOUT_CONFIRMAR_PEDIDO
    private lateinit var dialogLayoutConfirmarPedido: Dialog
    private lateinit var dialog_btn_gotoMap : Button
    private lateinit var dialog_btn_gotoHome : Button

    //DIALOG_LAYOUT_CONFIRMAR_PEDIDO_CODIGO
    private lateinit var dialogLayoutConfirmarPedidoCode: Dialog
    private lateinit var dialog_btn_confirm : Button
    private lateinit var dialog_txt_sendCode : TextView






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            subTotalPrice = it.getDouble("subTotalPrice",0.0)
            totalDeTudoPrice = it.getDouble("totalDeTudoPrice",0.0)

            endereco = it.getString("endereco")
            latitude = it.getDouble("latitude",0.0)
            longitude = it.getDouble("longitude",0.0)
            Log.d(TAG, "onCreate:\n subTotalPrice - $subTotalPrice\n totalDeTudoPrice - $totalDeTudoPrice\n endereco - $endereco\n latitude - $latitude\n longitude - $longitude")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentCheckoutBinding.inflate(inflater, container, false)
        val root: View = binding.root



        initViews()

        return root
    }

    private fun initViews() {
        val mToolBar: Toolbar = binding.toolbar
        (activity as AppCompatActivity).setSupportActionBar(mToolBar)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_checkout)
        if (((activity as AppCompatActivity)).supportActionBar != null) {
            (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(true)
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        mToolBar.setNavigationOnClickListener {
            val navController = (activity as AppCompatActivity).findNavController(R.id.nav_host_fragment_activity_main)
            navController.navigateUp()
        }



        txtTPA= binding.txtTPA
        txtCarteira= binding.txtCarteira

        radioBtnTPA= binding.radioBtnTPA
        radioBtnCarteira= binding.radioBtnCarteira

        txtSub = binding.txtSub
        txtSubtotal = binding.txtSubtotal

        txtTax = binding.txtTax
        txtTaxaEntrega = binding.txtTaxaEntrega

        txtDesc = binding.txtDesc
        txtDesconto = binding.txtDesconto

        separator_view = binding.separatorView

        txtTot = binding.txtTot
        txtTotal = binding.txtTotal
        btnEnviar = binding.btnEnviar



        //-------------------------------------------------------------//
        //-------------------------------------------------------------//
        //DIALOG_LAYOUT_CONFIRMAR_PROCESSO
        dialogLayoutConfirmarPedido = context?.let { Dialog(it) }!!
        dialogLayoutConfirmarPedido.setContentView(R.layout.layout_confirmar_pedido)
        dialogLayoutConfirmarPedido.setCancelable(false)
        if (dialogLayoutConfirmarPedido.window!=null)
            dialogLayoutConfirmarPedido.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog_btn_gotoMap = dialogLayoutConfirmarPedido.findViewById(R.id.dialog_btn_gotoMap)
        dialog_btn_gotoHome = dialogLayoutConfirmarPedido.findViewById(R.id.dialog_btn_gotoHome)

        //-------------------------------------------------------------//
        //-------------------------------------------------------------//
        //DIALOG_LAYOUT_CONFIRMAR_PROCESSO
        dialogLayoutConfirmarPedidoCode = context?.let { Dialog(it) }!!
        dialogLayoutConfirmarPedidoCode.setContentView(R.layout.layout_confirmar_pedido_codigo)
        dialogLayoutConfirmarPedidoCode.setCancelable(false)
        if (dialogLayoutConfirmarPedidoCode.window!=null)
            dialogLayoutConfirmarPedidoCode.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog_btn_confirm = dialogLayoutConfirmarPedidoCode.findViewById(R.id.dialog_btn_confirm)
        dialog_txt_sendCode = dialogLayoutConfirmarPedidoCode.findViewById(R.id.dialog_txt_sendCode)

        val spannableStringCode = SpannableString(getString(R.string.reenviar_o_codigo))
        spannableStringCode.setSpan(UnderlineSpan(),0,spannableStringCode.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        dialog_txt_sendCode.text = spannableStringCode

        mapFragment = (childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment)
        mapFragment.getMapAsync(this)

        setUpViews()





    }

    private fun setUpViews() {
        binding.txtMyAddress.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("destino", "Destino")
            bundle.putDouble("subTotalPrice", subTotalPrice)
            bundle.putDouble("totalDeTudoPrice", totalDeTudoPrice)

            val navController = (activity as AppCompatActivity).findNavController(R.id.nav_host_fragment_activity_main)
            navController.navigate(R.id.navigation_mapa, bundle)
        }
        txtTPA.setOnClickListener {
            radioBtnTPA.isChecked = true
        }

        radioBtnTPA.setOnCheckedChangeListener{
                buttonView, isChecked ->
            if (isChecked){
                radioBtnCarteira.isChecked=false
            }
        }

        txtCarteira.setOnClickListener {
            radioBtnCarteira.isChecked = true
        }
        radioBtnCarteira.setOnCheckedChangeListener{
                buttonView, isChecked ->
            if (isChecked){
                radioBtnTPA.isChecked=false
            }
        }

        totalDeTudoPrice -= 400//Desconto

        if(subTotalPrice<=0){
            hideViews()
        }else{
            showViews()
        }

        txtSubtotal.text = StringBuilder("")
            .append(MetodosUsados.formatPrice(subTotalPrice)).append(" Akz").toString()

        txtTotal.text = StringBuilder()
            .append(MetodosUsados.formatPrice(totalDeTudoPrice)).append(" Akz").toString()

        btnEnviar.setOnClickListener {
            if (!radioBtnTPA.isChecked && !radioBtnCarteira.isChecked){
                Toast.makeText(context, "Selecione um método de pagamento", Toast.LENGTH_SHORT).show()

            }else{
                if (radioBtnTPA.isChecked){
                    if (!dialogLayoutConfirmarPedidoCode.isShowing){
                        dialogLayoutConfirmarPedidoCode.show()
                    }
                }
                if (radioBtnCarteira.isChecked){
                    if (!dialogLayoutConfirmarPedido.isShowing){
                        dialogLayoutConfirmarPedido.show()
                    }
                }

            }

        }

        dialog_btn_confirm.setOnClickListener {
            dialogLayoutConfirmarPedidoCode.cancel()
            if (!dialogLayoutConfirmarPedido.isShowing){
                dialogLayoutConfirmarPedido.show()
            }
        }

        dialog_txt_sendCode.setOnClickListener {
            dialogLayoutConfirmarPedidoCode.cancel()
        }

        dialog_btn_gotoMap.setOnClickListener {
            Toast.makeText(context, "gotoMap", Toast.LENGTH_SHORT).show()
            dialogLayoutConfirmarPedido.cancel()
        }

        dialog_btn_gotoHome.setOnClickListener {
            Toast.makeText(context, "gotoHome", Toast.LENGTH_SHORT).show()
            dialogLayoutConfirmarPedido.cancel()
        }


    }

    private fun showViews() {
        txtSub.visibility = View.VISIBLE
        txtSubtotal.visibility = View.VISIBLE
        txtTax.visibility = View.VISIBLE
        txtTaxaEntrega.visibility = View.VISIBLE
        txtDesc.visibility = View.VISIBLE
        txtDesc.visibility = View.VISIBLE
        separator_view.visibility = View.VISIBLE
        txtTot.visibility = View.VISIBLE
        txtTotal.visibility = View.VISIBLE
        btnEnviar.visibility = View.VISIBLE
    }

    private fun hideViews() {
        txtSub.visibility = View.GONE
        txtSubtotal.visibility = View.GONE
        txtTax.visibility = View.GONE
        txtTaxaEntrega.visibility = View.GONE
        txtDesc.visibility = View.GONE
        txtDesconto.visibility = View.GONE

        separator_view.visibility = View.GONE
        txtTot.visibility = View.GONE
        txtTotal.visibility = View.GONE
        btnEnviar.visibility = View.GONE

    }

    override fun onMapReady(googleMap: GoogleMap) {

        val nightModeFlags = (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK)

        if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {

            try {

                val isSucess:Boolean = googleMap.setMapStyle(
                    activity?.let { MapStyleOptions.loadRawResourceStyle(it, R.raw.mapstyle_night) }
                )

                if (!isSucess)
                    Log.d(TAG, "onMapReady_ERROR: Map style load failed !!!")
            } catch ( ex: Resources.NotFoundException) {
                ex.printStackTrace()
            }
        }
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isZoomGesturesEnabled = true

        if (endereco!=null){
            loadAllAvailableDriver(LatLng(latitude!!,longitude!!))
        }

    }

    private fun loadAllAvailableDriver(location: LatLng) {


        mMap.clear()

        mDestinationMarker = mMap.addMarker(MarkerOptions()
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            .position(location)
            .title("Destino")
            .snippet(endereco)
        )

        binding.txtMyAddress.text = endereco
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 15.0f))

    }


    override fun onDestroyView() {
        EventBus.getDefault().removeAllStickyEvents()
        super.onDestroyView()
        _binding = null
    }
}