package com.example.expresskotlin.models

import java.io.Serializable

class MeusPedidos : Serializable {

    var titulo: String?=null
    var data: String?=null
    var estado: String?=null

    constructor(titulo:String,data:String,estado:String){
        this.titulo = titulo
        this.data = data
        this.estado = estado
    }

    override fun toString(): String {
        return "MeusPedidos = {titulo='$titulo', data=$data', estado=$estado}"

    }
}