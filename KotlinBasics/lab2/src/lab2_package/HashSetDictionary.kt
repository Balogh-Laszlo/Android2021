package lab2_package

import java.io.File

object HashSetDictionary : IDictionary {
    val words= hashSetOf<String>()
    init {
        File(IDictionary.path).forEachLine { this.add(it) }
    }
    override fun add(word: String): Boolean = words.add(word)

    override fun find(word: String): Boolean = words.contains(word)

    override fun size(): Int = words.size

}
