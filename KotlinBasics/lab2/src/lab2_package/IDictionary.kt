package lab2_package

interface IDictionary {
    fun add(word: String):Boolean
    fun find(word: String):Boolean
    fun size():Int
    private companion object{
        val name = "IDictionaryInterface"
    }
}