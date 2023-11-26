package com.ncorti.kotlin.template.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.ncorti.kotlin.template.app.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val usernameInputField = findViewById<TextInputLayout>(R.id.usernameInputLayout)
        val passwordInputField = findViewById<TextInputLayout>(R.id.passwordInputLayout)

        binding.loginButton.setOnClickListener {

            val username = binding.usernameInputField.text.toString()
            val password = binding.passwordInputField.text.toString()

            val usernameError = getUsernameErrorMessage(username)
            val passwordError = getPasswordErrorMessage(password)

            usernameInputField.error = usernameError
            passwordInputField.error = passwordError

            if (usernameError == null && passwordError == null) {
                val intent = Intent(this@LoginActivity, LifeStylesActivity::class.java)
                startActivity(intent)
            }
        }

        binding.signupButton.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignupActivity::class.java)
            startActivity(intent)
        }

    }
}
