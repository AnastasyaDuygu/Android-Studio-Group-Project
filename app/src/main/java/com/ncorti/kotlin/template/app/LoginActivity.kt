package com.ncorti.kotlin.template.app

import android.content.Intent
import android.health.connect.datatypes.units.Length
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.ncorti.kotlin.template.app.databinding.ActivityLoginBinding
import com.ncorti.kotlin.template.app.userClass.Constants
import com.ncorti.kotlin.template.app.userClass.HelperClass
import com.ncorti.kotlin.template.app.userClass.User


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //instantiating firebase instance
        FirebaseApp.initializeApp(this)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val usernameInputField = findViewById<TextInputLayout>(R.id.usernameInputLayout)
        val passwordInputField = findViewById<TextInputLayout>(R.id.passwordInputLayout)
        mediaPlayer = MediaPlayer.create(this,R.raw.soundeffect)

        binding.loginButton.setOnClickListener {
            mediaPlayer.start()

            val email = binding.usernameInputField.text.toString()
            val password = binding.passwordInputField.text.toString()

            val usernameError = getUsernameErrorMessage(email)
            val passwordError = getPasswordErrorMessage(password)

            usernameInputField.error = usernameError
            passwordInputField.error = passwordError
            //authentication
         
            val userNode = HelperClass.getDatabaseInstance().getReference("registeredUsers") //getting registeredUsers Node

            try{
                if (usernameError == null && passwordError == null) {

                    HelperClass.getAuthenticationInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener{
                            task ->
                        if(task.isSuccessful)
                        {
                            //sign in is successful
                            val user =  HelperClass.getAuthenticationInstance().currentUser
                            val uid = user?.uid //the uid, for example its User1 for this one (manually entered). Will have to use auto generated uids for  every signed up user
                            Log.d("UID", "UID for the user: $uid")
                            uid?.let { //we already got the UID so dont need to iterate over the collection with foreach in the database
                                userNode.child(it).addListenerForSingleValueEvent(object: ValueEventListener{
                                    override fun onDataChange(snapshot: DataSnapshot) {
                                        if(snapshot.exists())
                                        {
                                            val userData= snapshot.getValue(User::class.java) //converts firebase snapshot data and populate the instance of that class with it
                                            Log.d("USERDATA", "$userData")
                                            //now pass this created instance of the user to the next activity
                                            val intent = Intent(this@LoginActivity, LifestylesActivity::class.java)
                                            Constants.UID=uid
                                            Constants.USERDATA= userData!!
                                            startActivity(intent)

                                        }else
                                        {
                                            Log.d("USERDATA", "User data does not exist for UID: $uid")
                                        }
                                    }
                                    override fun onCancelled(error: DatabaseError) {
                                        Log.e("FirebaseError", "Error: ${error.message}")
                                    }
                                })
                            }
                        }else
                        {
                            Log.e("EXCEPTION", "Sign-in failed", task.exception)
                            Toast.makeText(this@LoginActivity, "Wrong credentials", Toast.LENGTH_LONG).show()

                        }
                    }
                }

            }catch (e: Exception)
            {
                Log.e("EXCEPTION", "Sign-in failed", e)
                Toast.makeText(this@LoginActivity, "Wrong credentials", Toast.LENGTH_LONG).show()

            }
        }

        binding.signupButton.setOnClickListener {
            mediaPlayer.start()
            val intent = Intent(this@LoginActivity, SignupActivity::class.java)
            startActivity(intent)
        }

    }
}
