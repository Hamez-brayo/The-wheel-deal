package com.example.thewheeldeal

import com.google.firebase.database.Exclude

data class Cars(
    var carId:String?=null,
    var type:String?= null,
    var model: String?=null,
    var plateNum: String?= null,
    var price: String?= null,
    var description: String?= null,
    @get:Exclude
    @set:Exclude
    var key:String?=null,
)
