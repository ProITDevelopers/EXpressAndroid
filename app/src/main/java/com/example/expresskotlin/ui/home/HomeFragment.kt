package com.example.expresskotlin.ui.home

import android.Manifest
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.expresskotlin.R
import com.example.expresskotlin.adapters.home.HomeMainAdapter
import com.example.expresskotlin.adapters.viewpager.ViewPagerAdapterSlider
import com.example.expresskotlin.databinding.FragmentHomeBinding
import com.example.expresskotlin.eventbus.SearchClick
import com.example.expresskotlin.helpers.Common
import com.example.expresskotlin.helpers.LoadData
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import de.hdodenhof.circleimageview.CircleImageView
import org.greenrobot.eventbus.EventBus
import java.util.*

class HomeFragment : Fragment() {

    private var TAG = "TAG_HomeFragment"
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!



    lateinit var imageView_avatar_toolbar: CircleImageView
    lateinit var txtMyLocation: TextView
    lateinit var imgSearch: ImageView


    lateinit var viewPager: ViewPager
    lateinit var wormDotsIndicator: WormDotsIndicator
//    private var sliderDotspanel: LinearLayout? = null
    private var dotscount:Int = 0

    private var imagesTopList = intArrayOf(R.drawable.login, R.drawable.registrar, R.drawable.grill)
    private var slideHandler = Handler()
    private var TIME_DELAY : Long = 5000 // Slide duration 5 seconds


    private lateinit var mRecyclerView: RecyclerView

