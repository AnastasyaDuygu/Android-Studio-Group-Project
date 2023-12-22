package com.ncorti.kotlin.template.app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView


class GridItemAdapter(private val context: Context, private val items: Array<GridItem>) : BaseAdapter() {

    data class GridItem(val text: String, val iconResId: Int)

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Any = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val gridItem: GridItem = getItem(position) as GridItem
        viewHolder.textView.text = gridItem.text
        viewHolder.imageView.setImageResource(gridItem.iconResId)
        return view
    }

    private class ViewHolder(view: View) {
        val imageView: ImageView = view.findViewById(R.id.imageViewItem)
        val textView: TextView = view.findViewById(R.id.textViewItem)
    }
}