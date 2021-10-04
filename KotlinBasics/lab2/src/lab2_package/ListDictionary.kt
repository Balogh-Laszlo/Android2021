package lab2_package

import java.io.File

object ListDictionary : IDictionary {
    val words : MutableList<String> = mutableListOf()

    init {
        File(IDictionary.path).forEachLine { this.add(it) }
    }
    override fun add(word: String): Boolean = words.add(word)

    override fun find(word: String): Boolean = words.contains(word)

    override fun size(): Int = words.size
}