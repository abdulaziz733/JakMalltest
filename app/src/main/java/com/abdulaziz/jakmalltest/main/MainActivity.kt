package com.abdulaziz.jakmalltest.main

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.abdulaziz.jakmalltest.R
import com.abdulaziz.jakmalltest.model.RandomText
import com.abdulaziz.jakmalltest.utils.getAPIService
import com.abdulaziz.jakmalltest.utils.gone
import com.abdulaziz.jakmalltest.utils.visible
import com.abdulaziz.submission4.api.ApiInterface
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var apiService: ApiInterface
    private lateinit var presenter: MainPresenter
    private lateinit var gson: Gson
    private lateinit var rvListText: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var addMoreData: Button
    private lateinit var adapter: MainAdapter
    private var randomTextList: MutableList<RandomText> = mutableListOf()
    private var randomTextAllList: MutableList<RandomText> = mutableListOf()
    private var listSize: Int = 0

    @SuppressLint("SetTextI18n", "ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    orientation = LinearLayout.VERTICAL

                    progressBar = progressBar {
                    }.lparams {
                        gravity = Gravity.CENTER
                    }

                    textView {
                        text = "Who's on Top?"
                        textSize = sp(12).toFloat()
                    }.lparams {
                        bottomMargin = dip(4)
                        gravity = Gravity.CENTER
                    }

                    rvListText = recyclerView {
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    addMoreData = button("Add More Data?") {
                        textSize = sp(4).toFloat()
                        backgroundColor = R.color.md_cyan_50
                        onClick {
                            addMore()
                        }
                    }.lparams {
                        bottomMargin = dip(2)
                        gravity = Gravity.CENTER
                        padding = dip(4)
                    }

                }
            }
        }

        adapter = MainAdapter(this, randomTextList) { randomText, positionItem ->
            val toBeFirst = randomTextList[positionItem]
            randomTextList.removeAt(positionItem)
            randomTextList.reverse()
            randomTextList.add(toBeFirst)
            randomTextList.reverse()
            adapter.notifyDataSetChanged()
            // Belum menemukan LinkedList di kotlin
        }

        rvListText.adapter = adapter

        apiService = getAPIService()
        gson = Gson()
        presenter = MainPresenter(this, apiService, gson)
        presenter.getDataRandomText()
        swipeRefresh.onRefresh {
            presenter.getDataRandomText()
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.gone()
    }

    override fun showTextList(data: List<RandomText>) {
        listSize = 3
        swipeRefresh.isRefreshing = false
        randomTextAllList.clear()
        randomTextAllList.addAll(data)
        randomTextList.clear()
        randomTextList.addAll(data.subList(0, listSize))
        adapter.notifyDataSetChanged()
        addMoreData.visibility = View.VISIBLE
    }

    private fun addMore() {
        randomTextList.add(randomTextAllList[listSize++])
        adapter.notifyDataSetChanged()
        if (listSize == 5) addMoreData.visibility = View.GONE

    }


}
