package com.example.myfirebaselogin

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Register : AppCompatActivity(){

    val mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val mBtnRegister = findViewById<View>(R.id.mBtnRegister) as Button

        mDatabase = FirebaseDatabase.getInstance().getReference("Names")

        mBtnRegister.setOnClickListener(View.OnClickListener {
            view -> register()
        })
    }

    private fun  register() {
        val mTvEmailreg = findViewById<View>(R.id.mTvEmail) as EditText
        val mTvPasswordreg = findViewById<View>(R.id.mTvPasswordreg) as EditText
        val mTvNamereg = findViewById<View>(R.id.mTvNamereg) as EditText

        var email = mTvEmailreg.text.toString()
        var password = mTvPasswordreg.text.toString()
        var name = mTvNamereg.text.toString()

        if (!name.isEmpty() && !password.isEmpty() && !email.isEmpty()){
           mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->
               if (task.isSuccessful){
                   val user = mAuth.currentUser
                   val uid = user!!.uid
                   mDatabase.child(uid).child("Name").setValue(name)
                    Toast.makeText(this, "Successfully Registered :(",Toast.LENGTH_LONG).show()
               }else{
                   Toast.makeText(this, "Error :(",Toast.LENGTH_LONG).show()
               }
           })
        }else{
            Toast.makeText(this, "Please fill all the fields :(",Toast.LENGTH_LONG).show()
        }
    }
}