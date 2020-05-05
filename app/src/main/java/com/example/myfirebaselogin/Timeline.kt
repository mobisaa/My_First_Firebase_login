package com.example.myfirebaselogin

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.solver.widgets.Snapshot
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Timeline : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline)

        val dispTxt = findViewById<View>(R.id.mTvTimeline) as TextView

        mDatabase = FirebaseDatabase.getInstance().getReference("Names")

        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun  onCancelled(error: DatabaseError){
                TODO("not implemented") //To change
            }

            override fun onDataChange(snapshot: DataSnapshot){
                val result = snapshot.child("Name").toString()
                dispTxt.text = "Welcome" + result
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item!!.itemId == R.id.mMitSignout){
            mAuth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
            Toast.makeText(this, "Logged out :(",Toast.LENGTH_LONG).show()

        }
        return super.onOptionsItemSelected(item)
    }

}