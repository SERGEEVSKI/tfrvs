import StructuralRedundancy.OperationalReliability.pj
import StructuralRedundancy.OperationalReliability.pj1
import StructuralRedundancy.OperationalReliability.ul
import kotlin.math.exp
import kotlin.math.pow

/**
 * Распределенные вычислительные системы со структурной избыточностью
 */
object StructuralRedundancy {

    /** Функция Θ(n) среднего времени наработки до отказа */
    fun averageOperatingTime(N: Int, λ: Double, m: Int, n: Int, µ: Int) =
        sum(n + 1, N) { j -> (1 / (j * λ)) *
            pow(n, j - 1) { l -> ml(µ, N, m, l) / (l * λ) }
        } + (1 / (n * λ))



    /** Функция T(n) среднего времени восстановления */
    fun averageRecoveryTime(N: Int, λ: Double, m: Int, n: Int, µ: Int) = when {
        n == 1 -> 1.0 / ml(µ, N, m, 0)
        n > 1 -> (1.0 / ml(µ, N, m, 0)) *
                pow(1, n-1) { l -> (l * λ) / (µ * l) } +
                sum(1, n-1) { j -> (1 / (j * λ)) *
                        pow(j, n-1) { l -> (l * λ) / ml(µ, N, m, l) }
        }
        else -> throw IllegalStateException("n должен быть больше 0!")
    }

    private fun ml(µ: Int, N: Int, m: Int, l: Int) = when {
        ((N - m) <= l && l <= N) -> (N - l) * µ
        (0 <= l && l <= (N - m)) -> m * µ
        else -> throw IllegalStateException("Невозможное условие")
    }


    /** Функция R*(t) оперативной надежности */
    object OperationalReliability {

        fun calculate(N: Int, λ: Double, m: Int, n: Int, µ: Double, t: Int) =
            sum(n, N) { i -> pj(N, λ, µ, i) * qi(N, λ, m, n, µ, t, i) }

        fun pj(N: Int, λ: Double, µ: Double, j: Int) =
            pj1(λ, µ, j) * sum(0, N) { l ->  pj1(λ, µ, l) }.pow(-1)

        fun pj1(λ: Double, µ: Double, i: Int) = delToFact((µ / λ).pow(i), i)

        private fun qi(N: Int, λ: Double, m: Int, n: Int, µ: Double, t: Int, i: Int) =
            sum(0, N) { l -> ul(N, m, µ, t, i, l) * sum(0, i - n + l) { r -> pi(i,  λ, t, r) } }

        private fun pi(i: Int, λ: Double, t: Int, r: Int) =
            delToFact((i * λ * t).pow(r), r) * exp(-i * λ * t)

        fun ul(N: Int, m: Int, µ: Double, t: Int, i: Int, l: Int) =
            delToFact((µ * t).pow(l), l) * (delta(N - i - m) * m.pow(l) * exp(-m * µ * t) +
                delta(m - N + i) * (N - i).pow(l) * exp(-(N - i) * µ * t))

        private fun delta(x: Int) = if (x < 0) 0 else 1
    }

    /** Функция U*(t) оперативной восстановимости */
    fun operationalRecovery(N: Int, λ: Double, m: Int, n: Int, µ: Double, t: Int): Double =
        1.0 - (sum(0, n - 1) { i ->
            pj(N, λ, µ, i) * sum(0, n - 1 - i) { l -> ul(N, m, µ, t, i, l) } })

    /** Коэффициент S готовности */
    fun availabilityFactor(N: Int, λ: Double, m: Int, n: Int, µ: Double) = when (m) {
        1 ->  1.0 - sum(0, n - 1) { i -> pj(N, λ, µ, i)* sum(0, n - 1 - i) { l -> ul(N, m, µ, 0, i, l) } }
        N -> 1.0 - (λ.pow(N - n  + 1) * (λ + µ).pow(-(N - n + 1)))
        else -> throw IllegalStateException("Невозможное условие")
    }

    fun pjm(m: Int, λ: Double, µ: Double, j: Int) = delToFact(((m * µ) / λ).pow(j) * exp((-µ * m) / λ), j)
}
