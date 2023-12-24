package com.ncorti.kotlin.template.app

import android.content.Intent
import android.os.Bundle
import android.service.autofill.UserData
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.habits.HabitsActivity
import com.ncorti.kotlin.template.app.databinding.ActivityMainBinding
import com.ncorti.kotlin.template.app.userClass.Constants
import com.ncorti.kotlin.template.app.userClass.User


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvUsernameMain.text=Constants.USERDATA.name

        // Assuming you have an array of icons and text for each button
        val items = arrayOf(GridItemAdapter.GridItem("Goals", R.drawable.goals_icon), GridItemAdapter.GridItem("Statistics", R.drawable.statistics_icon),
            GridItemAdapter.GridItem("Sharing", R.drawable.sharing_icon), GridItemAdapter.GridItem("Notes", R.drawable.notes_icon),
            GridItemAdapter.GridItem("Reminders", R.drawable.reminders_icon), GridItemAdapter.GridItem("Progress Reports", R.drawable.report_icon))
        val adapter = GridItemAdapter(this, items)
        binding.gridView.adapter = adapter

        binding.gridView.setOnItemClickListener { _, _, position, _ ->
            val dialog = AlertDialog.Builder(this)


            val message = when (position) {
                0 -> "Your current goals are: \nDrink 4L of Water \nEat 150g Protein\nWe go Jim"
                1 -> "Statistics:\nWeight: 65Kg\nHeight: 180cm"
                2 -> "Sharing options and information"
                3 -> "Your notes:\n Remember to cook"
                4 -> "Set or view reminders"
                5 -> "Progress Reports:\nOct Weight: 64Kg\nNov Weight: 65Kg\nDec Weight: 65Kg"
                else -> "Unknown Item"
            }

            dialog.setMessage(message)
            dialog.setPositiveButton("OK") { dialogInterface, _ -> dialogInterface.dismiss() }
            dialog.show()
        }

        binding.btnHabits.setOnClickListener {
            // Navigate to the Habits Layout
            val intent = Intent(this, HabitsActivity::class.java)
            startActivity(intent)
        }
    }
}
