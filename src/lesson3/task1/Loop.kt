@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.sqrt

// Урок 3: циклы
// Максимальное количество баллов = 9
// Рекомендуемое количество баллов = 7
// Вместе с предыдущими уроками = 16/21

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result *= i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая (2 балла)
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    if (n == 0)
        return 1
    var m = n
    var c = 0
    while (m > 0) {
        c += 1
        m /= 10
    }
    return c
}

/**
 * Простая (2 балла)
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    if (n == 1)
        return 1
    if (n == 2)
        return 1

    return fib(n - 1) + fib(n - 2)
}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    for (i in 2..n) {
        if (n % i == 0) return i
    }
    return -1
}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    for (i in n - 1 downTo 1) {
        if (n % i == 0) return i
    }
    return -1
}

/**
 * Простая (2 балла)
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x1: Int): Int {
    var x = x1
    var c = 0
    while (x > 1) {
        c += 1
        x = if (x % 2 == 0) x / 2
        else 3 * x + 1
    }
    return c
}

/**
 * Средняя (3 балла)
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var k = m.coerceAtLeast(n)
    while (!((k % n == 0) && (k % m == 0))) {
        k += 1
    }
    return k
}

/**
 * Средняя (3 балла)
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    val k = m.coerceAtMost(n)
    for (i in k downTo 2) {
        if ((m % i == 0) && (n % i == 0))
            return false
    }
    return true
}

/**
 * Средняя (3 балла)
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {

    var m = n
    var d: Int
    var c = 0
    var x = 0
    while (m > 0) {
        c += 1
        d = m % 10
        m /= 10
        x *= 10
        x += d
    }
    return x
}

/**
 * Средняя (3 балла)
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    val m = revert(n)
    return m == n
}

/**
 * Средняя (3 балла)
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    val c1 = n % 10
    var n1 = n / 10
    while (n1 > 0) {
        val c = n1 % 10
        if (c != c1)
            return true
        n1 /= 10

    }
    return false
}

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x1: Double, eps: Double): Double {
    val x = x1 % (2 * PI)
    var i = 1
    var a = x
    var s = a

    while (abs(a) > eps) {
        a *= -x * x / (i + 1) / (i + 2)
        s += a
        i += 2
    }
    return s
}

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x1: Double, eps: Double): Double {
    val x = x1 % (2 * PI)
    var i = 0
    var a = 1.0
    var s = a

    while (abs(a) > eps) {
        a *= -x * x / (i + 1) / (i + 2)
        s += a
        i += 2
    }
    return s
}

/**
 * Сложная (4 балла)
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n1: Int): Int {
    fun number(k: Int): Int = k * k
    var n = n1
    fun d2(k: Int): Int = digitNumber(number(k))

    fun cutBrick(n: Int, k: Int = 1): Array<Int> {
        if (n <= 0)
            return arrayOf(n, k)
        return arrayOf(n - d2(k), k + 1)
    }

    var k = 1
    //println("n =$n , k =$k")
    while (n > 0) {
        val a = cutBrick(n, k)
        n = a[0]
        k = a[1]
        //println("n =$n , k =$k")
    }
    var m = n1
    var k2 = number(k - 1)
    var c = k2 % 10
    k2 /= 10
    while (n < 0) {
        n += 1
        c = k2 % 10
        k2 /= 10
    }
    return c
}

/**
 * Сложная (5 баллов)
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */

fun fibSequenceDigit(n1: Int): Int {
    var n = n1
    fun number(k: Int): Int = fib(k)

    fun d2(k: Int): Int = digitNumber(number(k))

    fun cutBrick(n: Int, k: Int = 1): Array<Int> {
        if (n <= 0)
            return arrayOf(n, k)
        return arrayOf(n - d2(k), k + 1)
    }

    var k = 1
    //println("n =$n , k =$k")
    while (n > 0) {
        val a = cutBrick(n, k)
        n = a[0]
        k = a[1]
        //println("n =$n , k =$k")
    }
    var m = n1
    var k2 = number(k - 1)
    var c = k2 % 10
    k2 /= 10
    while (n < 0) {
        n += 1
        c = k2 % 10
        k2 /= 10
    }
    return c
}
