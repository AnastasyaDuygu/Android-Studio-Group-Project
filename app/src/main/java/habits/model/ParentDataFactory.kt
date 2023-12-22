package com.example.habits.model


import com.example.habits.adapter.db.CategoriesSys
import com.example.habits.adapter.db.HabitSys

object ParentDataFactory {
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

    private fun addHabits(): List<List<Habit>>{
        return HabitDataFactory.getHabit()
    }

    fun getParents() : List<Parent> {
        //categories = CategoriesSys.categories
        val parents = mutableListOf<Parent>()
        val habits : List<List<Habit>> = addHabits()
        var category: ArrayList<Parent>
        category = ArrayList()
        for (i in 0..<categories.size){
            category!!.add(Parent(addCategory(), habits[i]))
            parents.add(category[i])
        }
        /*val category0 = Parent(addCategory(), habits[0])
        parents.add(category0)
        val category1 = Parent(addCategory(), habits[1])
        parents.add(category1)*/
        return parents
    }
    //I LOST MY MIND TRYING TO MAKE THIS WORK BUT IT DOESNT WANT TO :(
    /*fun addNewCategory(name: String) : List<Parent>{
        for (x in 0..7){
            HabitSys.habits.add(arrayListOf(Habit("Habit 1"),Habit("Habit 2"),Habit("Habit 3") ))
        }

        categoriesPrepareData()
        categories.add(name)

        val parents = mutableListOf<Parent>()
        val habits : List<List<Habit>> = addHabits()
        var category: ArrayList<Parent>
        category = ArrayList()
        for (i in 0..categories.size-1){
            category!!.add(Parent(addCategory(), habits[i]))
            parents.add(category[i])
        }
        /*val parents = mutableListOf<Parent>()
        val habits : List<List<Habit>> = addHabits()
        i++
        val category1 = Parent(name, habits[i])
        parents.add(category1)*/
        return parents
    }

    fun categoriesPrepareData(){
        categories.add( "Health and Wellness")
        categories.add("Productivity and Time Management")
        categories.add("Mindfulness and Mental Health")
        categories.add("Learning and Growth")
        categories.add("Relationships")
        categories.add("Creativity and Hobbies")
        categories.add("Self-Care")
        categories.add("Professional Development")

    }*/
}