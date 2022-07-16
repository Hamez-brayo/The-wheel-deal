package com.example.thewheeldeal

import com.google.firebase.database.Exclude

data class Items(
    //val image: Int,
    var carId:String?=null,
    var text: String?=null,
    var type:String?= null,
    var price: String?= null,
    var model: String?=null,
    var plateNum: String?= null,
    var description: String?= null,
    @get:Exclude
    @set:Exclude
    var key:String?=null,

    )
