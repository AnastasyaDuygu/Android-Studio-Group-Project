package com.ncorti.kotlin.template.app.Lifestyle

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.habits.model.Habit
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.ncorti.kotlin.template.app.R
import com.ncorti.kotlin.template.app.userClass.Constants
import com.ncorti.kotlin.template.app.userClass.HelperClass
import kotlinx.coroutines.launch

class LifestylesAdapter(private val context: Context)
    : RecyclerView.Adapter<LifestylesAdapter.LifestylesRecyclerViewItemHolder>() {

    private var recyclerItemValues: MutableList<Lifestyle> = mutableListOf()
    @SuppressLint("NotifyDataSetChanged")
    fun setData(items:MutableList<Lifestyle>){
        recyclerItemValues = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): LifestylesRecyclerViewItemHolder {
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.recycler_item_lifestyle, viewGroup, false)
        return LifestylesRecyclerViewItemHolder(itemView)
    }


    override fun onBindViewHolder(holder: LifestylesRecyclerViewItemHolder, position: Int) {
        val currentItem = recyclerItemValues[position]
        holder.tvItemLifestyleName.text = currentItem.name
        // Set the image for the ImageView (you might want to change this depending on where your images come from)
        holder.imageViewLifestyle.setImageResource(R.drawable.lifestyle_icon) // Replace with actual image resource or loading mechanism

        // Set a click listener for the entire item view
        holder.itemLayout.setOnClickListener {
            //add to the DB
            val uid = Constants.UID
            val lifestyleNode = HelperClass.getDatabaseInstance().getReference("lifestyle").child(uid) //getting lifestyle Node

            lifestyleNode.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val replaceLifeStyleObject = Lifestyle(currentItem.id, currentItem.description, currentItem.description)
                    lifestyleNode.setValue(replaceLifeStyleObject)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("NodeCreation", "Error checking UID node existence: ${error.message}")
                }
            })


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