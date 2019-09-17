package nicestring

fun main() {
    println(duplicateNonZero(listOf(3, 0, 5)))
}

fun duplicateNonZero(list: List<Int>): List<Int> {
    return list.flatMap {
        if (it == 0) {
            println("0 and return")
//            return listOf()
            listOf()
//             return@flatMap listOf<Int>()
        } else {
            println("other and return")
            listOf(it, it)
        }
    }
}