package com.ncorti.kotlin.template.app

import android.content.Intent
import android.os.Bundle
import android.service.autofill.UserData
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.habits.HabitsActivity
import com.ncorti.kotlin.template.app.databinding.ActivityMainBinding
import com.ncorti.kotlin.template.app.userClass.User


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var uid: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Set the username from a saved instance or Intent
        val mainIntent = intent
        //we have the uid for the database
        uid = intent.getStringExtra("UID")!!
        Log.d("Habit UID: ", "$uid")
        val userData = intent.getSerializableExtra("UserData") as User
        Log.d("User Data", userData.toString())
        binding.tvUsernameMain.text=userData.name

        // Assuming you have an array of icons and text for each button
        val items = arrayOf(GridItemAdapter.GridItem("Set Goals", R.drawable.icon), GridItemAdapter.GridItem("Statistics", R.drawable.icon),
            GridItemAdapter.GridItem("Sharing", R.drawable.icon), GridItemAdapter.GridItem("Notes", R.drawable.icon),
            GridItemAdapter.GridItem("Reminders", R.drawable.icon), GridItemAdapter.GridItem("Progress Reports", R.drawable.icon))
        val adapter = GridItemAdapter(this, items)
        binding.gridView.adapter = adapter

        binding.gridView.setOnItemClickListener { parent, view, position, id ->
            // Handle grid button clicks
            // You can open dialogs or new activities based on the position
        }

        binding.btnHabits.setOnClickListener {
            // Navigate to the Habits Layout
            val intent = Intent(this, HabitsActivity::class.java)
            intent.putExtra("UID", uid)
            intent.putExtra("UserData",userData)
            startActivity(intent)
        }
    }
}
