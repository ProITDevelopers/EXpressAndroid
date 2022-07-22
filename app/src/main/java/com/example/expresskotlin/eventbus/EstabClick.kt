package com.example.expresskotlin.eventbus

import com.example.expresskotlin.models.Estabelecimento

class EstabClick {

    var success:Boolean = false
    var estabelecimento: Estabelecimento

    constructor(success:Boolean, estabelecimento: Estabelecimento){
        this.success = success
        this.estabelecimento = estabelecimento
    }
}