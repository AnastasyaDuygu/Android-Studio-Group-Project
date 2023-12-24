package com.example.habits.adapter

import HabitSys
import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.lifecycleScope

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.habits.model.Habit
import com.ncorti.kotlin.template.app.userClass.Constants
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.ncorti.kotlin.template.app.R
import com.ncorti.kotlin.template.app.userClass.HelperClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class HabitAdapter(private val habits: MutableList<Habit>, private val category_name: String, private val coroutineScope: CoroutineScope
)
    :RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    lateinit var customDialog: Dialog
    lateinit var btnAdd: Button
    lateinit var btnCancel: Button
    lateinit var btnClose: Button
    lateinit var btnDelete: Button

    lateinit var inp_new_habit_name: EditText
    lateinit var dialog_category_name: EditText
    lateinit var et_dialog_context: EditText
    var new_habit_name: String = ""
    lateinit var tv_full_dialog_habit_name: TextView
    lateinit var tv_full_dialog_habit_desc: TextView
    lateinit var tv_full_dialog_habit_category: TextView

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView: View
        val inflater = LayoutInflater.from(viewGroup.context)
        return if (viewType == Constants.EMPTY){
            itemView= inflater.inflate(R.layout.habit_item_empty,viewGroup,false)
            EmptyRecyclerViewItemHolder(itemView)
        } else {
            itemView= inflater.inflate(R.layout.habit_item_full,viewGroup,false)
            FullRecyclerViewItemHolder(itemView)
        }
    }
    override fun getItemCount(): Int {
        return habits.size
    }
    //STEP1
    override fun getItemViewType(position: Int): Int {
        val sc = habits[position]
        return if (sc.name == "") Constants.EMPTY else Constants.FULL
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        fun createDialog() {
            customDialog = Dialog(holder.itemView.context)
            customDialog.setContentView(R.layout.dialog)
            inp_new_habit_name = customDialog!!.findViewById(R.id.inp_new_habit_name)
            dialog_category_name = customDialog!!.findViewById(R.id.dialog_category_name)
            dialog_category_name.setText(this.category_name)
            et_dialog_context = customDialog!!.findViewById(R.id.et_dialog_context)
            btnAdd = customDialog!!.findViewById<Button>(R.id.btnAdd)
            btnCancel = customDialog!!.findViewById<Button>(R.id.btnCancel)
            btnAdd.setOnClickListener(View.OnClickListener {
                new_habit_name = inp_new_habit_name.getText().toString()

                if (new_habit_name != ""){
                    habits[position].description = et_dialog_context.text.toString()
                    habits[position].name = new_habit_name
                    //add to the database
                    val userNode = HelperClass.getDatabaseInstance().getReference("habits")
                    val childNode = userNode.child(Constants.UID)

                    userNode.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            if (!snapshot.exists()) {
                                // Child node doesn't exist, create it
                                userNode.child(Constants.UID).setValue(true)
                                Log.d("NodeCreation", "Child node created for UID: ${Constants.UID}")
                            } else {
                                Log.d("NodeCreation", "Child node already exists for UID: ${Constants.UID}")

                                // Use push() to generate a unique key for the new habit (a list)
                                val newHabitRef = childNode.push()
                                val uniqueKey = newHabitRef.key
                                // Child node exists, proceed to add a new habit
                                val newHabit = Habit(inp_new_habit_name.text.toString(), et_dialog_context.text.toString(), this@HabitAdapter.category_name, uniqueKey!!)
                                // Print the unique key
                                Log.d("Unique Key", uniqueKey ?: "Key is null")
                                // Add the new habit to the UID node
                                newHabitRef.setValue(newHabit)

                                coroutineScope.launch {
                                    HabitSys.prepareHabits(Constants.UID)
                                }
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            Log.e("NodeCreation", "Error checking UID node existence: ${error.message}")
                        }
                    })
                    Snackbar.make(holder.itemView, "New Habit Added!", Snackbar.LENGTH_LONG).show()
                    customDialog!!.dismiss()
                }else{
                    Snackbar.make(holder.itemView, "Please write a name!", Snackbar.LENGTH_LONG).show()
                }
            })
            btnCancel.setOnClickListener(View.OnClickListener { //customDialog.hide();
                //save_button = false
                customDialog!!.dismiss()
            })
        }
        fun createDetailsDialog() {
            customDialog = Dialog(holder.itemView.context)
            customDialog.setContentView(R.layout.dialog_details)
            tv_full_dialog_habit_name = customDialog!!.findViewById(R.id.tv_full_dialog_habit_name)
            tv_full_dialog_habit_desc = customDialog!!.findViewById(R.id.tv_full_dialog_habit_desc)
            tv_full_dialog_habit_category = customDialog!!.findViewById(R.id.tv_full_dialog_habit_category)
            tv_full_dialog_habit_name.text = habits[position].name
            tv_full_dialog_habit_category.text = this.category_name
            tv_full_dialog_habit_desc.text = habits[position].description
            btnClose = customDialog!!.findViewById<Button>(R.id.btnClose)
            btnClose.setOnClickListener(View.OnClickListener { //customDialog.hide();
                customDialog!!.dismiss()
            })
            btnDelete = customDialog!!.findViewById<Button>(R.id.btnDelete)
            btnDelete.setOnClickListener(View.OnClickListener { //customDialog.hide();
                Snackbar.make(holder.itemView, "You deleted the habit!", Snackbar.LENGTH_LONG).show()
                //delete that habit from the DB
                val habitToDelete= habits[position]
                Log.d("DELETE:", "$habitToDelete")
                val parentId= habitToDelete.parentId
                val userNode = HelperClass.getDatabaseInstance().getReference("habits")
                val childNode = userNode.child(Constants.UID)
                val habit = childNode.child(parentId)
                if(habit!=null)
                {
                    habit.removeValue()
                    coroutineScope.launch {
                            HabitSys.prepareHabits(Constants.UID)
                    }
                }

                customDialog!!.dismiss()
            })
        }
        var habit = habits[position]


        if (getItemViewType(position) == Constants.EMPTY){
            val itemView = holder as EmptyRecyclerViewItemHolder
            holder.fab_habit_add.setOnClickListener{
                createDialog()
                customDialog!!.show()
                //Snackbar.make(it, "clicked empty habit", Snackbar.LENGTH_LONG).show()
            }
        } else if (getItemViewType(position) == Constants.FULL){
            val itemView = holder as FullRecyclerViewItemHolder
            holder.tv_item_habit_name.text = habit.name
            holder.itemView.setOnClickListener{
                createDetailsDialog()
                customDialog!!.show()
                //Snackbar.make(it, "${habit.name}, desc: ${habit.description}", Snackbar.LENGTH_LONG).show()
                //Snackbar.make(it, "full habit clicked $habit", Snackbar.LENGTH_LONG).show()
            }
        }




        /*holder.itemView.setOnClickListener{
            Snackbar.make(it, "habit outside clicked $habit", Snackbar.LENGTH_LONG).show()
        }*/
    }

    //STEP 2
    inner class EmptyRecyclerViewItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var fab_habit_add: FloatingActionButton

        init {
            fab_habit_add = itemView.findViewById(R.id.fab_habit_add)
        }
    }

    inner class FullRecyclerViewItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var tv_item_habit_name: TextView
        init {
            tv_item_habit_name = itemView.findViewById(R.id.tv_item_habit_name)
        }
    }



}