package com.ncorti.kotlin.template.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ncorti.kotlin.template.app.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Set the username from a saved instance or Intent


        // Assuming you have an array of icons and text for each button
        val items = arrayOf("Set Goals", "Statistics", "Sharing", "Notes", "Reminders", "Progress Reports")
        val adapter = GridItemAdapter(this, items)
        binding.gridView.adapter = adapter

        binding.gridView.setOnItemClickListener { parent, view, position, id ->
            // Handle grid button clicks
            // You can open dialogs or new activities based on the position
        }

        binding.btnHabits.setOnClickListener {
            // Navigate to the Habits Layout
            //val intent = Intent(this, HabitsActivity::class.java)
            startActivity(intent)
        }
    }
}
