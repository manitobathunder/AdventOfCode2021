package `Day 1`
import java.io.File

fun countDepth(radarList: List<Int>) : Int {
    var depthCount = 0
    var x = 0
    var y = 1
    while (y < radarList.size) {
        if (radarList[x] < radarList[y]) {
            depthCount++
            x++
            y++
        } else {
            x++
            y++
        }
    }
    return depthCount
}

fun countSlide(radarList: List<Int>) : Int {
    var depthCount = 0
    var x = 0
    var y = 1
    var z = 2
    while (z < (radarList.size - 1)) {
        var window1 = (radarList[x]+radarList[y]+radarList[z])
        var window2 = (radarList[x+1]+radarList[y+1]+radarList[z+1])
        if (window1 < window2) {
            depthCount++
            x++
            y++
            z++
        } else {
            x++
            y++
            z++
        }
    }
    return depthCount
}

fun main() {
    val radar = File("src/main/kotlin/Day 1/Day 1.txt")
        .readLines().map{ it.toInt() }

    var depthCount = countDepth(radar)
    println(depthCount)

    var depthSlide = countSlide(radar)
    println(depthSlide)
}