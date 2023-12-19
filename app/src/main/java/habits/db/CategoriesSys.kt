package com.example.habits.adapter.db

import java.util.Collections

class CategoriesSys {
    companion object {
        lateinit var categories: ArrayList<String>
        fun prepareCategories(){
            categories.add(0, "Health and Wellness")
            categories.add(1, "Productivity and Time Management")
            categories.add(2, "Mindfulness and Mental Health")
            categories.add(3, "Learning and Growth")
            categories.add(4, "Relationships")
            categories.add(5, "Creativity and Hobbies")
            categories.add(6, "Self-Care")
            categories.add(7, "Professional Development")
        }
    }
}