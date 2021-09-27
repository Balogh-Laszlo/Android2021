package lab2_package

import java.io.File

object ListDictionary : IDictionary {
    private val words : MutableList<String> = mutableListOf()

    init {
        File("C:\\Users\\lacob\\AndroidStudioProjects\\Android2021\\KotlinBasics\\lab2\\src\\lab2_package\\in.txt").forEachLine { words.add(it) }
    }
    override fun add(word: String): Boolean {
        if(words.add(word)){
            return true
        }
        return false

    }

    override fun find(word: String): Boolean = words.contains(word)

    override fun size(): Int = words.size
}