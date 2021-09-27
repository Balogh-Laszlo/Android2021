package fundamentals

interface IDictionary {
    fun add(String):Boolean
    fun find(String):Boolean
    fun size():Int
    private companion object{
        val name = "IDictionaryInterface"
    }
}