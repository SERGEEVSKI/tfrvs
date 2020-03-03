object Laba2 {
    fun run() {
        inputData(3) {
            val result = when (it) {
                1 -> {
                    println("\n2. Функция  R*(t) оперативной надежности: N = 10; n ∈{8, 9, 10}; λ = 0,024 1/ч; µ = 0,71 1/ч; m = 1; t = 0, 2, 4, …, 24 ч.\n")
                    result1()
                }
                2 -> {
                    println("\n3. Функция U*(t) оперативной восстановимости: N = 16; n ∈{10, 11, …, 16}; λ = 0,024 1/ч; µ = 0,71 1/ч; m = 1; t = 0, 2, 4, …, 24 ч.\n")
                    result2()
                }
                3 -> {
                    println("\nКоэффициент S готовности: N = 16; λ = 0,024 1/ч; µ = 0,71 1/ч.\n")
                    result3()
                }
                else -> throw IllegalStateException("Нет такой операции")
            }
            println(result.show() + "\n\n")
        }
    }

    private fun result1() = (8..10).map { n ->
        (0..24 step 2).map {  t ->
            StructuralRedundancy.OperationalReliability
                .calculate(N = 10, λ =  0.024, m = 1, n = n, µ = 0.71, t = t)
        }
    }

    private fun result2() = (10..16).map { n ->
        (0..24 step 2).map {  t ->
            StructuralRedundancy.operationalRecovery(N = 16, λ =  0.024, m = 1, n = n, µ = 0.71, t = t)
        }
    }

    private fun result3() = listOf(1, 16).map { m ->
        (11..16).map { n ->
            StructuralRedundancy.availabilityFactor(N = 16, λ =  0.024, m = m, n = n, µ = 0.71)
        }
    }
}