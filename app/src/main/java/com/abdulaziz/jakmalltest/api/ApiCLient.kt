package com.abdulaziz.submission4.api

import com.abdulaziz.jakmalltest.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException


class ApiCLient {
    companion object {
        private lateinit var retrofit: Retrofit

        @Throws(NoSuchAlgorithmException::class, KeyManagementException::class)
        fun getClient(BASE_URL: String): Retrofit {

            val httpClient = OkHttpClient.Builder()

            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY

                httpClient.addInterceptor(logging)
            }

            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build()

            return retrofit
        }
    }
}
