package lab2_package


import java.time.LocalDate
import kotlin.random.Random


fun main(){

    problem1()
//    problem2()
//    problem3()

}
fun problem1(){
    val dict: IDictionary = DictionaryProvider.createDictionary( DictionaryType.TREE_SET)
    println("Number of words: ${dict.size()}")
    var word: String?
    while(true){
        print("What to find? ")
        word = readLine()
        if( word.equals("quit")){
            break
        }
        println("Result: ${word?.let { dict.find(it) }}")
    }
}
fun problem2(){
    fun String.monogram():String{
        val words= this.split(" ")
        return words.map { it.first() }.joinToString( "")
    }
    fun List<String>.join(separator:Char):String{
        var temp: String = ""
        for(item in this){
            temp += item + separator
        }
        temp = temp.dropLast(1)
        return temp
    }
    fun List<String>.longest():String{
        var longest : String = ""
        var size = 0
        this.forEach { if(it.length>size){
            longest = it
            size = it.length
            }
        }
        return longest
    }
    val temp = "John William Smith"
    println(temp.monogram())
    val list : List<String> = listOf("apple", "pear","strawberry", "melon")
    println(list.join('#'))
    println(list.longest())
}
fun problem3(){
    fun Date.leapYear():Boolean{
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                // year is divisible by 400, hence the year is a leap year
                return  year % 400 == 0
            } else
                return  true
        } else
            return false
    }
    fun Date.isValid():Boolean{
        if(year<1 || year>LocalDate.now().year){
            return false
        }
        if(month <1 || month >12){
            return false
        }
        if(listOf(1,3,5,7,8,10,12).contains(month)){
            if (day >31 || day < 1){
                return false
            }
        }
        if(listOf(4,6,9,11).contains(month)){
            if(day > 30 || day <1){
                return false
            }
        }
        if(month == 2){
            if(this.leapYear()){
                if(day >29 || day <1){
                    return false
                }
            }
            else{
                if(day>28 || day <1){
                    return false
                }
            }
        }
        return true
    }
    val dates : MutableList<Date> = mutableListOf()
    println("Invalid dates:")
    while (dates.size<10){
        val date = Date(Random.nextInt(1,2500),Random.nextInt(-5,15), Random.nextInt(-15,45))
        if(date.isValid()){
            dates.add(date)
        }else{
            println(date.toString())
        }
    }
    println("Valid dates:")
    dates.forEach { println("${it.year}-${it.month}-${it.day}") }
    dates.sort()
    println("Ordered dates:")
    dates.forEach { println("${it.year}-${it.month}-${it.day}") }
    dates.reverse()
    println("Dates in reversed order:")
    dates.forEach { println("${it.year}-${it.month}-${it.day}") }
    println("Dates ordered by day:")
//    dates.sortBy { it.day }
//    dates.forEach { println("${it.year}-${it.month}-${it.day}") }
    val compareByDay = Comparator<Date>{
        d1:Date , d2:Date -> d1.day - d2.day
    }
    dates.sortWith(compareByDay)
    dates.forEach { println("${it.year}-${it.month}-${it.day}") }



}

