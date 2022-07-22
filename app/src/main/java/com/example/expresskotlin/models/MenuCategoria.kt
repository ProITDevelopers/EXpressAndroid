package com.example.expresskotlin.models

import java.io.Serializable


class MenuCategoria : Serializable {

    var id: Int?=null
    var titulo: String?=null

    var estabelecimentoList =  ArrayList<Estabelecimento>()

    constructor(id: Int, titulo: String, estabelecimentoList:ArrayList<Estabelecimento>) {
        this.id = id
        this.titulo = titulo
        this.estabelecimentoList = estabelecimentoList
    }

    constructor(id: Int, titulo: String) {
        this.id = id
        this.titulo = titulo
    }

    override fun toString(): String {
        return "MenuCategoria = {id='$id', titulo=$titulo', estabelecimentoList=$estabelecimentoList}"

    }


}