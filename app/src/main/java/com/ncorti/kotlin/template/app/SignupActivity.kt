package com.ncorti.kotlin.template.app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.ncorti.kotlin.template.app.databinding.ActivitySignupBinding
import com.ncorti.kotlin.template.app.userClass.HelperClass
import com.ncorti.kotlin.template.app.userClass.User

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

            val userNode = HelperClass.getDatabaseInstance().getReference("registeredUsers")

            try {
                if (
                    nameErrorMessage == null
                    && usernameErrorMessage == null
                    && emailErrorMessage == null
                    && passwordErrorMessage == null
                    && reEnteredPasswordErrorMessage == null
                ) {
                    HelperClass.getAuthenticationInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val uid = HelperClass.getAuthenticationInstance().currentUser?.uid //new generated UID for the user
                            if(uid!=null)
                            {
                                val newUser = User(email, name, password, username)
                                userNode.child(uid).setValue(newUser) //creates a new JSON entry
                                 val intent = Intent(this@SignupActivity, MainActivity::class.java) //send to main activity
                                 startActivity(intent)
                            }
                        } else {
                            Log.d("SIGN-UP", "Error: ${task.exception}")
                        }
                    }

                }
            }catch(e: Exception)
            {
                Log.e("EXCEPTION", "Sign-up failed", e)

            }

        }
    }
}
