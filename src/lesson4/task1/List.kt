@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import kotlin.math.sqrt

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.lowercase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var s = 0.0
    for (x in v) {
        s += x * x

    }

    return sqrt(s)
}


/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    if (list.isEmpty())
        return 0.0
    var s = 0.0
    for (x in list) {
        s += x
    }
    return s / list.size
}

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val m = mean(list)
    for (i in list.indices) {
        list[i] -= m
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var s = 0
    for (i in a.indices) {
        s += a[i] * b[i]

    }
    return s
}

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var s: Int
    val n = p.size
    if (n == 0)
        return 0
    s = p[n - 1]
    for (i in n - 2 downTo 0) {
        s *= x
        s += p[i]
    }
    return s
}

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    var s = 0
    var x: Int
    for (i in list.indices) {
        x = list[i]
        list[i] += s
        s += x
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val l: MutableList<Int> = mutableListOf()
    fun f(n: Int): Int {
        if (n == 1)
            return 0
        for (i in 2..n) {
            if (n % i == 0) {
                l.add(i)
                return f(n / i)
            }
        }
        return 0
    }
    f(n)
    return l


}

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String {
    val l = factorize(n)
    var s: String = l[0].toString()
    for (i in 1 until l.size) {
        s += '*' + l[i].toString()
    }
    return s
}

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val b = mutableListOf<Int>()
    var n1 = n
    while (n1 > 0) {
        b.add(n1 % base)
        n1 /= base
    }
    return if (n == 0) return listOf(0) else b.reversed()
}

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    val list = convert(n, base)
    val abc = "abcdefghijklmnopqrstuvwxyz"
    var str = ""
    for (i in list.indices)
        str += if (list[i] < 10) list[i] else
            abc[list[i] - 10]
    return str
}


/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    val n = digits.size
    if (n == 0)
        return 0

    var s: Int = digits[0]
    for (i in 1 until n) {
        s *= base
        s += digits[i]
    }
    return s
}

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    fun c2num(c: Char): Int {
        val a = 'a'

        return c.code - a.code + 10
    }

    fun cd2num(c: Char): Int {
        val c0 = '0'

        return c.code - c0.code
    }

    fun d2num(c: Char): Int {
        if (c.isDigit())
            return cd2num(c)
        return c2num(c)
    }
    //fun decimal(digits: List<Int>, base: Int): Int {
    val l: MutableList<Int> = mutableListOf(d2num(str[0]))
    for (i: Int in 1 until str.length) {
        l.add(d2num(str[i]))

    }
    //fun decimal(digits: List<Int>, base: Int): Int {
    return decimal(l, base)
}

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n1: Int): String {
    var s = ""
    fun rom(n: Int): Int {
        if (n >= 1000) {
            s += "M"
            return rom(n - 1000)
        } else if (n >= 900) {
            s += "CM"
            return rom(n - 900)
        } else if (n >= 500) {
            s += "D"
            return rom(n - 500)
        } else if (n >= 400) {
            s += "CD"
            return rom(n - 400)
        } else if (n >= 100) {
            s += "C"
            return rom(n - 100)
        } else if (n >= 90) {
            s += "XC"
            return rom(n - 90)
        } else if (n >= 50) {
            s += "L"
            return rom(n - 50)
        } else if (n >= 40) {
            s += "XL"
            return rom(n - 40)
        } else if (n >= 10) {
            s += "X"
            return rom(n - 10)
        } else if (n >= 9) {
            s += "IX"
            return rom(n - 9)
        } else if (n >= 5) {
            s += "V"
            return rom(n - 5)
        } else if (n >= 4) {
            s += "IV"
            return rom(n - 4)
        } else if (n >= 1) {
            s += "I"
            return rom(n - 1)
        }

        return 0
    }
    rom(n1)
    return s

}

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val result = mutableListOf<String>()
    val part1 = n / 1000
    if (part1 > 0) {
        result.addAll(russianMiddle(part1, units))
        if ((part1 % 10 == 1) && (part1 / 10 % 10 != 1)) result.add("тысяча")
        else if ((((part1 % 100) / 10) != 1) && (((part1 % 10) == 2) || ((part1 % 10) == 3) || ((part1 % 10) == 4)))
            result.add("тысячи")
        else result.add("тысяч")
    }
    val part2 = n % 1000
    result.addAll(russianMiddle(part2, units2))
    return result.joinToString(separator = " ")
}

val hundreds = listOf(
    "сто",
    "двести",
    "триста",
    "четыреста",
    "пятьсот",
    "шестьсот",
    "семьсот",
    "восемьсот",
    "девятьсот"
)
val tens = listOf(
    "двадцать",
    "тридцать",
    "сорок",
    "пятьдесят",
    "шестьдесят",
    "семьдесят",
    "восемьдесят",
    "девяносто"
)
val tens2 = listOf(
    "десять",
    "одиннадцать",
    "двенадцать",
    "тринадцать",
    "четырнадцать",
    "пятнадцать",
    "шестнадцать",
    "семнадцать",
    "восемнадцать",
    "девятнадцать"
)
val units = listOf("одна", "две", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять")
val units2 = listOf("один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять")

fun russianMiddle(middle: Int, unit: List<String>): List<String> {
    val result = mutableListOf<String>()
    if (middle / 100 >= 1) {
        val hd = middle / 100
        result.add(hundreds[hd - 1])
    }
    if (middle % 100 / 10 == 1) {
        val tn1 = middle % 10
        result.add(tens2[tn1])
    } else if (middle % 100 / 10 == 0) {
        val un = middle % 10
        if (un >= 1) result.add(unit[un - 1])
    } else {
        val tn = middle % 100 / 10
        result.add(tens[tn - 2])
        val un = middle % 10
        if (un >= 1) result.add(unit[un - 1])
    }
    return result
}