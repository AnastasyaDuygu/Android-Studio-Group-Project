package com.example.habits.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.habits.model.Parent
import com.google.android.material.snackbar.Snackbar
import com.ncorti.kotlin.template.app.R
import kotlinx.coroutines.CoroutineScope


class ParentAdapter(private val parents: MutableList<Parent>, private val coroutineScope: CoroutineScope)
    :RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView: View
        val inflater = LayoutInflater.from(viewGroup.context)
        itemView= inflater.inflate(R.layout.parent_recycler,viewGroup,false)
        return ViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return parents.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val parent = parents[position]
        val itemView = holder as ViewHolder
        holder.tv_category_name.text = parent.categoryTitle
        holder.rv_habit_items.apply {
            layoutManager = LinearLayoutManager(holder.itemView.context, RecyclerView.HORIZONTAL, false)
            adapter = HabitAdapter(parent.items, parent.categoryTitle, coroutineScope)
        }

        holder.itemView.setOnClickListener{
            Snackbar.make(holder.itemView, "${parent.categoryTitle}", Snackbar.LENGTH_LONG).show()
        }

    }


    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        lateinit var rv_habit_items: RecyclerView
        lateinit var tv_category_name: TextView

        init {
            rv_habit_items = itemView.findViewById(R.id.rv_habit_items)
            tv_category_name = itemView.findViewById(R.id.tv_category_name)
        }
    }

}