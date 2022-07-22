package com.example.expresskotlin.eventbus

class PerfilSettingsClick {

    var success:Boolean = false
    var titulo: String=""
    var position: Int = 0

    constructor(success:Boolean, titulo:String,position: Int){
        this.success = success
        this.titulo = titulo
        this.position = position
    }
}