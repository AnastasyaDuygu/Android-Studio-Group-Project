package com.example.habits.model

import com.example.habits.adapter.db.HabitSys

object HabitDataFactory {
    lateinit var habits : ArrayList<ArrayList<Habit>>
    //var category_size: Int = 0
    //private val habit_names = arrayListOf("Habit1","Habit2","Habit3","Habit4")

    //var x: Int = -1

    /*private fun addHabitNameForCategory(int: Int): String{
        x++
        if (x > habits[int].size-1)
            return "null"
        return habits[int][x].name

    }*/

    fun getHabit(): List<List<Habit>>{
        habits = HabitSys.habits
        /*habits = MainActivity.getData()
        val habits = mutableListOf<Habit>()
        category_size = ParentDataFactory.getCategorySize()-1
        repeat(5){
            var i : Int
            var habit: Habit
            for (i in 0..category_size){
                habit = Habit(addHabitNameForCategory(i))
                habits.add(habit)
            }
        }*/
        return habits
    }

}