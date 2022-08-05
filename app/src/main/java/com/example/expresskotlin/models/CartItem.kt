package com.example.expresskotlin.models

import java.io.Serializable
import java.util.*

class CartItem : Serializable {

    private var idCart =  UUID.randomUUID()
    var cartItemQuantity: Int = 0
    var produto =  Produtos()



    constructor(produto: Produtos, cartItemQuantity:Int)
    {
        this.produto = produto
        this.cartItemQuantity = cartItemQuantity
    }

    override fun toString(): String {
        return "CartItem = {idCart='$idCart', cartItemQuantity=$cartItemQuantity', produto=$produto}"

    }
}