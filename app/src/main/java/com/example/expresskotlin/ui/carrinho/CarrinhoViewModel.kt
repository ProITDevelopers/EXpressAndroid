package com.example.expresskotlin.ui.carrinho

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CarrinhoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is carrinho Fragment"
    }
    val text: LiveData<String> = _text
}