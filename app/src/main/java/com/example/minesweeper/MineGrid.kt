package com.example.minesweeper

class MineGrid {
    private var cells: MutableList<Cell>? = null
    private var size = 0

    fun MineGrid(size: Int) {
        this.size = size
        cells = ArrayList()
        for (i in 0 until size * size) {
            cells.add(Cell(Cell.BLANK))
        }
    }

    fun generateGrid(totalBombs: Int) {
        var bombsPlaced = 0
        while (bombsPlaced < totalBombs) {
            val x: Int = Random().nextInt(size)
            val y: Int = Random().nextInt(size)
            if (cellAt(x, y)!!.getValue() == Cell.BLANK) {
                cells!![x + y * size] = Cell(Cell.BOMB)
                bombsPlaced++
            }
        }
        for (x in 0 until size) {
            for (y in 0 until size) {
                if (cellAt(x, y)!!.getValue() != Cell.BOMB) {
                    val adjacentCells = adjacentCells(x, y)
                    var countBombs = 0
                    for (cell in adjacentCells) {
                        if (cell.getValue() == Cell.BOMB) {
                            countBombs++
                        }
                    }
                    if (countBombs > 0) {
                        cells!![x + y * size] = Cell(countBombs)
                    }
                }
            }
        }
    }

    fun cellAt(x: Int, y: Int): Cell? {
        return if (x < 0 || x >= size || y < 0 || y >= size) {
            null
        } else cells!![x + y * size]
    }

    fun adjacentCells(x: Int, y: Int): List<Cell> {
        val adjacentCells: MutableList<Cell> = ArrayList()
        val cellsList: MutableList<Cell?> = ArrayList()
        cellsList.add(cellAt(x - 1, y))
        cellsList.add(cellAt(x + 1, y))
        cellsList.add(cellAt(x - 1, y - 1))
        cellsList.add(cellAt(x, y - 1))
        cellsList.add(cellAt(x + 1, y - 1))
        cellsList.add(cellAt(x - 1, y + 1))
        cellsList.add(cellAt(x, y + 1))
        cellsList.add(cellAt(x + 1, y + 1))
        for (cell in cellsList) {
            if (cell != null) {
                adjacentCells.add(cell)
            }
        }
        return adjacentCells
    }

    fun toIndex(x: Int, y: Int): Int {
        return x + y * size
    }

    fun toXY(index: Int): IntArray? {
        val y = index / size
        val x = index - y * size
        return intArrayOf(x, y)
    }

    fun revealAllBombs() {
        for (c in cells!!) {
            if (c.getValue() == Cell.BOMB) {
                c.setRevealed(true)
            }
        }
    }

    fun getCells(): List<Cell>? {
        return cells
    }
}