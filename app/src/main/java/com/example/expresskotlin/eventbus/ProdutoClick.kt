package com.example.expresskotlin.eventbus

import com.example.expresskotlin.models.Produtos

class ProdutoClick {

    var success:Boolean = false
    var estabTitulo: String=""
    var produto: Produtos

    constructor(success:Boolean, estabTitulo:String,produto: Produtos){
        this.success = success
        this.estabTitulo = estabTitulo
        this.produto = produto
    }
}