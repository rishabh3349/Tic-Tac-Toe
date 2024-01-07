package com.example.tic_tac_toe


import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class TwoPlayerMode : AppCompatActivity() {
    private val boardCells = Array(3) { arrayOfNulls<ImageView>(3) }
    var dualBoard = DualMode()
    private var currentPlayer = DualMode.PLAYER1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.two_player_mode)
        loadBoard()
        val button: Button = findViewById(R.id.button_restart)
        button.setOnClickListener {
            dualBoard = DualMode()
            currentPlayer = DualMode.PLAYER1
            val textView: TextView = findViewById(R.id.text_view_result)
            textView.text = ""
            mapBoardToUi()
        }
    }

    private fun mapBoardToUi() {
        for (i in dualBoard.board.indices) {
            for (j in dualBoard.board.indices) {
                when (dualBoard.board[i][j]) {
                    DualMode.PLAYER1 -> {
                        boardCells[i][j]?.setImageResource(R.drawable.circle)
                        boardCells[i][j]?.isEnabled = false
                    }
                    DualMode.PLAYER2 -> {
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
        private val j: Int,
    ) : View.OnClickListener {
        override fun onClick(p0: View?) {
            if (!dualBoard.isGameOver) {
                val cell = Cell(i, j)

                if (currentPlayer == DualMode.PLAYER1) {
                    dualBoard.placeMove(cell, DualMode.PLAYER1)
                    currentPlayer = DualMode.PLAYER2
                } else {
                    dualBoard.placeMove(cell, DualMode.PLAYER2)
                    currentPlayer = DualMode.PLAYER1
                }

                mapBoardToUi()
                val text_view: TextView = findViewById(R.id.text_view_result)
                val player1 = intent.getStringExtra("PLAYER1_INFO")
                val player2 = intent.getStringExtra("PLAYER2_INFO")
                when {
                    dualBoard.hasPlayer1Won() -> text_view.text = "$player1 Won"
                    dualBoard.hasPlayer2Won() -> text_view.text ="$player2 Won"
                    dualBoard.isGameOver -> text_view.text = "Game Tied"
                    !(dualBoard.isGameOver)->text_view.text =convert(currentPlayer,player1,player2)
                }
            }
        }
        private fun convert(currentPlayer:String,player1:String?,player2:String?):String{
            if(currentPlayer=="X") return "$player2's Turn"
            else return "$player1's Turn"
        }
    }
}
