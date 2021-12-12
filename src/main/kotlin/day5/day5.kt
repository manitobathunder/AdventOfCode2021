package day5

import day4.Number
import java.io.File

data class Coordinate(
    var crossedCounter: Int = 0
)

data class HydroGrid(
    var grid: Array<Array<Coordinate>> = Array(999) { Array(999) { Coordinate() } }
) {
    fun markGrid(x1: Int, y1: Int, x2: Int, y2: Int) {
        var x1 = x1
        var x2 = x2
        var y1 = y1
        var y2 = y2
        grid[x1][y1].crossedCounter++

        if (x1 == x2) {
            if (y1 > y2) {
                while (y1 > y2) {
                    y1 -= 1
                    grid[x1][y1].crossedCounter++
                }
            } else if (y1 < y2) {
                while (y1 < y2) {
                    y1 ++
                    grid[x1][y1].crossedCounter++
                }
            }
        } else if (y1 == y2) {
            if (x1 > x2) {
                while (x1 > x2) {
                    x1 -= 1
                    grid[x1][y1].crossedCounter++
                }
            } else if (x1 < x2) {
                while (x1 < x2) {
                    x1 ++
                    grid[x1][y1].crossedCounter++
                }
            }
        } else if ((x1 > x2) && (y1 > y2)) {
            while ((x1 > x2) && (y1 > y2)) {
                x1 -= 1
                y1 -= 1
                grid[x1][y1].crossedCounter++
            }
        } else if ((x1 > x2) && (y1 < y2)) {
            while ((x1 > x2) && (y1 < y2)) {
                x1 -= 1
                y1 ++
                grid[x1][y1].crossedCounter++
            }
        } else if ((x1 < x2) && (y1 > y2)) {
            while ((x1 < x2) && (y1 > y2)) {
                x1 ++
                y1 -= 1
                grid[x1][y1].crossedCounter++
            }
        } else if ((x1 < x2) && (y1 < y2)) {
            while ((x1 < x2) && (y1 < y2)) {
                x1 ++
                y1 ++
                grid[x1][y1].crossedCounter++
            }
        }
    }

    fun countMultiCrossed() : Int{
        var counter = 0
        for (row in 0 until 999) {
            for (col in 0 until 999) {
                if (grid[row][col].crossedCounter > 1){
                    counter++
                }
            }
        }

        return counter
    }
}

fun readData(): MutableList<List<List<Int>>> {
    val input = File("src/main/kotlin/day5/day5.txt").readLines().map {it.split(" -> ")}
    val coordinateList = mutableListOf<List<List<Int>>>()

    for (coordPair in input) {
        val startingCoords = coordPair[0].split(',').map {it.toInt()}
        val endingCoords = coordPair[1].split(',').map {it.toInt()}
        val direction = listOf(startingCoords, endingCoords)
        coordinateList.add(direction)
    }

    return coordinateList
}

fun solve1() {
    val directions = readData()

    var grid = HydroGrid()

    for (direction in directions) {
        var x1 = direction[0][0]
        var y1 = direction[0][1]
        var x2 = direction[1][0]
        var y2 = direction[1][1]

        if (x1 == x2 || y1 == y2) {
            grid.markGrid(x1, y1, x2, y2)
        }
    }

    val multiCrossed = grid.countMultiCrossed()
    println("Vertical and Horizontal Lines cross $multiCrossed times")
}

fun solve2() {
    val directions = readData()

    var grid = HydroGrid()

    for (direction in directions) {
        var x1 = direction[0][0]
        var y1 = direction[0][1]
        var x2 = direction[1][0]
        var y2 = direction[1][1]
        grid.markGrid(x1, y1, x2, y2)
    }


    val multiCrossed = grid.countMultiCrossed()
    println("All lines cross $multiCrossed times")
}

fun main() {
    solve1()
    solve2()
}