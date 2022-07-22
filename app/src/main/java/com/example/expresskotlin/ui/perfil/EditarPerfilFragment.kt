package com.example.expresskotlin.ui.perfil

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.expresskotlin.R
import com.example.expresskotlin.databinding.FragmentEditarPerfilBinding

import org.greenrobot.eventbus.EventBus
import java.util.*


class EditarPerfilFragment : Fragment() {

    private var _binding: FragmentEditarPerfilBinding? = null
    private val binding get() = _binding!!
    private var mToolbarTitle: String? = null

    private lateinit var txtDataNasc: TextView
    private lateinit var txtMasculino: TextView
    private lateinit var txtFeminino: TextView

    private lateinit var radioBtnMasc: AppCompatRadioButton
    private lateinit var radioBtnFem: AppCompatRadioButton

    private lateinit var bi:String
    private lateinit var dataNasc:String
    private lateinit var data_toShow:String
    private lateinit var date:String

    private  var minDateMilliseconds:Long = 0
    private  var maxDateMilliseconds:Long = 0

    private lateinit var mDateSetListener: DatePickerDialog.OnDateSetListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mToolbarTitle = it.getString("mToolbarTitle")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentEditarPerfilBinding.inflate(inflater, container, false)
        val root: View = binding.root


        initViews()
        return root
    }

    private fun initViews() {
        val mToolBar: Toolbar = binding.toolbar
        (activity as AppCompatActivity).setSupportActionBar(mToolBar)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_editar_perfil)
        if (((activity as AppCompatActivity)).supportActionBar != null) {
            (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(true)
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        mToolBar.setNavigationOnClickListener {
            val navController = (activity as AppCompatActivity).findNavController(R.id.nav_host_fragment_activity_main)
            navController.navigateUp()
        }

        txtDataNasc= binding.txtDataNasc
        txtMasculino= binding.txtMasculino
        txtFeminino= binding.txtFeminino

        radioBtnMasc= binding.radioBtnMasc
        radioBtnFem= binding.radioBtnFem

        txtMasculino.setOnClickListener {
            radioBtnMasc.isChecked = true
        }

        radioBtnMasc.setOnCheckedChangeListener{
                buttonView, isChecked ->
            if (isChecked){
                txtMasculino.text = "Masculino"
                txtFeminino.hint = "Feminino"
                txtFeminino.text = ""
                radioBtnFem.isChecked=false
            }
        }

        txtFeminino.setOnClickListener {
            radioBtnFem.isChecked = true
        }
        radioBtnFem.setOnCheckedChangeListener{
                buttonView, isChecked ->
            if (isChecked){
                txtFeminino.text = "Feminino"
                txtMasculino.hint = "Masculino"
                txtMasculino.text = ""
                radioBtnMasc.isChecked=false
            }
        }

        mDateSetListener = DatePickerDialog.OnDateSetListener { datePicker, ano, mes, dia ->
            var mes = mes + 1

            var monthString:String = mes.toString()
            var dayString:String = dia.toString()
            if (monthString.length == 1) {
                monthString = "0"+monthString
            }

            if (dayString.length == 1) {
                dayString = "0"+dayString
            }


//            date = ano + "-" + mes + "-" + dia;
            data_toShow = StringBuilder().append(dayString).append("/")
                .append(monthString).append("/").append(ano).toString()

            date = StringBuilder().append(ano).append("-").append(monthString).append("-")
                .append(dayString).toString()

            txtDataNasc.text = data_toShow

        }

        val calendarMin = Calendar.getInstance()
        calendarMin.add(Calendar.YEAR, -90)
        calendarMin.set(Calendar.MONTH,0)
        calendarMin.set(Calendar.DAY_OF_MONTH,1)
        minDateMilliseconds = calendarMin.timeInMillis

        val calendar = Calendar.getInstance()
        calendar.add(Calendar.YEAR, -19)
        calendar.set(Calendar.MONTH,11)
        calendar.set(Calendar.DAY_OF_MONTH,31)
        maxDateMilliseconds = calendar.timeInMillis

        txtDataNasc.setOnClickListener {
            val cal = Calendar.getInstance()
            var ano:Int = cal.get(Calendar.YEAR)
            var mes:Int = cal.get(Calendar.MONTH)
            var dia:Int = cal.get(Calendar.DAY_OF_MONTH)

            val dialog = context?.let { it1 -> DatePickerDialog(it1,mDateSetListener,ano, mes, dia) }
            dialog?.datePicker?.minDate = minDateMilliseconds
            dialog?.datePicker?.maxDate = maxDateMilliseconds
            dialog?.show()
        }









    }

    override fun onDestroyView() {
        EventBus.getDefault().removeAllStickyEvents()
        super.onDestroyView()
        _binding = null
    }
}