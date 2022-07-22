package com.example.expresskotlin.models

import java.io.Serializable

class Estabelecimento : Serializable {

    var id: Int?=null
    var titulo: String?=null
    var categoria: String?=null
    var endereco: String?=null
    var imgUrl: String?=null

    var menuCatProdutosList =  ArrayList<MenuCatProdutos>()

    constructor(id: Int,titulo:String,categoria:String,endereco:String,menuCatProdutosList:ArrayList<MenuCatProdutos>,imgUrl:String){
        this.id = id
        this.titulo = titulo
        this.categoria = categoria
        this.endereco = endereco
        this.menuCatProdutosList = menuCatProdutosList
        this.imgUrl = imgUrl
    }

    override fun toString(): String {
        return "Estabelecimento = {id='$id', titulo=$titulo', categoria=$categoria', menuCatProdutosList=$menuCatProdutosList}"

    }





}