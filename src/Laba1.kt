object Laba1 {
    fun run() {
        inputData(6) {
            val result = when (it) {
                1 -> {
                    println("\n2.1. Θ(n). Параметры: N = 65536; λ = 10-5 ; m = 1; n = 65527, 65528, …, 65536; µ ∈{1, 10, 100, 1000}.\n")
                    result1()
                }
                2 -> {
                    println("\n2.2. Θ(n). Параметры: N = 65536; µ = 1; m = 1; n = 65527, 65528, …, 65536; λ ∈{10-5, 10-6, 10-7, 10-8 , 10-9}.\n")
                    result2()
                }
                3 -> {
                    println("\n2.3. Θ(n). Параметры: N = 65536; µ = 1; λ = 10-5 ; n = 65527, 65528, …, 65536; m ∈{1, 2, 3, 4}.\n")
                    result3()
                }
                4 -> {
                    println("\n3.1. T(n). Параметры: N = 1000; λ = 10-3 ; m = 1; n = 900, 910, …, 1000; µ ∈{1, 2, 4, 6}.\n")
                    result4()
                }
                5 -> {
                    println("\n3.2. T(n). Параметры: N = 8192; µ = 1; m = 1; n = 8092, 8102, …, 8192; λ ∈{10-5, 10-6, 10-7, 10-8 , 10-9}.\n")
                    result5()
                }
                6 -> {
                    println("\n3.3. T(n). Параметры: N = 8192; µ = 1; λ = 10-5 ; n = 8092, 8102, …, 8192; m ∈{1, 2, 3, 4}\n")
                    result6()
                }
                else -> throw IllegalStateException("Нет такой операции")
            }
            println(result.show() + "\n\n")
        }
    }


    private val λList = listOf(0.00001, 0.000001, 0.0000001, 0.00000001, 0.000000001)

    private fun result1() = listOf(1, 10, 100, 1000).map { µ -> Θ(λ = 0.0001, m = 1, µ = µ) }
    private fun result2() = λList.map { λ -> Θ(λ = λ, m = 1, µ = 1) }
    private fun result3() = (1..4).map { m -> Θ(λ = 0.00001, m = m, µ = 1) }
    private fun result4() = listOf(1, 2, 4, 6).map { µ -> (900..1000 step 10).map { n ->
        StructuralRedundancy.averageRecoveryTime(N = 1000, λ = 0.001, m = 1, n = n, µ = µ)
    }}
    private fun result5() = λList.map { λ -> T(λ = λ, m = 1, µ = 1) }
    private fun result6() = (1..4).map { m -> T(λ = 0.00001, m = m, µ = 1) }

    private fun Θ(λ: Double, m: Int, µ: Int) = (65526..65536).map { n ->
        StructuralRedundancy.averageOperatingTime(N = 65536, λ = λ, m = m, n = n, µ = µ)
    }

    private fun T(λ: Double, m: Int, µ: Int) = (8092..8192 step 10).map { n ->
        StructuralRedundancy.averageRecoveryTime(N = 8192, λ = λ, m = m, n = n, µ = µ)
    }
}