@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson5.task1

// Урок 5: ассоциативные массивы и множества
// Максимальное количество баллов = 14
// Рекомендуемое количество баллов = 9
// Вместе с предыдущими уроками = 33/47

/**
 * Пример
 *
 * Для заданного списка покупок `shoppingList` посчитать его общую стоимость
 * на основе цен из `costs`. В случае неизвестной цены считать, что товар
 * игнорируется.
 */
fun shoppingListCost(
    shoppingList: List<String>,
    costs: Map<String, Double>
): Double {
    var totalCost = 0.0

    for (item in shoppingList) {
        val itemCost = costs[item]
        if (itemCost != null) {
            totalCost += itemCost
        }
    }

    return totalCost
}

/**
 * Пример
 *
 * Для набора "имя"-"номер телефона" `phoneBook` оставить только такие пары,
 * для которых телефон начинается с заданного кода страны `countryCode`
 */
fun filterByCountryCode(
    phoneBook: MutableMap<String, String>,
    countryCode: String
) {
    val namesToRemove = mutableListOf<String>()

    for ((name, phone) in phoneBook) {
        if (!phone.startsWith(countryCode)) {
            namesToRemove.add(name)
        }
    }

    for (name in namesToRemove) {
        phoneBook.remove(name)
    }
}

/**
 * Пример
 *
 * Для заданного текста `text` убрать заданные слова-паразиты `fillerWords`
 * и вернуть отфильтрованный текст
 */
fun removeFillerWords(
    text: List<String>,
    vararg fillerWords: String
): List<String> {
    val fillerWordSet = setOf(*fillerWords)

    val res = mutableListOf<String>()
    for (word in text) {
        if (word !in fillerWordSet) {
            res += word
        }
    }
    return res
}

/**
 * Пример
 *
 * Для заданного текста `text` построить множество встречающихся в нем слов
 */
fun buildWordSet(text: List<String>): MutableSet<String> {
    val res = mutableSetOf<String>()
    for (word in text) res.add(word)
    return res
}


/**
 * Простая (2 балла)
 *
 * По заданному ассоциативному массиву "студент"-"оценка за экзамен" построить
 * обратный массив "оценка за экзамен"-"список студентов с этой оценкой".
 *
 * Например:
 *   buildGrades(mapOf("Марат" to 3, "Семён" to 5, "Михаил" to 5))
 *     -> mapOf(5 to listOf("Семён", "Михаил"), 3 to listOf("Марат"))
 */
fun buildGrades(grades: Map<String, Int>): Map<Int, List<String>> {
    val res = mutableMapOf<Int, List<String>>()
    for (name: String in grades.keys) {
        val grade: Int? = grades[name]
        val rec: MutableList<String> = (res[grade] ?: mutableListOf()) as MutableList<String>
        rec.add(name)
        if (grade != null) {
            res[grade] = rec
        }
    }
    return res
}

/**
 * Простая (2 балла)
 *
 * Определить, входит ли ассоциативный массив a в ассоциативный массив b;
 * это выполняется, если все ключи из a содержатся в b с такими же значениями.
 *
 * Например:
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "z", "b" to "sweet")) -> true
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "zee", "b" to "sweet")) -> false
 */
fun containsIn(a: Map<String, String>, b: Map<String, String>): Boolean {
    for (key: String in a.keys) {
        val ax: String? = a[key]
        val bx: String? = b[key]
        if (bx == null)
            return false
        else if (ax != bx)
            return false
    }
    return true
}

/**
 * Простая (2 балла)
 *
 * Удалить из изменяемого ассоциативного массива все записи,
 * которые встречаются в заданном ассоциативном массиве.
 * Записи считать одинаковыми, если и ключи, и значения совпадают.
 *
 * ВАЖНО: необходимо изменить переданный в качестве аргумента
 *        изменяемый ассоциативный массив
 *
 * Например:
 *   subtractOf(a = mutableMapOf("a" to "z"), mapOf("a" to "z"))
 *     -> a changes to mutableMapOf() aka becomes empty
 */
