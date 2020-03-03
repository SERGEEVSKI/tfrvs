import java.math.BigInteger
import kotlin.math.pow

inline fun pow(i: Int, j: Int, pow: (l: Int) -> Double) = (i..j).map(pow).reduce { h, k -> h * k }
inline fun sum(i: Int, j: Int, sum: (j: Int) -> Double) = (i..j).map(sum).sum()
fun Int.pow(x: Int) = toDouble().pow(x)
fun delToFact(del: Double, fact: Int) = (del.toBigDecimal() / fact(fact).toBigDecimal()).toDouble()

fun fact(num: Int): BigInteger {
    var factorial = BigInteger.ONE
    for (i in 1..num) {
        factorial = factorial.multiply(BigInteger.valueOf(num.toLong()))
    }
    return factorial
}