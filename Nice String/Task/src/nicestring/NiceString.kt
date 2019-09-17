package nicestring

fun String.isNice(): Boolean {
    val vowelsSet: Set<Char> = setOf('a', 'e', 'i', 'o', 'u')
    val badSubStringPair: Set<Pair<Char, Char>> =
            setOf(Pair('b', 'u'), Pair('b', 'a'), Pair('b', 'e'))
    var matchedConditionNum = 3
    if (this.toCharArray().count() { x -> vowelsSet.contains(x) } < 3) matchedConditionNum--
    if (this.toList().zipWithNext().any() {  badSubStringPair.contains(it)}) matchedConditionNum--
    if (!this.toList().zipWithNext().any() { it.first == it.second}) matchedConditionNum--
    return matchedConditionNum>=2
}