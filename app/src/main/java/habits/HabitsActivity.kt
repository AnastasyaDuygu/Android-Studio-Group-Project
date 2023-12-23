package com.example.habits

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.habits.adapter.ParentAdapter
import com.example.habits.model.ParentDataFactory
import com.ncorti.kotlin.template.app.MainActivity
import com.ncorti.kotlin.template.app.databinding.ActivityHabitsBinding
import kotlinx.coroutines.launch

class HabitsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHabitsBinding
    lateinit var recyclerView: RecyclerView
    lateinit var uid: String
    private lateinit var parentAdapter: ParentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHabitsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainIntent = intent
        //we have the uid for the database
        uid = intent.getStringExtra("UID")!!
        Log.d("Habit UID: ", "$uid")

        lifecycleScope.launch {
            try {
                // Step 1: Load habits data
                HabitSys.prepareHabits(uid)

                // Step 2: Observe changes in habits LiveData
                HabitSys.habits.observeForever { habits ->
                    // Step 3: Trigger logic that depends on the loaded data
                    Log.d("_HABITS_OBSERVER", habits.toString())
                    // Call the logic that uses the habits data here, like initializing the recycler view
                    lifecycleScope.launch{
                        initRecycler()
                    }
                }
            } catch (e: Exception) {
                Log.e("HabitsActivity", "Error during data retrieval", e)
            }
        }

        //CategoriesSys.prepareCategories()
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

      suspend fun initRecycler() {
        recyclerView = binding.rvParent
        parentAdapter = ParentAdapter(ParentDataFactory.getParents(uid), uid)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HabitsActivity, RecyclerView.VERTICAL, false)
            adapter =parentAdapter
        }
    }
}