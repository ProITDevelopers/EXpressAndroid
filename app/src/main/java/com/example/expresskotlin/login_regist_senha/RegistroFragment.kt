package com.example.expresskotlin.login_regist_senha

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.example.expresskotlin.MainActivity
import com.example.expresskotlin.R
import com.example.expresskotlin.databinding.FragmentRegistroBinding
import com.example.expresskotlin.activities.CadastroActivity
import de.hdodenhof.circleimageview.CircleImageView


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class RegistroFragment : Fragment() {

    private var _binding: FragmentRegistroBinding?=null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var imgUserPic: CircleImageView
    private lateinit var fabAddPic: FloatingActionButton
    private lateinit var editNome: AppCompatEditText
    private lateinit var editTelefone:AppCompatEditText
    private lateinit var editPassword:AppCompatEditText
    private lateinit var checkboxAcceptTerms: AppCompatCheckBox
    private lateinit var txtTermsCondicoes: TextView
    private lateinit var txtLogin:TextView
    private lateinit var btnRegistrar: Button

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegistroFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegistroBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initViews()
        return root
//        return inflater.inflate(R.layout.fragment_registro, container, false)
    }

    private fun initViews() {
        imgUserPic = binding.imgUserPic
        fabAddPic = binding.fabAddPic

        editNome = binding.editNome
        editTelefone = binding.editTelefone
        editPassword = binding.editPassword

        checkboxAcceptTerms = binding.checkboxAcceptTerms
        txtTermsCondicoes = binding.txtTermsCondicoes
        txtLogin = binding.txtLogin
        btnRegistrar = binding.btnRegistrar

        val spannableStringTerms = SpannableString(getString(R.string.termos_e_condicoes))
        spannableStringTerms.setSpan(UnderlineSpan(),19,37, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        txtTermsCondicoes.text = spannableStringTerms

        val spannableString = SpannableString(getString(R.string.ja_possui_conta_login))
        spannableString.setSpan(StyleSpan(Typeface.BOLD),21,26, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        txtLogin.text = spannableString

        btnRegistrar.setOnClickListener {
            launchHomescreen()
        }

        txtTermsCondicoes.setOnClickListener{
            if(activity!=null){

                val licensesFragment = LicensesFragment()
                licensesFragment.show((activity as CadastroActivity).supportFragmentManager.beginTransaction(),"dialog_licenses")

            }
        }


        txtLogin.setOnClickListener {
            val tabLayout: TabLayout? = CadastroActivity.getTabLayout()
            tabLayout?.selectTab(tabLayout.getTabAt(1))
        }


    }


    private fun launchHomescreen() {
        if (activity!=null)
            startActivity(Intent(activity, MainActivity::class.java))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}