package com.ncorti.kotlin.template.app.Lifestyle

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LifeStyleServiceInstance {
    private const val BASE_URL="https://www.jsonkeeper.com/"

    val lifestyleServiceIntance: Retrofit by lazy{  //create one instance
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

    val lifestyleServiceApi: LifeStyleService by lazy{
        lifestyleServiceIntance.create(LifeStyleService::class.java) //use the lifestyle service interface
    }

}