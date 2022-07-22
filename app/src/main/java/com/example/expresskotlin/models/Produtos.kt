package com.example.expresskotlin.models

import java.io.Serializable


class Produtos : Serializable {

    var idProduto: Int?=null
    var titulo: String?=null
    var preco: Double?=null
    var tipo: String?=null
    var imgUrl: String?=null
    var quantity: Int=0

    var estabelecimentoId: Int?=null

    constructor(idProduto: Int, titulo: String, preco: Double, tipo: String, imgUrl: String)
    {
        this.idProduto = idProduto
        this.titulo = titulo
        this.preco = preco
        this.tipo = tipo
        this.imgUrl = imgUrl
    }

    constructor()
    {

    }

    override fun toString(): String {
        return "Produto = {id='$idProduto', titulo=$titulo', preco=$preco', tipo=$tipo}"

    }
}