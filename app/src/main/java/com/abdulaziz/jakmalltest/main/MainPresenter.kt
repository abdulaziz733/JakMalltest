package com.abdulaziz.jakmalltest.main

import com.abdulaziz.jakmalltest.model.RandomTextResponse
import com.abdulaziz.submission4.api.ApiInterface
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter (private val view: MainView,
                     private val apiService: ApiInterface,
                     private val gson: Gson) {

    fun getDataRandomText(){
        view.showLoading()
        apiService.getListRandomText().enqueue(object : Callback<RandomTextResponse>{
            override fun onFailure(call: Call<RandomTextResponse>, t: Throwable) {
                view.hideLoading()

            }

            override fun onResponse(call: Call<RandomTextResponse>, response: Response<RandomTextResponse>) {
                view.hideLoading()
                if (response.isSuccessful && response.body()?.type.equals("success")){
                    val data = response.body()?.randomText
                    if (data != null){
                        view.showTextList(data)
                    }
                }
            }

        })

    }

}