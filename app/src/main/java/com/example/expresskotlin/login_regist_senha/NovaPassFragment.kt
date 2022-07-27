package com.example.expresskotlin.login_regist_senha

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.expresskotlin.R
import com.example.expresskotlin.databinding.FragmentNovaPassBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class NovaPassFragment : Fragment() {

    private var _binding: FragmentNovaPassBinding?=null
    private val binding get() = _binding!!

    private var param1: String? = null
    private var param2: String? = null

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NovaPassFragment().apply {
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
        _binding = FragmentNovaPassBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val btnOK : Button = binding.btnOK
        btnOK.setOnClickListener {
            mensagemSucesso()
        }
        return root
    }

    private fun mensagemSucesso() {
        var message =getString(R.string.feedback_sucesso);

        if (activity!=null){
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {

                val builder = activity?.let { androidx.appcompat.app.AlertDialog.Builder(it) }

                builder?.setMessage(message);

                builder?.setPositiveButton("OK", DialogInterface.OnClickListener() {

                        dialog, id -> dialog.cancel()
                    activity?.finish()
                })

                builder?.show()
            } else {

                val builder = activity?.let { AlertDialog.Builder(it) }
                builder?.setMessage(message);

                builder?.setPositiveButton("OK", DialogInterface.OnClickListener() {
                        dialog, id -> dialog.cancel()
                    activity?.finish()
                });


                builder?.show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}