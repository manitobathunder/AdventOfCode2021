package `Day 2`
import java.io.File
import kotlin.text.digitToInt as digitToInt1

fun trackSubMult(instructions: List<List<String>>) : Int {
    var horizontal = 0
    var depth = 0
    for (instruction in instructions) {
        when (instruction[0]) {
            "forward" -> horizontal += instruction[1].toInt()
            "up" -> depth -= instruction[1].toInt()
            "down" -> depth += instruction[1].toInt()
        }
    }
    return (horizontal * depth)
}

fun trackSubAim(instructions: List<List<String>>) : Int {
    var horizontal = 0
    var depth = 0
    var aim = 0
    for (instruction in instructions) {
        when (instruction[0]) {
            "forward" -> {
                horizontal += instruction[1].toInt()
                depth += (aim * instruction[1].toInt())
            }
            "up" -> aim -= instruction[1].toInt()
            "down" -> aim += instruction[1].toInt()
        }
    }
    return (horizontal * depth)
}

fun main() {
    val instructions = File("src/main/kotlin/Day 2/day2.txt").readLines().map { it.split(" ")}
    val answer1 = trackSubMult(instructions)
    println(answer1)
    val answer2 = trackSubAim(instructions)
    println(answer2)
}