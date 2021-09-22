package fundamentals


import java.util.*
import kotlin.random.Random

fun main(){
    // 1.
    val a = 2
    val b = 3
    println("$a+$b=${a+b}")
   // 2.

    val daysOfWeek: List<String> = listOf("monday", "tuesday", "wednesday","thursday","friday","saturday","sunday")
    for(day in daysOfWeek){
        print("$day ")
    }
    println()
//    daysOfWeek.forEach{
//        day -> print(day)
//    }

    val filteredDays = daysOfWeek.filter{ it[0] == 'T'}
    for(day in filteredDays){
        print("$day ")
    }
    println()
    val filteredDays2 = daysOfWeek.filter { it.contains('e') }
    for(day in filteredDays2){
        print("$day ")
    }
    println()

    daysOfWeek.filter { it.length==6 }.forEach { print("$it ") }
    println()
//    3.
    println(isPrime(244))

    val primes : MutableList<Int> = primesInRange(100)
    primes.forEach{
        print("$it ")
    }
    println()
//4.
    val temp = "Random string"
    val encodedString = encode(temp)
    println(encodedString)
    println(decode(encodedString))
    val msg = messageCoding("Hello",::encode)
    println(msg)
    println(messageCoding(msg,::decode))

    //6.
    val numbers = listOf(2.3,4.12,3.14,3.15,6.27)
    numbers.map { it*2 }.forEach { print("$it ") }
    println()

    daysOfWeek.map { it.toUpperCase() }.forEach { print("$it ") }
    println()

    daysOfWeek.map { it.capitalize() }.forEach { print(("$it ")) }
    println()

    daysOfWeek.map { it.length }.forEach { print("$it ") }
    println()

    println(daysOfWeek.map { it.length }.average())
    //7.
    val daysOfWeekMutable :MutableList<String> = daysOfWeek.toMutableList()
    daysOfWeekMutable.replaceAll { it.capitalize() }

    daysOfWeekMutable.removeIf { it.contains('n') }
    daysOfWeekMutable.forEach { print("$it ") }
    println()

    for((index,value) in daysOfWeekMutable.withIndex()){
        println("Item at $index is $value")
    }
    daysOfWeekMutable.sort()
    daysOfWeekMutable.forEach { print("$it ") }
    println()

    //8.
    val arr = Array(10){ Random.nextInt(0,100)}
    arr.forEach { println(it) }
    println("************")
    arr.sort()
    arr.forEach { println(it) }
    println(arr.any { it%2==0 })
    println(arr.all { it%2==0 })
    var sum:Double = 0.0
    arr.forEach { sum+=it }
    println("The avarage of the generated array: ${sum/arr.size}")
//    println(arr.average())

}
//3.
fun isPrime(num:Int):Boolean{
    if(num == 0 || num == 1){
        return false
    }
    if (num == 2 || num == 3){
        return true
    }
    if(num%2 == 0){
        return false
    }
    for(i in 5..num/2 step 2){
        if(num%i == 0) {
            return false
        }
    }
    return true
}

fun primesInRange(range:Int):MutableList<Int>{
    val primes:MutableList<Int> = mutableListOf()
    for (i in 1..range){
        if(isPrime(i)){
            primes.add(i)
        }
    }
    return primes
}
//4.
fun messageCoding(msg: String, func: (String) -> String): String{
 return func(msg)
}
fun encode(msg:String):String{
    return Base64.getEncoder().encodeToString(msg.toByteArray())
}

fun decode(msg:String):String{
    return String(Base64.getDecoder().decode(msg))
}
// 5.
fun printEven(list:List<Int>):Unit = list.filter { it%2 == 0 }.forEach{ print("$it ") }
