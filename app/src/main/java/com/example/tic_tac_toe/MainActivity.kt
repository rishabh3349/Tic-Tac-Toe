package com.example.tic_tac_toe

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import com.example.tic_tac_toe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button1:Button=findViewById(R.id.button1)
        val button2:Button=findViewById(R.id.button2)
        button1.setOnClickListener {
            val intent = Intent(this, Computer::class.java)
            startActivity(intent)
        }
        button2.setOnClickListener {
            val intent = Intent(this, Friend::class.java)
            startActivity(intent)
        }
    }
}