package com.ncorti.kotlin.template.app.Lifestyle

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ncorti.kotlin.template.app.R

class LifestylesAdapter(private val context: Context, private val recyclerItemValues: ArrayList<Lifestyle>)
    : RecyclerView.Adapter<LifestylesAdapter.LifestylesRecyclerViewItemHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): LifestylesRecyclerViewItemHolder {
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.recycler_item_lifestyle, viewGroup, false)
        return LifestylesRecyclerViewItemHolder(itemView)
    }


    override fun onBindViewHolder(holder: LifestylesRecyclerViewItemHolder, position: Int) {
        val currentItem = recyclerItemValues[position]
        holder.tvItemLifestyleName.text = currentItem.name
        // Set the image for the ImageView (you might want to change this depending on where your images come from)
        holder.imageViewLifestyle.setImageResource(R.drawable.dumbbell) // Replace with actual image resource or loading mechanism

        // Set a click listener for the entire item view
        holder.itemLayout.setOnClickListener {
            // Handle item click
            Toast.makeText(context, "${currentItem.name} selected", Toast.LENGTH_LONG).show()
            // Perform action such as navigating to another activity or updating the selection
        }
    }

    override fun getItemCount(): Int {
        return recyclerItemValues.size
    }

    inner class LifestylesRecyclerViewItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageViewLifestyle: ImageView = itemView.findViewById(R.id.imageViewLifestyle)
        var tvItemLifestyleName: TextView = itemView.findViewById(R.id.tvItemLifestyleName)
        var itemLayout: LinearLayout = itemView.findViewById(R.id.itemLayoutLifestyle)
        // If you have a button or other interactive elements, declare them here
    }
}