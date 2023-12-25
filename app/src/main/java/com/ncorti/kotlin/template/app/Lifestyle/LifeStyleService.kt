package com.ncorti.kotlin.template.app.Lifestyle
import retrofit2.Call
import retrofit2.http.GET
interface LifeStyleService {
    @GET("b/BH12")
    fun getAllLifeStyles(): Call<MutableList<Lifestyle>>
}