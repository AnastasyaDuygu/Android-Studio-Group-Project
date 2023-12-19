package com.example.habits

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.habits.adapter.ParentAdapter
import com.example.habits.adapter.db.HabitSys
import com.example.habits.model.ParentDataFactory
import com.ncorti.kotlin.template.app.databinding.ActivityHabitsBinding

class HabitsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHabitsBinding
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHabitsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        HabitSys.prepareHabits()
        //CategoriesSys.prepareCategories()
        initRecycler()
        /*binding.btnAddCategory.setOnClickListener{
        }*/
    }

    private fun initRecycler() {
        recyclerView = binding.rvParent

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HabitsActivity, RecyclerView.VERTICAL, false)
            adapter = ParentAdapter(ParentDataFactory.getParents())
        }
    }
}