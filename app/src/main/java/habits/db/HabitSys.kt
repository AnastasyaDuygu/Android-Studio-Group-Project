package com.example.habits.adapter.db

import com.example.habits.model.Habit
import com.google.firebase.database.GenericTypeIndicator
import com.ncorti.kotlin.template.app.userClass.HelperClass
import java.lang.reflect.GenericArrayType
import java.util.Collections

class HabitSys {
    companion object {
        lateinit var habits: ArrayList<ArrayList<Habit>>
        fun prepareHabits(uid: String) {
            val userNode = HelperClass.getDatabaseInstance().getReference("habits") //getting registeredUsers Node
            val habit = userNode.child(uid).get().getResult()
            var habitData: ArrayList<Habit> = ArrayList()
            if(habit.exists())
            {
                val habits = habit.getValue(object: GenericTypeIndicator<ArrayList<Habit>>(){}) //converts the snapshot to an array
                habitData = habits?: ArrayList()
            }
            habits.add(habitData)
        }
    }
}