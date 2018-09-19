package com.abdulaziz.jakmalltest.main

import com.abdulaziz.jakmalltest.model.RandomText

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showTextList(data: List<RandomText>)
}