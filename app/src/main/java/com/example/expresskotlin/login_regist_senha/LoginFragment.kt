package com.example.expresskotlin.login_regist_senha

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import com.example.expresskotlin.databinding.FragmentLoginBinding
import com.google.android.material.tabs.TabLayout
import com.example.expresskotlin.*
import com.example.expresskotlin.activities.CadastroActivity
import com.example.expresskotlin.activities.RecuperarSenhaActivity
import com.example.expresskotlin.R
import com.example.expresskotlin.helpers.MetodosUsados


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding?=null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var editEmailPhone: AppCompatEditText
    private lateinit var editPassword:AppCompatEditText
    private lateinit var txtForgetPass: TextView
    private lateinit var txtRegistrar:TextView
    private lateinit var btnLogin: Button
    private lateinit var btnFacebook:Button
    private lateinit var btnGmail:Button

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
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
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initViews()
        return root
//        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    private fun initViews() {
        editEmailPhone = binding.editEmailPhone
        editPassword = binding.editPassword

        txtForgetPass = binding.txtForgetPass
        txtRegistrar = binding.txtRegistrar

        btnLogin = binding.btnLogin
        btnFacebook = binding.btnFacebook
        btnGmail = binding.btnGmail

        val spannableString = SpannableString(getString(R.string.nao_tem_conta_registrar))
        spannableString.setSpan(StyleSpan(Typeface.BOLD),19,28, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        txtRegistrar.text = spannableString


        btnLogin.setOnClickListener {
            launchHomescreen()
        }

        btnFacebook.setOnClickListener {
            context?.let { it1 -> MetodosUsados.mostrarMensagem(it1,R.string.facebook) }
        }

        btnGmail.setOnClickListener{
            context?.let { it1 -> MetodosUsados.mostrarMensagem(it1,R.string.gmail) }
        }

        txtForgetPass.setOnClickListener{
            if (activity!=null)
                startActivity(Intent(activity, RecuperarSenhaActivity::class.java))

        }

        txtRegistrar.setOnClickListener{

            val tabLayout: TabLayout? = CadastroActivity.getTabLayout()
            tabLayout?.selectTab(tabLayout.getTabAt(0))
        }


    }

    private fun launchHomescreen() {
        if (activity!=null)
            startActivity(Intent(activity,MainActivity::class.java))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}