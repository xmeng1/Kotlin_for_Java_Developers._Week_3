package nicestring

class Name(val value:String)

fun isFoo1(n: Name) = n.value == "foo"
//fun isFoo2(n: Name?) = n.value == "foo"
fun isFoo3(n: Name?) = n != null && n.value == "foo"
fun isFoo4(n: Name?) = n?.value == "foo"
fun isFoo5(n: Name?) = (n?:Name("")).value == "foo"

fun main1(args: Array<String>) {
//    isFoo1(null)
//    isFoo2(null)
    isFoo3(null)
    isFoo4(null)

    // if name == null, x,y,z same string
    // if name != null, x is string , but y and z is Name,because . is high priority.
    val name = Name("xxx")
//    val name = null
    val x = (name?:Name("yyy")).value
    val y = name?:(Name("yyy").value)
    val z = name?:Name("yyy").value
    println(x)
    println(y)
    println(z)

//    val lam: () -> Int? = null
    val lam2: () -> Int? = {null}
//    val lam3: (() -> Int)? = {null}
    val lam4: (() -> Int)? = null
}

fun String?.isEmptyOrNull(): Boolean {
    return this.isNullOrEmpty()
}

fun main(args: Array<String>) {
    val s1: String? = null
    val s2: String? = ""
    assert(s1.isEmptyOrNull())
    assert(s2.isEmptyOrNull())

    val s3 = "   "
    assert(!s3.isEmptyOrNull())
}

//fun isNoE(s: String?): Boolean = s == null || s.isEmpty()
//==> intellij auto convert  [String? -> String?.]
fun String?.isNoE(): Boolean = this == null || isEmpty()