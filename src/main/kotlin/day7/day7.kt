package day7

import java.io.File
import kotlin.math.absoluteValue
import kotlin.math.floor


fun IntRange.toIntArray(): IntArray {
    if (last < first)
        return IntArray(0)

    val result = IntArray(last - first + 1)
    var index = 0
    for (element in this)
        result[index++] = element
    return result
}

fun calculateFuelCostByMean(positions: MutableList<Int>, mean: Double) : Int {
    var fuelCost = 0
    val target = floor(mean).toInt()
    positions.forEach { number ->
        if (number != target) {
            val difference = (number - target).absoluteValue
            println(difference)
            val differenceArray: IntArray = (1 .. difference).toIntArray()
            fuelCost += differenceArray.sum()
            println("adding ${differenceArray.sum()} to fuel cost. Fuel cost now $fuelCost")
        }
    }
    return fuelCost
}

fun calculateFuelCostByMedian(positions: MutableList<Int>, median: Int) : Int {
    var fuelCost = 0
    positions.forEach { number ->
        if (number != median) {
            val difference = (number - median).absoluteValue
            fuelCost += difference
        }
    }

    return fuelCost
}

fun calculateStats(positions: MutableList<Int>): Pair<Double, Int> {
    val mean = positions.average()
    val sortedPositions = positions.sorted()
    val median = sortedPositions[sortedPositions.size/2]
    return Pair(mean, median)
}

fun readHorizontalPositions() : MutableList<Int>{
    val positions = File("src/main/kotlin/day7/day7.txt").readLines()[0]
        .split(",")
        .map { it.toInt() }
        .toMutableList()

    return positions
}

fun solve1() {
    val positions = readHorizontalPositions()
    val (mean, median) = calculateStats(positions)
    val fuelCost = calculateFuelCostByMedian(positions, median)
    println(fuelCost)
}

fun solve2() {
    val positions = readHorizontalPositions()
    println(positions)
    val (mean, median) = calculateStats(positions)
    val fuelCost = calculateFuelCostByMean(positions, mean)
    println(fuelCost)
}

fun main() {
    solve1()
    solve2()
}