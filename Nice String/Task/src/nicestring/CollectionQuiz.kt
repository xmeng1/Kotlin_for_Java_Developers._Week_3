package nicestring
import nicestring.Gender.*
import java.lang.Exception

data class Hero(val name: String,val age: Int, val gender: Gender?)

enum class Gender() {
    MALE,FEMALE
}

fun main() {

    val heroes = listOf(
            Hero("The Captain", 60, MALE),
            Hero("Frenchy", 42, MALE),
            Hero("The Kid", 9, null),
            Hero("Lady Lauren", 29, FEMALE),
            Hero("First Mate", 29, MALE),
            Hero("Sir Stephen", 37, MALE))

    println(heroes.last().name)

    println(heroes.firstOrNull() {it.age == 30}?.name)
    try {
        println(heroes.first() {it.age == 30}.name)
    } catch(e: Exception) {
        print("get exception: ${e.message}")
    }

    val (y, o) = heroes.partition { it.age < 30}
    val maxAgePerson = heroes.maxBy { it.age }?.name
    heroes.all { it.age < 50}

    val mapByAge: Map<Int, List<Hero>> = heroes.groupBy { it.age }
    val (age, group) = (mapByAge.maxBy { (_, group) -> group.size })!!
//    val (age, group) = (mapByAge.maxBy { (_, group) -> group?.size })?:

    val mapByName = heroes.associateBy { it.name }
    val age1 = mapByName["Frenchy"]?.age
    val age2 = mapByName.getValue("Frenchy").age

    //bad
    val k = heroes
            .flatMap { heroes.map { hero -> it to hero } }
    val kSame = heroes.map { h ->  heroes.map { hero -> h to hero } }.flatten()
    // bad
    val (first, second) = heroes
            .flatMap { heroes.map { hero -> it to hero } }
            .maxBy { it.first.age - it.second.age }!!
}