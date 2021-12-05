package day2
import java.io.File

fun trackSubMulti(instructions : List<Pair<String, Int>>) : Int {
    var horizontal = 0
    var depth = 0
    for ((command, amt) in instructions) {
        when (command) {
            "forward" -> horizontal += amt
            "up" -> depth -= amt
            "down" -> depth += amt
        }
    }
    return (horizontal * depth)
}

fun trackSubAim(instructions : List<Pair<String, Int>>) : Int {
    var horizontal = 0
    var depth = 0
    var aim = 0
    for ((command, amt) in instructions) {
        when (command) {
            "forward" -> {
                horizontal += amt
                depth += (aim * amt)
            }
            "up" -> aim -= amt
            "down" -> aim += amt
        }
    }
    return (horizontal * depth)
}

fun main() {
    val instructions = File("src/main/kotlin/day2/day2.txt").readLines().map { it.split(" ")
        .let { (direction, value) -> Pair(direction, value.toInt())}}
    val answer1 = trackSubMulti(instructions)
    println(answer1)
    val answer2 = trackSubAim(instructions)
    println(answer2)
}