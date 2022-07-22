package com.example.expresskotlin.eventbus

import com.example.expresskotlin.models.MenuCatProdutos

class EstabCatClick {

    var success:Boolean = false
    var estabTitulo: String=""
    var menuCatProdutos: MenuCatProdutos

    constructor(success:Boolean, estabTitulo:String,menuCatProdutos: MenuCatProdutos){
        this.success = success
        this.estabTitulo = estabTitulo
        this.menuCatProdutos = menuCatProdutos
    }
}