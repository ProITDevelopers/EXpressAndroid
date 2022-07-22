package com.example.expresskotlin.login_regist_senha

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.expresskotlin.activities.RecuperarSenhaActivity
import com.example.expresskotlin.R
import com.example.expresskotlin.databinding.FragmentRecuperarBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RecuperarFragment : Fragment() {

    private var _binding: FragmentRecuperarBinding?=null
    private val binding get() = _binding!!

    private var param1: String? = null
    private var param2: String? = null

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecuperarFragment().apply {
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
        _binding = FragmentRecuperarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val btnOK : Button = binding.btnOK
        btnOK.setOnClickListener {
            if (activity!=null){
                val fragmentManager = (activity as RecuperarSenhaActivity).supportFragmentManager
                val transaction = fragmentManager.beginTransaction()
                transaction.replace(R.id.frame_layout_senha, InserirCodigoFragment(), null)
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