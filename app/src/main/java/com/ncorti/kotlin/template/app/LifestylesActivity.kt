package com.ncorti.kotlin.template.app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ncorti.kotlin.template.app.Lifestyle.LifestyleData
import com.ncorti.kotlin.template.app.Lifestyle.LifestylesAdapter
import com.ncorti.kotlin.template.app.databinding.ActivityLifestylesBinding
import com.ncorti.kotlin.template.app.userClass.Constants
import com.ncorti.kotlin.template.app.userClass.User


class LifestylesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLifestylesBinding
    private lateinit var lifestylesAdapter: LifestylesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLifestylesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.usernameTextView.text=Constants.USERDATA.name

        lifestylesAdapter = LifestylesAdapter(this, LifestyleData.getList())
        binding.lifestylesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.lifestylesRecyclerView.adapter = lifestylesAdapter

        binding.okButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}