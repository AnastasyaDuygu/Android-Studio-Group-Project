package com.example.habits.adapter.db

import com.example.habits.model.Habit
import java.util.Collections

class HabitSys {
    companion object {
        lateinit var habits: ArrayList<ArrayList<Habit>>
        fun prepareHabits() {
            habits = ArrayList()
            Collections.addAll(
                habits,
                arrayListOf(Habit(""),Habit(""),Habit(""),Habit(""),Habit("") ),//0
                arrayListOf(Habit(""),Habit(""),Habit(""),Habit(""),Habit("") ),//1
                arrayListOf(Habit(""),Habit(""),Habit(""),Habit(""),Habit("") ),//2
                arrayListOf(Habit(""),Habit(""),Habit(""),Habit(""),Habit("") ),//3
                arrayListOf(Habit(""),Habit(""),Habit(""),Habit(""),Habit("") ),//4
                arrayListOf(Habit(""),Habit(""),Habit(""),Habit(""),Habit("") ),//5
                arrayListOf(Habit(""),Habit(""),Habit(""),Habit(""),Habit("") ),//6
                arrayListOf(Habit(""),Habit(""),Habit(""),Habit(""),Habit("") ),//7
                arrayListOf(Habit(""),Habit(""),Habit(""),Habit(""),Habit("") )

            )
        }
    }
}