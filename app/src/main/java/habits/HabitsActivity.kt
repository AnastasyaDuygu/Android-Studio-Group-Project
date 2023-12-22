package com.example.habits

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.habits.adapter.ParentAdapter
import com.example.habits.adapter.db.HabitSys
import com.example.habits.model.ParentDataFactory
import com.ncorti.kotlin.template.app.MainActivity
import com.ncorti.kotlin.template.app.databinding.ActivityHabitsBinding

class HabitsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHabitsBinding
    lateinit var recyclerView: RecyclerView
    lateinit var uid: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHabitsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainIntent = intent
        //we have the uid for the database
        uid = intent.getStringExtra("UID")!!
        Log.d("Habit UID: ", "$uid")



        HabitSys.prepareHabits()
        //CategoriesSys.prepareCategories()
        initRecycler()
        /*binding.btnAddCategory.setOnClickListener{
        }*/
        /*var xActivityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            //Toast.makeText(this, "Working", Toast.LENGTH_SHORT).show()
        }*/
        binding.btnHabitsToMain.setOnClickListener{
            val switchActivityIntent: Intent
            switchActivityIntent = Intent(this, MainActivity::class.java)
            startActivity(switchActivityIntent)
        }
    }

    private fun initRecycler() {
        recyclerView = binding.rvParent

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HabitsActivity, RecyclerView.VERTICAL, false)
            adapter = ParentAdapter(ParentDataFactory.getParents(), uid)
        }
    }
}