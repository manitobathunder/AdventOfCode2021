package day4

import java.io.File

data class Number(
    val value: Int,
    var isMarked: Boolean = false,
)

class Board(
    val data: Array<Array<Number>> = Array(5) { Array(5) {Number(0)} }
) {
    override fun equals(other: Any?): Boolean {
        if (other !is Board) return false
        return data.contentEquals(other.data)
    }

    override fun hashCode(): Int {
        return data.contentHashCode()
    }

    override fun toString(): String {
        return data.joinToString("\n") { row ->
            row.joinToString(" ") { number ->
                if (number.isMarked) "X" else number.value.toString()
            }
        }
    }

    fun mark(value: Int){
        data.forEach { row ->
            row.forEach { number ->
                if (number.value == value) number.isMarked = true
            }
        }
    }

    fun sumUnmarkedNumbers(): Int {
        return data.flatten().filter { !it.isMarked }.sumBy { it.value }
    }

    fun isWinner(): Boolean {
        return isRowWinner() || isColWinner()
    }

    private fun isRowWinner(): Boolean {
        for (y in 0 until 5) {
            var winner = true
            for (x in 0 until 5) {
                if (!data[y][x].isMarked) {
                    winner = false
                }
            }
            if (winner) return true
        }
        return false
    }

    private fun isColWinner(): Boolean {
        for (x in 0 until 5) {
            var winner = true
            for (y in 0 until 5) {
                if (!data[y][x].isMarked) {
                    winner = false
                }
            }
            if (winner) return true
        }
        return false
    }
}

fun readBoards(data: List<String>, position: Int): Board {
    val boardData = data.subList(position, position + 5)
    val board = Board()

    boardData.forEachIndexed { row, line ->
        val rowData = line.split(' ').filter { it.isNotEmpty() }.map { it.toInt() }
        rowData.forEachIndexed { column, value ->
            board.data[row][column] = Number(value)
        }
    }
    return board
}

fun readData() : Pair<List<Int>, List<Board>> {
    val values = File("src/main/kotlin/day4/day4.txt").readLines()

    val drawnNumbers = values[0].split(',').map { it.toInt() }
    val boards = mutableListOf<Board>()

    for (i in 2 until values.size step 6) {
        val board = readBoards(values, i)
        boards.add(board)
    }
    return Pair(drawnNumbers, boards)
}

fun findWinningBoard(numbers: List<Int>, boards: List<Board>): Pair<Int, Board> {
    for (number in numbers){
        for (board in boards){
            board.mark(number)
            if (board.isWinner()) {
                return Pair(number, board)
            }
        }
    }
    throw IllegalStateException("No winning board found.")
}

fun solvePart1(){
    val (numbers, boards) = readData()

    val (number, board) = findWinningBoard(numbers, boards)

    println("Winning number: $number")
    println("Winning board:")
    println(board)

    val sum = board.sumUnmarkedNumbers()

    println("Sum of unmarked numbers: $sum")
    println("Result is: ${sum * number}")
}

fun solvePart2(){
    val (numbers, boards) = readData()

    var boardCandidates = boards.toMutableList()

    var lastNumber = 0

    while (boardCandidates.size > 1) {
        val (number, winningBoard) = findWinningBoard(numbers, boardCandidates)
        lastNumber = number
        boardCandidates.remove(winningBoard)
    }

    val board = boardCandidates[0]
    val (number, winningBoard) = findWinningBoard(numbers, listOf(board))

    println("Winning number: $number")
    println("Winning board:")
    println(winningBoard)

    val sum = winningBoard.sumUnmarkedNumbers()

    println("Sum of unmarked numbers: $sum")
    println("Result is: ${sum * number}")
}

fun main() {
    solvePart1()
    solvePart2()
}
