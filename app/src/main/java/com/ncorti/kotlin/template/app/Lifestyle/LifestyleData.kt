package com.ncorti.kotlin.template.app.Lifestyle

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LifestyleData {

    //https://www.jsonkeeper.com/b/BH12 json link
    companion object {
        var lifestyleList: MutableList<Lifestyle> = mutableListOf()
        fun getDataFromTheServer(lifestylesAdapter: LifestylesAdapter) {
            val call: Call<MutableList<Lifestyle>> =
                LifeStyleServiceInstance.lifestyleServiceApi.getAllLifeStyles() //single instance
            call.enqueue(object : Callback<MutableList<Lifestyle>> {
                override fun onResponse(
                    call: Call<MutableList<Lifestyle>>,
                    response: Response<MutableList<Lifestyle>>
                ) {
                    if (response.isSuccessful) {
                        val lifestyleData = response.body()
                        Log.d("LifestyleData", "$lifestyleData")
                        lifestyleList= lifestyleData!!
                        lifestylesAdapter.setData(lifestyleList)
                    } else {
                        Log.d("Error", "${response.errorBody()}")

                    }

                }

                override fun onFailure(call: Call<MutableList<Lifestyle>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })

        }
    }
}