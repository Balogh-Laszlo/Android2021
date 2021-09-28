package lab2_package

import java.io.File
import java.util.*

object TreeSetDictionary : IDictionary {
    private val words: TreeSet<String> = TreeSet()
    init {
        File("C:\\Users\\lacob\\AndroidStudioProjects\\Android2021\\KotlinBasics\\lab2\\src\\lab2_package\\in.txt").forEachLine { this.add(it) }
    }
    override fun add(word: String): Boolean = words.add(word)

    override fun find(word: String): Boolean = words.contains(word)

    override fun size(): Int = words.size
}