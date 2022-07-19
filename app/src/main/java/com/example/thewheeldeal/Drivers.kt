package com.example.thewheeldeal


class Drivers {

    var DrName: String? = null
    var DrContact: String? = null
    var DrDlNum: String? = null
    var DrIdNum: String? = null
    var DrEmail: String? = null
    var DrimageUrl: String? = null
    var DrCharge: String? = null
    constructor()
    constructor(
        DrName:String?,
        DrContact:String?,
        DrDlNum:String?,
        DrIdNum:String?,
        DrEmail:String,
        DrimageUrl:String,
        DrCharge:String
    ){
        this.DrName =DrName
        this.DrContact=DrContact
        this.DrDlNum=DrDlNum
        this.DrIdNum=DrIdNum
        this.DrEmail=DrEmail
        this.DrimageUrl=DrimageUrl
        this.DrCharge=DrCharge
    }


}
