package com.example.expresskotlin.eventbus

import com.example.expresskotlin.models.MenuCategoria

class CategoriaEstabClick {

    var success:Boolean = false
    var menuCategoria: MenuCategoria

    constructor(success:Boolean, menuCategoria: MenuCategoria){
        this.success = success
        this.menuCategoria = menuCategoria
    }
}