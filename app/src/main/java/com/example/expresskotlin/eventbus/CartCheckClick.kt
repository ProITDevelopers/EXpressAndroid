package com.example.expresskotlin.eventbus



class CartCheckClick {

    var success:Boolean = false
    var subTotalPrice: Double
    var totalDeTudoPrice: Double

    constructor(success:Boolean, subTotalPrice: Double, totalDeTudoPrice: Double){
        this.success = success
        this.subTotalPrice = subTotalPrice
        this.totalDeTudoPrice = totalDeTudoPrice
    }
}