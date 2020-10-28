package com.pjhubs.medical.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pjhubs.medical.R
import kotlinx.android.synthetic.main.item_home.view.*

class HomeAdapter(private val itemList: List<HomeItem>): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemImage: ImageView = view.findViewById(R.id.home_item_imageView)
        val itemTextView: TextView = view.findViewById(R.id.home_item_textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.itemImage.setImageResource(item.itemImageId)
        holder.itemTextView.text = item.itemName
    }
}