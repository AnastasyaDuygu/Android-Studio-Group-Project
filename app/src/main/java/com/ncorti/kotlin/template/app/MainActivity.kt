package com.ncorti.kotlin.template.app

import android.annotation.SuppressLint //For Gesture
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.service.autofill.UserData
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.habits.HabitsActivity
import com.google.android.material.snackbar.Snackbar
import com.ncorti.kotlin.template.app.databinding.ActivityMainBinding
import com.ncorti.kotlin.template.app.userClass.Constants
import com.ncorti.kotlin.template.app.userClass.User


class MainActivity : AppCompatActivity(), BottomFragment.BottomFragmentListener {
    //Gesture STEP 1
    val TAG:String="GESTURE"
    lateinit var gestureDetector: GestureDetector
    lateinit var mediaPlayer: MediaPlayer

    //Fragment STEP 1
    lateinit var bottomFragment: BottomFragment

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mediaPlayer = MediaPlayer.create(this,R.raw.soundeffect)
        //Fragment STEP 2
        bottomFragment = BottomFragment()

        binding.tvUsernameMain.text=Constants.USERDATA.name
        binding.lifestyle.text=Constants.USERDATA.email //add lifestyle selected here later
        //Gesture STEP 2
        gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onLongPress(e: MotionEvent) {
                super.onLongPress(e)
                Log.i(TAG, "onLongPress")
                displayUserDetails()
                Toast.makeText(this@MainActivity, "deneme", Toast.LENGTH_SHORT).show()
            }
        })
        //Gesture STEP 3
        binding.tvUsernameMain.setOnTouchListener { v, event ->
            gestureDetector.onTouchEvent(event)
            false
        }

        // Assuming you have an array of icons and text for each button
        val items = arrayOf(GridItemAdapter.GridItem("Goals", R.drawable.goals_icon), GridItemAdapter.GridItem("Statistics", R.drawable.statistics_icon),
            GridItemAdapter.GridItem("Sharing", R.drawable.sharing_icon), GridItemAdapter.GridItem("Notes", R.drawable.notes_icon),
            GridItemAdapter.GridItem("Reminders", R.drawable.reminders_icon), GridItemAdapter.GridItem("Progress Reports", R.drawable.report_icon))
        val adapter = GridItemAdapter(this, items)
        binding.gridView.adapter = adapter


        binding.gridView.setOnItemClickListener { _, _, position, _ ->
            val dialog = AlertDialog.Builder(this)
            mediaPlayer.start()

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

        /*binding.btnHabits.setOnClickListener {
            // Navigate to the Habits Layout
            val intent = Intent(this, HabitsActivity::class.java)
            startActivity(intent)
        }*/
    }
    //Gesture STEP 4
    override fun onTouchEvent(event: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }
    //Gesture STEP 5
    fun displayUserDetails(){
        val dialog = AlertDialog.Builder(this)
        val message = "Profile Details\n" +
                "Name: ${Constants.USERDATA.name}" +
                "\nUsername: ${Constants.USERDATA.username}" +
                "\nE-mail: ${Constants.USERDATA.email}"
        dialog.setMessage(message)
        dialog.setPositiveButton("OK") { dialogInterface, _ -> dialogInterface.dismiss() }
        dialog.show()
    }
    //Fragment STEP 3
    override fun onButtonClick() {
        // Navigate to the Habits Layout
        val intent = Intent(this, HabitsActivity::class.java)
        startActivity(intent)
        mediaPlayer.start()
        //Snackbar.make(binding.root, "works", Snackbar.LENGTH_SHORT).show()
    }

}
