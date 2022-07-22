package com.example.expresskotlin.models

import java.io.Serializable


class MenuCatProdutos : Serializable {

    var id: Int?=null
    var titulo: String?=null


    var produtosList =  ArrayList<Produtos>()

    constructor(id: Int, titulo: String, produtosList:ArrayList<Produtos>) {
        this.id = id
        this.titulo = titulo
        this.produtosList = produtosList
    }

    constructor(id: Int, titulo: String) {
        this.id = id
        this.titulo = titulo
    }

    override fun toString(): String {
        return "MenuCatProdutos = {id='$id', titulo=$titulo', produtosList=$produtosList}"

    }

}