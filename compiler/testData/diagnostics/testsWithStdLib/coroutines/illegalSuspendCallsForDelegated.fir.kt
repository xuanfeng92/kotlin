fun bar(d: Delegate): String {
    val x: String by d
    return x
}

class Delegate {
    suspend operator fun getValue(thisRef: Any?, property: Any?): String = ""
}
