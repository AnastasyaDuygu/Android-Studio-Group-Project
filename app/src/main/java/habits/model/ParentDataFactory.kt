package com.example.habits.model


import HabitSys.Companion.prepareHabits
import android.content.Context
import android.util.Log
import kotlin.math.log

object ParentDataFactory {
    val maxSize: Int = 10
    //lateinit var categories: ArrayList<String>
    var categories = arrayListOf("Health and Wellness", "Productivity and Time Management",
        "Mindfulness and Mental Health","Learning and Growth",
        "Relationships", "Creativity and Hobbies",
        "Self-Care","Professional Development","Other")
    var i: Int = -1

    private fun addCategory(): String{
        i++
        if (i > categories.size-1)
            return "null"
        return categories[i]

    }

    suspend fun getParents(context: Context, uid: String): MutableList<Parent> {
        //categories = CategoriesSys.categories
        var initialList: MutableList<Habit> = MutableList(maxSize) { Habit() }
        val parents = mutableListOf<Parent>()
        val habits : MutableList<Habit> = prepareHabits(context, uid)
        println("Habits: $habits")
        var category: ArrayList<Parent> = ArrayList()
        for (i in 0..<categories.size){
            val tempList: ArrayList<Habit> = ArrayList()
            val cat= addCategory()
            for(j in 0 ..<habits.size)
            {
                val value=habits[j].categoryTitle
                if(cat==value)
                {
                    tempList.add(habits[j])
                    println("TempList: $tempList")
                }
            }
            initialList= (tempList + initialList).take(maxSize).toMutableList()
            category!!.add(Parent(cat, initialList))
            parents.add(category[i])
            initialList = MutableList(maxSize) { Habit() }
        }
        i=-1
        return parents
    }

}

