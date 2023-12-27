package com.ncorti.kotlin.template.app.Lifestyle

data class Lifestyle(
    var id: Int,
    var name: String,
    var description: String // Assuming each lifestyle has a description

){
    constructor() : this(0, "ERROR 800: Lifestyle isn't selected yet", "")
}
