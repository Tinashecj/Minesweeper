package com.example.minesweeper

class Cell {
    val BOMB = -1
    val BLANK = 0

    private var value = 0
    private var isRevealed = false
    private var isFlagged = false

    fun Cell(value: Int) {
        this.value = value
        isRevealed = false
        isFlagged = false
    }

    fun getValue(): Int {
        return value
    }

    fun isRevealed(): Boolean {
        return isRevealed
    }

    fun setRevealed(revealed: Boolean) {
        isRevealed = revealed
    }

    fun isFlagged(): Boolean {
        return isFlagged
    }

    fun setFlagged(flagged: Boolean) {
        isFlagged = flagged
    }
}