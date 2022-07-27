package com.example.expresskotlin.models

import java.io.Serializable

class MyAddress : Serializable {

    var titulo: String?=null
    var endereco: String?=null

    constructor(titulo:String,endereco:String){
        this.titulo = titulo
        this.endereco = endereco
    }

    override fun toString(): String {
        return "MyAddress = {titulo='$titulo', endereco=$endereco}"

    }
}