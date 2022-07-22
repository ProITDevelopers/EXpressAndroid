package com.example.expresskotlin.models

import java.io.Serializable


class IconTitle : Serializable {

    lateinit var titulo: String
    var imgResource: Int?=null

    constructor(titulo: String, imgResource: Int)
    {
        this.titulo = titulo
        this.imgResource = imgResource

    }

    override fun toString(): String {
        return "IconTitle = {titulo='$titulo}"

    }
}