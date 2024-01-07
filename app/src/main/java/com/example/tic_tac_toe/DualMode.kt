package com.example.tic_tac_toe


class DualMode {
    companion object {
        const val PLAYER1 = "O"
        const val PLAYER2 = "X"
    }
    val board = Array(3) { arrayOfNulls<String>(3) }
    val availableCells: List<Cell>
        get() {
            val cells = mutableListOf<Cell>()
            for (i in board.indices) {
                for (j in board.indices) {
                    if (board[i][j].isNullOrEmpty()) {
                        cells.add(Cell(i, j))
                    }
                }
            }
            return cells
        }
    val isGameOver: Boolean
        get() = hasPlayer1Won() || hasPlayer2Won() || availableCells.isEmpty()
    fun hasPlayer1Won(): Boolean {
        if (board[0][0] == board[1][1] &&
            board[0][0] == board[2][2] &&
            board[0][0] == PLAYER1 ||
            board[0][2] == board[1][1] &&
            board[0][2] == board[2][0] &&
            board[0][2] == PLAYER1
        ) {
            return true
        }

        for (i in board.indices) {
            if (
                board[i][0] == board[i][1] &&
                board[i][0] == board[i][2] &&
                board[i][0] == PLAYER1 ||
                board[0][i] == board[1][i] &&
                board[0][i] == board[2][i] &&
                board[0][i] == PLAYER1
            ) {
                return true
            }
        }
        return false
    }

    fun hasPlayer2Won(): Boolean {

        if (board[0][0] == board[1][1] &&
            board[0][0] == board[2][2] &&
            board[0][0] == PLAYER2 ||
            board[0][2] == board[1][1] &&
            board[0][2] == board[2][0] &&
            board[0][2] == PLAYER2
        ) {
            return true
        }

        for (i in board.indices) {
            if (
                board[i][0] == board[i][1] &&
                board[i][0] == board[i][2] &&
                board[i][0] == PLAYER2 ||
                board[0][i] == board[1][i] &&
                board[0][i] == board[2][i] &&
                board[0][i] == PLAYER2
            ) {
                return true
            }
        }

        return false
    }
    fun placeMove(cell: Cell, player: String) {
        board[cell.i][cell.j] = player
    }
}