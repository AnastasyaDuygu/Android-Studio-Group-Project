package com.ncorti.kotlin.template.app.userClass

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

object HelperClass {
    private val authentication = FirebaseAuth.getInstance()
    private val database= FirebaseDatabase.getInstance()

    fun getAuthenticationInstance(): FirebaseAuth {
        return authentication;
    }

    fun getDatabaseInstance(): FirebaseDatabase{
        return database
    }
}