    private lateinit var  fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private lateinit var getMyEndereco: String
    private  lateinit var mLocationRequest: LocationRequest
    private val UPDATE_INTERVAL = 5000 // 5 secs
    private val FATEST_INTERVAL = 3000
    private val DISPLACEMENT = 10
    private var mLocationPermissionGranted = false
    private val permissionsArray = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION)



    //DIALOG_LAYOUT_ALERTA_GPS
    private lateinit var dialogLayoutAlertDialog: Dialog
    private lateinit var txtAlertTitle : TextView
    private lateinit var txtAlertMsg : TextView
    private lateinit var dialog_btn_cancel : Button
    private lateinit var dialog_btn_ok : Button

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initViews()
        setUpViews()

        return root
    }




    private fun initViews() {
        val mToolBar: Toolbar = binding.toolbar
//        (activity as AppCompatActivity).setSupportActionBar(mToolBar)
//        if (((activity as AppCompatActivity)).supportActionBar != null) {
//            (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
//            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
//        }

        imageView_avatar_toolbar = binding.imageViewAvatarToolbar
        txtMyLocation = binding.txtMyLocation
        imgSearch = binding.imgSearch


        viewPager = binding.viewPager
        wormDotsIndicator = binding.wormDotsIndicator
//        sliderDotspanel = binding.SliderDots;
        mRecyclerView = binding.recyclerView

        imgSearch.setOnClickListener {
            EventBus.getDefault().postSticky(SearchClick(true))
        }

        //-------------------------------------------------------------//
        //-------------------------------------------------------------//
        //DIALOG_LAYOUT_ALERTA_GPS
        dialogLayoutAlertDialog = context?.let { Dialog(it) }!!
        dialogLayoutAlertDialog.setContentView(R.layout.layout_alert_popup)
        dialogLayoutAlertDialog.setCancelable(false)
        if (dialogLayoutAlertDialog.window!=null)
            dialogLayoutAlertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        txtAlertTitle = dialogLayoutAlertDialog.findViewById(R.id.txtAlertTitle)
        txtAlertMsg = dialogLayoutAlertDialog.findViewById(R.id.txtAlertMsg)
        dialog_btn_cancel = dialogLayoutAlertDialog.findViewById(R.id.dialog_btn_cancel)
        dialog_btn_ok = dialogLayoutAlertDialog.findViewById(R.id.dialog_btn_ok)
    }

    private fun setUpViews() {
        slideAnimate()
        setUpRecyclerView()
        setUpMyLocation()
    }



    private fun slideAnimate() {
//        sliderDotspanel?.removeAllViews()

        val viewPagerAdapter = context?.let { ViewPagerAdapterSlider(it, imagesTopList) }
        viewPager.adapter = viewPagerAdapter


        wormDotsIndicator.attachTo(viewPager)

        dotscount = viewPagerAdapter!!.count


        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {

                slideHandler.removeCallbacks(sliderRunnable)
                slideHandler.postDelayed(
                    sliderRunnable,
                    TIME_DELAY
                ) // Slide duration 5 seconds

                if (position + 1 == dotscount) {
                    slideHandler.postDelayed(
                        runnable,
                        TIME_DELAY
                    ) // Slide duration 5 seconds
                }


            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })
    }

    private fun setUpRecyclerView() {
        val homeMainAdapter = context?.let { HomeMainAdapter(it) }
        homeMainAdapter?.setData(LoadData.getAllMenuCategoriaList())

        val myLinearLayoutManager = object : LinearLayoutManager(context,RecyclerView.VERTICAL,false) {
            override fun canScrollVertically(): Boolean {
                return true
            }
        }
//        mRecyclerView?.setHasFixedSize(true)
        mRecyclerView.layoutManager = myLinearLayoutManager
        mRecyclerView.adapter = homeMainAdapter
    }

    private fun setUpMyLocation() {
        fusedLocationProviderClient = context?.let {
            LocationServices.getFusedLocationProviderClient(
                it
            )
        }!!


        if(checkMapServices()){
            if(mLocationPermissionGranted){
                getMyLoCation()
            }else{

                getLocationPermission()
            }
        }
    }


    private fun getMyLoCation() {
        buildLocationCallBack()
        createLocationRequest()
        displayLocation()
    }


    private fun buildLocationCallBack() {

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                Common.mLastLocation = locationResult.locations[locationResult.locations.size - 1] //Get last location
                displayLocation()
            }
        }

    }

    private fun displayLocation() {

        if (activity?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_COARSE_LOCATION)
            } != PackageManager.PERMISSION_GRANTED &&
            activity?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION)
            } != PackageManager.PERMISSION_GRANTED) {
            return
        }


        fusedLocationProviderClient.lastLocation.addOnSuccessListener {

            if (it!=null){
                Common.mLastLocation = it
                val myAddress = LatLng(it.latitude, it.longitude)
                getMyEndereco = getMyAddress(myAddress)

                Log.d(TAG, String.format("Your location was changed : %f / %f",
                    it.latitude, it.longitude))

                txtMyLocation.text = getMyEndereco
//                txtMyLocation.visibility = View.VISIBLE



            }else{
                Log.d(TAG, "Can not get your location with it.lastLocation")


            }


        }


    }

    private fun createLocationRequest() {
        mLocationRequest = LocationRequest()
        mLocationRequest.interval = UPDATE_INTERVAL.toLong()
        mLocationRequest.fastestInterval = FATEST_INTERVAL.toLong()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.smallestDisplacement = DISPLACEMENT.toFloat()
    }

    private fun checkMapServices():Boolean{
        if(isServicesOK()){
            if(isMapsEnabled()){
                return true
            }
        }
        return false
    }

    private fun isMapsEnabled():Boolean{

        val manager: LocationManager = (context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager)

        return if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            buildAlertMessageNoGps()
            false
        }else{
            true
        }

    }

    private fun getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */



        if (activity?.let {
                ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_COARSE_LOCATION)
            } == PackageManager.PERMISSION_GRANTED &&
            activity?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION)
            } == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true
            Log.d(TAG, "getLocationPermission: Granted")

            getMyLoCation()




        } else {
            Log.d(TAG, "getLocationPermission: Need that permission")
            activity?.let {
                ActivityCompat.requestPermissions(
                    it,
                    permissionsArray,
                    Common.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION)
            }
        }

    }

    private fun isServicesOK():Boolean{
        Log.d(TAG, "isServicesOK: checking google services version")

        val available: Int = context?.let {
            GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(
                it
            )
        }!!

        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working")
            return true
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it")
            val dialog = activity?.let { GoogleApiAvailability.getInstance().getErrorDialog(it, available, Common.ERROR_DIALOG_REQUEST) }
            dialog?.show()

        }else{
            Toast.makeText(context, "Você não pode fazer solicitações de localização.", Toast.LENGTH_SHORT).show()

        }
        return false
    }

    private fun getMyAddress(location:LatLng) : String {
        var address =""
        try {
            val geo = Geocoder(context, Locale.getDefault())
            val addresses:List<Address> = geo.getFromLocation(location.latitude, location.longitude, 1)
            if (addresses.isEmpty()) {
                Log.d("TAG_Mapa", "Waiting for Location")

            }
            else {
                address = addresses.get(0).getAddressLine(0)
            }
        }
        catch (e:Exception) {
            e.printStackTrace() // getFromLocation() may sometimes fail
        }

        return address
    }

    private fun buildAlertMessageNoGps() {

        txtAlertTitle.text = ""
        txtAlertTitle.visibility = View.GONE
        txtAlertMsg.text = getString(R.string.msg_ligar_gps)
        dialog_btn_cancel.setOnClickListener {
            dialogLayoutAlertDialog.cancel()
        }

        dialog_btn_ok.setOnClickListener {
            dialogLayoutAlertDialog.cancel()
            val enableGpsIntent = Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivityForResult(enableGpsIntent, Common.PERMISSIONS_REQUEST_ENABLE_GPS)

//            resultLauncher.launch(enableGpsIntent)
        }

        if (!dialogLayoutAlertDialog.isShowing)
            dialogLayoutAlertDialog.show()



    }



    @Deprecated("Deprecated in Java",
        ReplaceWith(
            "super.onRequestPermissionsResult(requestCode, permissions, grantResults)",
            "androidx.fragment.app.Fragment")
    )
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
                                            grantResults: IntArray
    ) {

        mLocationPermissionGranted = false
        Log.d(TAG, "onRequestPermissionsResult: Need that permission")
        if (requestCode == Common.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION){
            // If request is cancelled, the result arrays are empty.
            if (grantResults.isNotEmpty()
                && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true

                Log.d(TAG, "onRequestPermissionsResult: Granted")



            }


        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    @Deprecated("Deprecated in Java", ReplaceWith(
        "super.onActivityResult(requestCode, resultCode, data)",
        "androidx.fragment.app.Fragment"
    )
    )
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Common.PERMISSIONS_REQUEST_ENABLE_GPS){

            Log.d(TAG, "onActivityResult: data "+data.toString())

            if(mLocationPermissionGranted){
                getMyLoCation()
            }
            else{
                getLocationPermission()
            }



        }
    }

//    var resultLauncher = registerForActivityResult(
//        ActivityResultContracts.StartActivityForResult()) { result ->
//        if (result.resultCode == Activity.RESULT_OK) {
//            // parse result and perform action
//        }
//    }

    private val sliderRunnable = Runnable {
        viewPager.currentItem = viewPager.currentItem + 1
    }

    private val runnable = Runnable {
        viewPager.currentItem = 0
    }


    override fun onResume() {
        Log.d(TAG, "onResume:")
        super.onResume()
        slideHandler.postDelayed(
            sliderRunnable,
            TIME_DELAY
        ) // Slide duration 5 seconds


//        if(checkMapServices()){
//            if(mLocationPermissionGranted){
//                getMyLoCation()
//            }else{
//                getLocationPermission()
//            }
//        }



//        if(checkMapServices()){
//            if(mLocationPermissionGranted){
//                getMyLoCation()
//            }
//            else{
//                getLocationPermission()
//
//            }
//        }



    }

    override fun onPause() {
        slideHandler.removeCallbacks(sliderRunnable)
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}