package com.example.habits.model

data class Parent (
    val categoryTitle: String = "",
    val items: MutableList<Habit>
)