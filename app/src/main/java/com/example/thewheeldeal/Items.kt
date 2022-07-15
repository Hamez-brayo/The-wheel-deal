package com.example.thewheeldeal

import android.net.Uri
import com.google.firebase.database.Exclude

data class Items(
    //val image: Int,
    var text: String?=null,
    var price: String?= null,
    var model: String?=null,
    var plateNum: String?= null,
    var description: String?= null,
    @get:Exclude
    @set:Exclude
    var key:String?=null,

    )
