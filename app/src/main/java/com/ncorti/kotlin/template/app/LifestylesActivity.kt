package com.ncorti.kotlin.template.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ncorti.kotlin.template.app.Lifestyle.LifestyleData
import com.ncorti.kotlin.template.app.Lifestyle.LifestylesAdapter
import com.ncorti.kotlin.template.app.databinding.ActivityLifestylesBinding


class LifestylesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLifestylesBinding
    private lateinit var lifestylesAdapter: LifestylesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLifestylesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        lifestylesAdapter = LifestylesAdapter(this, LifestyleData.getList())
        binding.lifestylesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.lifestylesRecyclerView.adapter = lifestylesAdapter

        binding.okButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}