package com.example.thewheeldeal

import com.google.firebase.database.Exclude

data class Drivers(

    var DrName:String?= null,
    var DrContact: String?= null,
    var DrDlNum: String?= null,
    var DrIdNum: String?=null,
    var DrEmail:String?=null,
    var DrimageUrl: String?=null,
    @get:Exclude
    @set:Exclude
    var key:String?=null,


    )
