package day6

import java.io.File

fun MutableMap<Int, Long>.simulateFishBabies() {
    val birthingFish = get(0) ?: 0

    for (i in 1 .. 8) {
        val count = get(i) ?: 0
        put(i - 1, count)
    }
    put(8, birthingFish)
    val sixFishCount = get(6) ?: 0
    put(6, sixFishCount + birthingFish)
}

fun readData(): MutableMap<Int, Long> {
    val initialFish = File("src/main/kotlin/day6/day6.txt").readLines()[0]
        .split(',')
        .map { it.toInt() }

    val map = mutableMapOf<Int, Long>()
        initialFish.forEach { fish ->
            val count = map.getOrDefault(fish, 0)
            map[fish] = count + 1
        }

    return map
}

fun solve1() {
    var initialFish = File("src/main/kotlin/day6/day6.txt").readLines()
    var fishList = initialFish[0].split(",").map { it.toInt() }.toMutableList()

    for (day in 0 until 80) {
        for (i in 0 until (fishList.size)) {
            fishList[i] += -1
            if (fishList[i] == -1) {
                fishList[i] += 7
            }
        }
        val birthingFish = fishList.count { it == 0 }
        if (birthingFish >= 1) {
            for (count in 0 until birthingFish) {
                fishList.add(9)
            }
        }
    }
    println(fishList.size)
}

fun solve2() {
    var map = readData()
    println(map)
    repeat(256) {
        map.simulateFishBabies()
    }
    val totalFish = map.values.sum()
    println(totalFish)
}

fun main() {
    solve1()
    solve2()


}