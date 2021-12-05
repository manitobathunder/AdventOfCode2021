package day3
import java.io.File
import kotlin.math.pow

fun convertBinaryToDecimal(num: Long): Int {
    var num = num
    var decimalNumber = 0
    var i = 0
    var remainder: Long

    while (num.toInt() != 0) {
        remainder = num % 10
        num /= 10
        decimalNumber += (remainder * 2.0.pow(i.toDouble())).toInt()
        ++i
    }
    return decimalNumber
}

fun solveConsumption(binary: List<String>) : Int {
    var gamma = ""
    for (i in (0..11))
        gamma += getGammaBits(binary, i)
    val epsilon: String = getEpsilonBits(gamma)
    return (convertBinaryToDecimal(gamma.toLong()) * convertBinaryToDecimal(epsilon.toLong()))
}

fun getGammaBits(binary: List<String>, i: Int) : Char {
    val gammaBinMap = binary.groupingBy { it.elementAt(i) }.eachCount()
    return if (gammaBinMap['0']!! > gammaBinMap['1']!!) {
        '0'
    } else {
        '1'
    }
}

fun getEpsilonBits(gamma: String) : String {
    var epsilon = ""
    for (char in gamma) {
        when (char) {
            '0' -> {
                epsilon += '1'
            }
            '1' -> {
                epsilon += '0'
            }
        }
    }
    return epsilon
}

fun findOxygenRating(binary: List<String>) : Int {
    var i = 0
    var candidates = binary.toMutableList()
    while (candidates.size > 1) {
        val binaryMap = candidates.groupingBy { it.elementAt(i) }.eachCount()
        if ((binaryMap['0']!! < binaryMap['1']!!) || (binaryMap['0']!! == binaryMap['1']!!)) {
            candidates = candidates.filter { it.elementAt(i) == '1' } as MutableList<String>
            i++
        } else if (binaryMap['0']!! > binaryMap['1']!!) {
            candidates = candidates.filter { it.elementAt(i) == '0'} as MutableList<String>
            i++
        }
    }
    val oxygenRating = convertBinaryToDecimal(candidates[0].toLong())
    return oxygenRating
}

fun findCarbonRating(binary: List<String>) : Int {
    var i = 0
    var candidates = binary.toMutableList()
    while (candidates.size > 1) {
        val binaryMap = candidates.groupingBy { it.elementAt(i) }.eachCount()
        if ((binaryMap['0']!! > binaryMap['1']!!)) {
            candidates = candidates.filter { it.elementAt(i) == '1' } as MutableList<String>
            i++
        } else if (binaryMap['0']!! < binaryMap['1']!! || (binaryMap['0']!! == binaryMap['1']!!)) {
            candidates = candidates.filter { it.elementAt(i) == '0'} as MutableList<String>
            i++
        }
    }
    val carbonRating = convertBinaryToDecimal(candidates[0].toLong())
    return carbonRating
}

fun main() {
    val binaryData = File("src/main/kotlin/day3/day3.txt").readLines()
    val answer1 = solveConsumption(binaryData)
    val answer2 = (findOxygenRating(binaryData) * findCarbonRating(binaryData))
}