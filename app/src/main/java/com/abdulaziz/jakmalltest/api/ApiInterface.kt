package com.abdulaziz.submission4.api

import com.abdulaziz.jakmalltest.model.RandomTextResponse
import com.abdulaziz.jakmalltest.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

open interface ApiInterface {
    @GET(Constants.RANDOM_TEXT)
    fun getListRandomText() : Call<RandomTextResponse>
}