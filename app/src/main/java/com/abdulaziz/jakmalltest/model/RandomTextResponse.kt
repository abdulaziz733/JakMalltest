package com.abdulaziz.jakmalltest.model

import com.google.gson.annotations.SerializedName

data class RandomTextResponse(
        val type: String,
        @SerializedName("value")
        val randomText: List<RandomText>
)