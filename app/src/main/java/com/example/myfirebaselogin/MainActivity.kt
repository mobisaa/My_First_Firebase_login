package com.example.myfirebaselogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mBtnLogin = findViewById<View>(R.id.mBtnLogin) as Button
        val mTvLink = findViewById<View>(R.id.mTvLink) as TextView

        mBtnLogin.setOnClickListener(View.OnClickListener {
            view -> login()
        })

        mTvLink.setOnClickListener(View.OnClickListener {
                view -> register()
        })
    }

    private fun login () {
        val mTvEmail = findViewById<View>(R.id.mTvEmail) as EditText
        val mTvPassword = findViewById<View>(R.id.mTvPassword) as EditText

        var email = mTvEmail.text.toString()
        var password = mTvPassword.text.toString()

        if (!email.isEmpty() && !password.isEmpty()){
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->
                if (task.isSuccessful){
                    startActivity(Intent(this, Timeline :: class.java))
                    Toast.makeText(this, "Successfully logged in :)", Toast.LENGTH_LONG).show()
                }else {
                    Toast.makeText(this, "Error :(", Toast.LENGTH_LONG).show()
                }
            })
        }else{
            Toast.makeText(this, "Please fill up all the fields :(", Toast.LENGTH_LONG).show()
        }

    }

    private fun register() {
        startActivity(Intent(this, Register :: class.java))
    }
}
