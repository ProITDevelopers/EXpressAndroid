package com.example.expresskotlin.ui.mapa


import android.Manifest
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.expresskotlin.R
import com.example.expresskotlin.databinding.FragmentMapaBinding
import com.example.expresskotlin.helpers.Common
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import java.util.*


private const val ARG_PARAM1 = "destino"

class MapaFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener{

    private var TAG = "TAG_MapaFragment"
    private var _binding: FragmentMapaBinding? = null
    private val binding get() = _binding!!

    private lateinit var  fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback

    private lateinit var mapFragment: SupportMapFragment
    private lateinit var mMap: GoogleMap
    private lateinit var mUserMarker: Marker
    private var mDestinationMarker: Marker?=null
    private lateinit var getMyEndereco: String
    private lateinit var getMyDestination: String





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

    private var mDestinationMarkerTitle: String? = null
    private var subTotalPrice:Double= 0.0
    private var totalDeTudoPrice:Double= 0.0

    private lateinit var icon_user_marker:Bitmap
    private lateinit var icon_destination_marker:Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mDestinationMarkerTitle = it.getString(ARG_PARAM1)
            subTotalPrice = it.getDouble("subTotalPrice",0.0)
            totalDeTudoPrice = it.getDouble("totalDeTudoPrice",0.0)

            Log.d(TAG, "onCreate:\n mDestinationMarkerTitle - $mDestinationMarkerTitle\n subTotalPrice - $subTotalPrice\n totalDeTudoPrice - $totalDeTudoPrice")
        }
    }


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {


        _binding = FragmentMapaBinding.inflate(inflater, container, false)
        val root: View = binding.root


        initViews()

        return root
    }

    private fun initViews() {

//        context?.let {
//            val user_marker  = AppCompatResources.getDrawable(it,R.drawable.ic_baseline_user_marker_24)
//            icon_user_marker = (user_marker as BitmapDrawable).bitmap
//
//            val destination_marker  = AppCompatResources.getDrawable(it,R.drawable.ic_baseline_destination_marker_24)
//            icon_destination_marker = (destination_marker as BitmapDrawable).bitmap
//        }



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

        mapFragment = (childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment)
        mapFragment.getMapAsync(this)


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
                val myAddress = LatLng(it.latitude,it.longitude)
                getMyEndereco = getMyAddress(myAddress)

                Log.d(TAG, String.format("Your location was changed : %f / %f",
                    it.latitude, it.longitude))

                loadAllAvailableDriver(LatLng(it.latitude, it.longitude))



            }else{
                Log.d(TAG, "Can not get your location")

            }


        }


    }

    @Suppress("DEPRECATION")
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
            getMyLoCation()


        } else {
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

    private fun loadAllAvailableDriver(location: LatLng) {


        mMap.clear()

        mUserMarker = mMap.addMarker(MarkerOptions()
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
            .position(location)
            .title("Minha posição")
            .snippet(getMyEndereco)
        )!!

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 15.0f))

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
                address = addresses[0].getAddressLine(0)
            }
        }
        catch (e:Exception) {
            e.printStackTrace() // getFromLocation() may sometimes fail
        }

        return address
    }


    override fun onMapReady(googleMap: GoogleMap) {

        val nightModeFlags = (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK)

        if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {

            Log.d(TAG, "onMapReady: darkmodeOn")
            try {

                val isSucess:Boolean = googleMap.setMapStyle(
                    activity?.let { MapStyleOptions.loadRawResourceStyle(it, R.raw.mapstyle_night) }
                )

                if (!isSucess)
                    Log.d(TAG, "onMapReady_ERROR: Map style load failed !!!")
            } catch ( ex: Resources.NotFoundException) {
                ex.printStackTrace()
            }
        }else{
            Log.d(TAG, "onMapReady: darkmodeOff")

        }
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isZoomGesturesEnabled = true


        mMap.setOnMapClickListener {
            if (mDestinationMarkerTitle!=null){
                if (mDestinationMarkerTitle.equals("Destino")){
                    getMyDestination = getMyAddress(it)
                    if (mDestinationMarker!=null)
                        mDestinationMarker?.remove()

//                    val icon_destination_marker: Bitmap = BitmapFactory.decodeResource(requireContext().resources,
//                        R.drawable.ic_baseline_destination_marker_24)
                    mDestinationMarker = mMap.addMarker(MarkerOptions()
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                        .position(it)
                        .title(mDestinationMarkerTitle)
                        .snippet(getMyDestination)
                    )!!

                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(it, 15.0f))
                }
            }
        }
        mMap.setOnInfoWindowClickListener(this)

        if (activity?.let { ActivityCompat.checkSelfPermission(it, Manifest.permission.ACCESS_FINE_LOCATION) } != PackageManager.PERMISSION_GRANTED &&
            activity?.let { ActivityCompat.checkSelfPermission(it, Manifest.permission.ACCESS_COARSE_LOCATION) } != PackageManager.PERMISSION_GRANTED) {
            return
        }


        try {
            fusedLocationProviderClient.requestLocationUpdates(mLocationRequest, locationCallback, Looper.myLooper())
        } catch (e:Exception) {
            e.printStackTrace()
        }
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
        if (requestCode == Common.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION){
            // If request is cancelled, the result arrays are empty.
            if (grantResults.isNotEmpty()
                && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true

            }
            else{
                Toast.makeText(context, "Need that permission", Toast.LENGTH_SHORT).show()
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
            Log.d(TAG, "onActivityResult: requestCode")
            if(mLocationPermissionGranted){
                getMyLoCation()
            }
            else{
                getLocationPermission()
            }
        }
    }


    override fun onResume() {
//        if(checkMapServices()){
//            if(mLocationPermissionGranted){
//                getMyLoCation()
//            }
//            else{
//                getLocationPermission()
//            }
//
//        }
        super.onResume()
    }

    override fun onDestroyView() {

        super.onDestroyView()
        _binding = null
    }

    override fun onInfoWindowClick(marker: Marker) {


        if (mDestinationMarkerTitle!=null){
            if (mDestinationMarkerTitle.equals("Destino")){
                val endereco:String
                if (marker.title.equals("Minha posição")){
                    Log.d(TAG, "onInfoWindowClick: "+marker.title)
//            Toast.makeText(context, "onInfoWindowClick: "+marker.title, Toast.LENGTH_SHORT).show()
                    endereco = getMyEndereco
                }else{
                    Log.d(TAG, "onInfoWindowClick: "+marker.title)
                    endereco = getMyDestination
                }

                val bundle = Bundle()
                bundle.putString("endereco", endereco)
                bundle.putDouble("latitude", marker.position.latitude)
                bundle.putDouble("longitude", marker.position.longitude)
                bundle.putDouble("subTotalPrice", subTotalPrice)
                bundle.putDouble("totalDeTudoPrice", totalDeTudoPrice)
                val navController = (activity as AppCompatActivity).findNavController(R.id.nav_host_fragment_activity_main)
//                navController.popBackStack(R.id.checkoutFragment, false)
                navController.navigate(R.id.checkoutFragment, bundle)
//                navController.clearBackStack(R.id.checkoutFragment)

            }
        }

    }





}