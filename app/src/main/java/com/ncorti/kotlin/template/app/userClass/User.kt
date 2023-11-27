package com.ncorti.kotlin.template.app.userClass

import java.io.Serializable

//data class for the database

data class User (var email: String, var name: String, var password: String, var username:String ):Serializable {
    constructor():this("","","","") //no argument constructor
}