package com.example.thewheeldeal


class Cars {
    var type: String? = null
    var model: String? = null
    var plateNum: String? = null
    var price: String? = null
    var imageUrl: String? = null
    var description: String? = null

    constructor()
    constructor(
        type:String?,
        model:String?,
        plateNum:String?,
        price:String?,
        imageUrl:String,
        description:String
    ){
        this.type =type
        this.model=model
        this.plateNum=plateNum
        this.price=price
        this.imageUrl=imageUrl
        this.description=description
    }

}