fun subtractOf(a: MutableMap<String, String>, b: Map<String, String>) {
    for (key: String in b.keys) {
        val bx: String? = b[key]
        val ax: String? = a[key]
        if (ax == bx)
            a.remove(key)
    }
    return
}

/**
 * Простая (2 балла)
 *
 * Для двух списков людей найти людей, встречающихся в обоих списках.
 * В выходном списке не должно быть повторяющихся элементов,
 * т. е. whoAreInBoth(listOf("Марат", "Семён, "Марат"), listOf("Марат", "Марат")) == listOf("Марат")
 */
fun whoAreInBoth(a: List<String>, b: List<String>): List<String> {
    val res = mutableListOf<String>()
    for (key: String in a) {
        if (key in b && key !in res) res.add(key)
    }
    return res
}

/**
 * Средняя (3 балла)
 *
 * Объединить два ассоциативных массива `mapA` и `mapB` с парами
 * "имя"-"номер телефона" в итоговый ассоциативный массив, склеивая
 * значения для повторяющихся ключей через запятую.
 * В случае повторяющихся *ключей* значение из mapA должно быть
 * перед значением из mapB.
 *
 * Повторяющиеся *значения* следует добавлять только один раз.
 *
 * Например:
 *   mergePhoneBooks(
 *     mapOf("Emergency" to "112", "Police" to "02"),
 *     mapOf("Emergency" to "911", "Police" to "02")
 *   ) -> mapOf("Emergency" to "112, 911", "Police" to "02")
 */
fun mergePhoneBooks(mapA: Map<String, String>, mapB: Map<String, String>): Map<String, String> {
    val res = mutableMapOf<String, String>()
    for (key: String in mapA.keys) {
        val ax = mapA[key]
        res[key] = ax as String
    }
    for (key in mapB.keys) {
        val bx = mapB[key]
        var rx = res[key]
        if (rx == null) {
            res[key] = bx as String
        } else if (rx != bx) {
            rx += ", $bx"
            res[key] = rx
        }
    }

    return res
}

/**
 * Средняя (4 балла)
 *
 * Для заданного списка пар "акция"-"стоимость" вернуть ассоциативный массив,
 * содержащий для каждой акции ее усредненную стоимость.
 *
 * Например:
 *   averageStockPrice(listOf("MSFT" to 100.0, "MSFT" to 200.0, "NFLX" to 40.0))
 *     -> mapOf("MSFT" to 150.0, "NFLX" to 40.0)
 */
fun averageStockPrice(stockPrices: List<Pair<String, Double>>): Map<String, Double> {
    val resSum = mutableMapOf<String, Double>()
    val resCount = mutableMapOf<String, Int>()
    val res = mutableMapOf<String, Double>()
    for ((s, d) in stockPrices) {
        resCount[s] = (resCount[s] ?: 0) + 1
        resSum[s] = (resSum[s] ?: 0.0) + d
        res[s] = 0.0
    }
    for (s in res.keys) {
        res[s] = resSum[s]!! / resCount[s]!!
    }
    return res
}

/**
 * Средняя (4 балла)
 *
 * Входными данными является ассоциативный массив
 * "название товара"-"пара (тип товара, цена товара)"
 * и тип интересующего нас товара.
 * Необходимо вернуть название товара заданного типа с минимальной стоимостью
 * или null в случае, если товаров такого типа нет.
 *
 * Например:
 *   findCheapestStuff(
 *     mapOf("Мария" to ("печенье" to 20.0), "Орео" to ("печенье" to 100.0)),
 *     "печенье"
 *   ) -> "Мария"
 */
fun findCheapestStuff(stuff: Map<String, Pair<String, Double>>, kind: String): String? {
    val res = mutableMapOf<String, Pair<String, Double>>()
    for (s in stuff.keys) {
        val p = stuff[s]
        if (p != null) {
            val k = p.first
            val d = p.second
            val rx = res[k]
            if (rx == null || rx.second > d) {
                res[k] = Pair(s, d)
            }
        }
    }
    val rs = res[kind] ?: return null
    return rs.first
}

