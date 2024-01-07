package com.example.tic_tac_toe

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.coroutines.delay
import android.os.Handler

import android.os.Looper
class Computer:AppCompatActivity() {
    private val boardCells = Array(3) { arrayOfNulls<ImageView>(3) }
    var board = Board()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.play_with_computer)
        loadBoard()
        val button: Button = findViewById(R.id.button_restart)
        button.setOnClickListener {
            board = Board()
            val textView: TextView = findViewById(R.id.text_view_result)
            textView.text = ""
            mapBoardToUi()
        }
    }
    private fun mapBoardToUi() {
        for (i in board.board.indices) {
            for (j in board.board.indices) {
                when (board.board[i][j]) {
                    Board.PLAYER -> {
                        boardCells[i][j]?.setImageResource(R.drawable.circle)
                        boardCells[i][j]?.isEnabled = false
                    }
                    Board.COMPUTER -> {
                        boardCells[i][j]?.setImageResource(R.drawable.cross)
                        boardCells[i][j]?.isEnabled = false
                    }
                    else -> {
                        boardCells[i][j]?.setImageResource(0)
                        boardCells[i][j]?.isEnabled = true
                    }
                }
            }
        }
    }
    private fun loadBoard() {
        for (i in boardCells.indices) {
            for (j in boardCells.indices) {
                boardCells[i][j] = ImageView(this)
                boardCells[i][j]?.layoutParams = GridLayout.LayoutParams().apply {
                    rowSpec = GridLayout.spec(i)
                    columnSpec = GridLayout.spec(j)
                    width = 250
                    height = 230
                    bottomMargin = 5
                    topMargin = 5
                    leftMargin = 5
                    rightMargin = 5
                }
                boardCells[i][j]?.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                boardCells[i][j]?.setOnClickListener(CellClickListener(i, j))
                val gridLayout: GridLayout = findViewById(R.id.layout_board)
                gridLayout.addView(boardCells[i][j])
            }
        }
    }
    inner class CellClickListener(
        private val i: Int,
        private val j: Int
    ) : View.OnClickListener {

        override fun onClick(p0: View?) {
            if (!board.isGameOver) {
                val cell = Cell(i, j)
                board.placeMove(cell, Board.PLAYER)
                mapBoardToUi()
                val handler = Handler(Looper.getMainLooper())
                handler.postDelayed({
                    board.minimax(0, Board.COMPUTER)
                    board.computersMove?.let {
                        board.placeMove(it, Board.COMPUTER)
                        mapBoardToUi()
                        val text_view: TextView = findViewById(R.id.text_view_result)
                        when {
                            board.hasComputerWon() -> text_view.text = "Computer Won"
                            board.hasPlayerWon() -> text_view.text = "Player Won"
                            board.isGameOver -> text_view.text = "Game Tied"
                        }
                    }
                }, 400)
            }
        }
    }
}