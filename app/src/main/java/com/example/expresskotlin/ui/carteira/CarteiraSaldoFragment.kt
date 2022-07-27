package com.example.expresskotlin.ui.carteira

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.expresskotlin.databinding.FragmentCarteiraSaldoBinding


class CarteiraSaldoFragment : Fragment() {

    private var _binding: FragmentCarteiraSaldoBinding?=null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCarteiraSaldoBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}