/**
 * Средняя (3 балла)
 *
 * Для заданного набора символов определить, можно ли составить из него
 * указанное слово (регистр символов игнорируется)
 *
 * Например:
 *   canBuildFrom(listOf('a', 'b', 'o'), "baobab") -> true
 */
fun canBuildFrom(chars: List<Char>, word: String): Boolean {
    for (c in word) {
        if (c !in chars)
            return false
    }
    return true
}

/**
 * Средняя (4 балла)
 *
 * Найти в заданном списке повторяющиеся элементы и вернуть
 * ассоциативный массив с информацией о числе повторений
 * для каждого повторяющегося элемента.
 * Если элемент встречается только один раз, включать его в результат
 * не следует.
 *
 * Например:
 *   extractRepeats(listOf("a", "b", "a")) -> mapOf("a" to 2)
 */
fun extractRepeats(list: List<String>): Map<String, Int> {
    val res = mutableMapOf<String, Int>()
    for (s in list) {
        val predicate: (String) -> Boolean = { it == s }
        val n = list.count(predicate)
        if (n > 1) res[s] = n
    }
    return res
}

/**
 * Средняя (3 балла)
 *
 * Для заданного списка слов определить, содержит ли он анаграммы.
 * Два слова здесь считаются анаграммами, если они имеют одинаковую длину
 * и одно можно составить из второго перестановкой его букв.
 * Скажем, тор и рот или роза и азор это анаграммы,
 * а поле и полено -- нет.
 *
 * Например:
 *   hasAnagrams(listOf("тор", "свет", "рот")) -> true
 */
fun hasAnagrams(words: List<String>): Boolean {
    fun myCountCS(c: Char, s: String): Int {
        var n = 0
        for (x in s) {
            if (x == c) n += 1
        }
        return n
    }

    fun myAinB(a: String, b: String): Boolean {
        for (c in a) {
            val an = myCountCS(c, a)
            val bn = myCountCS(c, b)
            if (an != bn) return false
        }
        return true
    }

    fun myAB(a: String, b: String): Boolean = myAinB(a, b) && myAinB(b, a)
    for (a in words)
        for (b in words) {
            if (a != b && myAB(a, b)) {
                return true
            }
        }
    return false


}

/**
 * Сложная (5 баллов)
 *
 * Для заданного ассоциативного массива знакомых через одно рукопожатие `friends`
 * необходимо построить его максимальное расширение по рукопожатиям, то есть,
 * для каждого человека найти всех людей, с которыми он знаком через любое
 * количество рукопожатий.
 *
 * Считать, что все имена людей являются уникальными, а также что рукопожатия
 * являются направленными, то есть, если Марат знает Свету, то это не означает,
 * что Света знает Марата.
 *
 * Оставлять пустой список знакомых для людей, которые их не имеют (см. EvilGnome ниже),
 * в том числе для случая, когда данного человека нет в ключах, но он есть в значениях
 * (см. GoodGnome ниже).
 *
 * Например:
 *   propagateHandshakes(
 *     mapOf(
 *       "Marat" to setOf("Mikhail", "Sveta"),
 *       "Sveta" to setOf("Marat"),
 *       "Mikhail" to setOf("Sveta"),
 *       "Friend" to setOf("GoodGnome"),
 *       "EvilGnome" to setOf()
 *     )
 *   ) -> mapOf(
 *          "Marat" to setOf("Mikhail", "Sveta"),
 *          "Sveta" to setOf("Marat", "Mikhail"),
 *          "Mikhail" to setOf("Sveta", "Marat"),
 *          "Friend" to setOf("GoodGnome"),
 *          "EvilGnome" to setOf(),
 *          "GoodGnome" to setOf()
 *        )
 */
