package com.example.tic_tac_toe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class Friend : AppCompatActivity() {
    private lateinit var playerName: PlayerName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.enter_player_name)
        val button: Button = findViewById(R.id.button)
        val player1EditText: EditText = findViewById(R.id.player1)
        val player2EditText: EditText = findViewById(R.id.player2)
        val player1 = player1EditText.text.toString()
        val player2 = player2EditText.text.toString()

        playerName = PlayerName(player1, player2)
        button.setOnClickListener {
            val player1 = player1EditText.text.toString()
            val player2 = player2EditText.text.toString()

            playerName = PlayerName(player1, player2)

            val intent = Intent(this, TwoPlayerMode::class.java)
            intent.putExtra("PLAYER1_INFO",player1)
            intent.putExtra("PLAYER2_INFO",player2)
            startActivity(intent)
            /*val intent = Intent(this, TwoPlayerMode()::class.java)
            startActivity(intent)*/
        }
    }
}