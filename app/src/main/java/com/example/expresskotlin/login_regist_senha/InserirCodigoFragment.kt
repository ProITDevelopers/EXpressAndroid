package com.example.expresskotlin.login_regist_senha

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
import androidx.fragment.app.Fragment
import com.example.expresskotlin.R
import com.example.expresskotlin.databinding.FragmentInserirCodigoBinding
import com.example.expresskotlin.activities.RecuperarSenhaActivity



private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class InserirCodigoFragment : Fragment() {

    private var _binding: FragmentInserirCodigoBinding?=null
    private val binding get() = _binding!!

    private var param1: String? = null
    private var param2: String? = null

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InserirCodigoFragment().apply {
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
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentInserirCodigoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val txtReenviarCode: TextView = binding.txtReenviarCode
        val spannableStringCode = SpannableString(getString(R.string.nao_recebeu_clique_aqui))
        spannableStringCode.setSpan(UnderlineSpan(),0,24, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableStringCode.setSpan(StyleSpan(Typeface.BOLD),13,24, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        txtReenviarCode.text = spannableStringCode

        val btnOK : Button = binding.btnOK
        btnOK.setOnClickListener {
            if (activity!=null){
                val fragmentManager = (activity as RecuperarSenhaActivity).supportFragmentManager
                val transaction = fragmentManager.beginTransaction()
                transaction.replace(R.id.frame_layout_senha, NovaPassFragment(), null)
                transaction.commit()
            }
        }
        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}