package com.abdulaziz.jakmalltest.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abdulaziz.jakmalltest.R
import com.abdulaziz.jakmalltest.model.RandomText
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_random_text.view.*

class MainAdapter(private val context: Context,
                  private val listRandomTextt: List<RandomText>,
                  private val listener: (RandomText, positionItem: Int) -> Unit)
    : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_random_text, parent, false))

    override fun getItemCount(): Int = listRandomTextt.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(listRandomTextt[position], position, listener)
    }


    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindItem(randomText: RandomText, positionItem: Int, listener: (RandomText, positionItem: Int) -> Unit) {
            itemView.text_content.text = randomText.joke
            if (positionItem == 0) {
                itemView.img_upward.visibility = View.GONE
                itemView.text_top_status.visibility = View.VISIBLE
            } else {
                itemView.img_upward.visibility = View.VISIBLE
                itemView.text_top_status.visibility = View.GONE
            }
            itemView.setOnClickListener { listener(randomText, positionItem) }
        }

    }

}