fun propagateHandshakes(friends: Map<String, Set<String>>): Map<String, Set<String>> {
    var sortKeys: List<String> = friends.keys.sorted()
    var nonKeys: List<String> = mutableListOf()
    val friendsAhead = mutableMapOf<String, Set<String>>()

    for (s in sortKeys) friendsAhead[s] = mutableSetOf()
    for (s in sortKeys) {
        val fs = friends[s]
        if (fs != null) {
            friendsAhead[s] = mutableSetOf()
            for (s2: String in fs) {
                friendsAhead[s] = friendsAhead[s]!!.plus(s2)
                if (s2 !in sortKeys) {
                    nonKeys = nonKeys.plus(s2)
                }
            }
        }

    }
    for (t in nonKeys) {
        friendsAhead[t] = mutableSetOf()
    }
    sortKeys = sortKeys.plus(nonKeys)
    val n: Int = sortKeys.size
    for (i in 1..n) {
        for (x in sortKeys) {
            val fax = friendsAhead[x]
            if (fax != null)
                for (y in fax) {
                    if (x != y) {
                        var vx = friendsAhead[x]
                        val vy = friendsAhead[y]
                        if (vx != null && vy != null) {
                            vx = vx.plus(vy)
                            friendsAhead[x] = vx
                        }
                    }
                }

        }
    }
    for (x in friendsAhead.keys) {
        friendsAhead[x] = friendsAhead[x]!!.minus(x)
    }
    return friendsAhead

}

/**
 * Сложная (6 баллов)
 *
 * Для заданного списка неотрицательных чисел и числа определить,
 * есть ли в списке пара чисел таких, что их сумма равна заданному числу.
 * Если да, верните их индексы в виде Pair<Int, Int>;
 * если нет, верните пару Pair(-1, -1).
 *
 * Индексы в результате должны следовать в порядке (меньший, больший).
 *
 * Постарайтесь сделать ваше решение как можно более эффективным,
 * используя то, что вы узнали в данном уроке.
 *
 * Например:
 *   findSumOfTwo(listOf(1, 2, 3), 4) -> Pair(0, 2)
 *   findSumOfTwo(listOf(1, 2, 3), 6) -> Pair(-1, -1)
 */
fun findSumOfTwo(list: List<Int>, number: Int): Pair<Int, Int> {
    val n = list.size
    for (i in 0..n - 2)
        for (j in i + 1 until n) {
            if (list[i] + list[j] == number)
                return Pair(i, j)
        }
    return Pair(-1, -1)
}

/**
 * Очень сложная (8 баллов)
 *
 * Входными данными является ассоциативный массив
 * "название сокровища"-"пара (вес сокровища, цена сокровища)"
 * и вместимость вашего рюкзака.
 * Необходимо вернуть множество сокровищ с максимальной суммарной стоимостью,
 * которые вы можете унести в рюкзаке.
 *
 * Перед решением этой задачи лучше прочитать статью Википедии "Динамическое программирование".
 *
 * Например:
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     850
 *   ) -> setOf("Кубок")
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     450
 *   ) -> emptySet()
 */
fun bagPacking(treasures: Map<String, Pair<Int, Int>>, capacity: Int): Set<String> {
    fun getKs(k: Int): String = treasures.keys.toList()[k - 1]
    println(treasures)
    val n = treasures.keys.size
    val a = Array(n + 1) { IntArray(capacity + 1) }
    for (i in 0..capacity) {
        a[0][i] = 0
    }
    for (i in 0..n) {
        a[i][0] = 0
    }                                               //Первые элементы приравниваем к 0
    for (k in 1..n) {
        for (s in 1..capacity) {
            if (s >= treasures[getKs(k)]!!.first)  //Если текущий предмет вмещается в рюкзак
                a[k][s] = maxOf(
                    a[k - 1][s],
                    a[k - 1][s - treasures[getKs(k)]!!.first] + treasures[getKs(k)]!!.second
                )                                   //Выбираем класть его или нет
            else
                a[k][s] = a[k - 1][s]               //Иначе, не кладем

        }
    }
    var ans = mutableSetOf<String>()
    var xs: String
    fun findAns(k: Int, s: Int) {
        if (k <= 0 || a[k][s] == 0)
            return
        if (a[k][s] == 0)
            return
        xs = getKs(k)
        if (a[k - 1][s] == a[k][s]) {
            findAns(k - 1, s)
        } else {
            ans = ans.plus(xs) as MutableSet<String>
            findAns(k - 1, s - treasures[getKs(k)]!!.first)
        }
    }

    findAns(n, capacity)
    return ans

}