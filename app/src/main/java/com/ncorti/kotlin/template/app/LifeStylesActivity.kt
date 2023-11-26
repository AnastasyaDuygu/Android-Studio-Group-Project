package com.ncorti.kotlin.template.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ncorti.kotlin.template.app.databinding.ActivityLifestylesBinding

class LifeStylesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLifestylesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLifestylesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}