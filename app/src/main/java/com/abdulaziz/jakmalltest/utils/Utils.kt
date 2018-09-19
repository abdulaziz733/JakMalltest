package com.abdulaziz.jakmalltest.utils

import android.view.View
import com.abdulaziz.jakmalltest.BuildConfig
import com.abdulaziz.submission4.api.ApiCLient
import com.abdulaziz.submission4.api.ApiInterface
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

@Throws(KeyManagementException::class, NoSuchAlgorithmException::class)
fun getAPIService(): ApiInterface {
    return ApiCLient.getClient(BuildConfig.BASE_URL).create(ApiInterface::class.java)
}




