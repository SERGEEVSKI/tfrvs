import java.util.*

inline fun inputData(count: Int, inputted: (Int) -> Unit) {
    while (true) {
        try {
            print("Введите номер операции (1-$count): ")
            val number = Scanner(System.`in`).next()
                .toInt()
            if ((1..count).contains(number)) {
                inputted(number)
            } else throw IllegalStateException("Нет такой операции")
        } catch (ex: NumberFormatException) {
            println("Неверный ввод")
        } catch (ex: IllegalStateException) { println(ex.localizedMessage) }
    }
}

fun List<List<Double>>.show() = map { it.toColumn() }
    .reduce { i, j -> "$i\n\n$j" }

private fun List<Double>.toColumn() = map { String.format("%.9f", it).replace('.', ',') }.reduce { i, j -> "$i\n$j" }//map { String.format("%.3f", it).replace('.', ',') }

