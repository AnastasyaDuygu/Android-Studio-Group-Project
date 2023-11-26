package com.ncorti.kotlin.template.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.ncorti.kotlin.template.app.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nameInputField = findViewById<TextInputLayout>(R.id.nameInputLayout)
        val usernameInputField = findViewById<TextInputLayout>(R.id.usernameInputLayout)
        val emailInputField = findViewById<TextInputLayout>(R.id.emailInputLayout)
        val passwordInputField = findViewById<TextInputLayout>(R.id.passwordInputLayout)
        val reEnterPasswordField = findViewById<TextInputLayout>(R.id.reEnterPasswordInputLayout)

        binding.signupButton.setOnClickListener {
            val name = binding.nameInputField.text.toString()
            val username = binding.usernameInputField.text.toString()
            val email = binding.emailInputField.text.toString()
            val password = binding.passwordInputField.text.toString()
            val reEnterPassword = binding.reEnterPasswordInputField.text.toString()

            val nameErrorMessage = getNameErrorMessage(name)
            val usernameErrorMessage = getUsernameErrorMessage(username)
            val emailErrorMessage = getEmailErrorMessage(email)
            val passwordErrorMessage = getPasswordErrorMessage(password)
            val reEnteredPasswordErrorMessage =
                getReEnteredPasswordErrorMessage(password, reEnterPassword)

            nameInputField.error = nameErrorMessage
            usernameInputField.error = usernameErrorMessage
            emailInputField.error = emailErrorMessage
            passwordInputField.error = passwordErrorMessage
            reEnterPasswordField.error = reEnteredPasswordErrorMessage


            if (
                nameErrorMessage == null
                && usernameErrorMessage == null
                && emailErrorMessage == null
                && passwordErrorMessage == null
                && reEnteredPasswordErrorMessage == null
            ) {
                val intent = Intent(this@SignupActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
