package fundamentals

import sun.security.ec.point.ProjectivePoint
import java.util.*

fun main(){
    val a = 2
    val b = 3
    println("$a+$b=${a+b}")
   // Feladat 2.

    val daysOfWeek: List<String> = listOf("Monday", "Tuesday", "Wednesday","Thursday","Friday","Saturday","Sunday")
    for(day in daysOfWeek){
        print("$day ")
    }
    println()
//    daysOfWeek.forEach{
//        day -> print(day)
//    }

    val filteredDays = daysOfWeek.filter{ it[0].equals('T')}
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
    println(isPrime(244))

    val primes : MutableList<Int> = primesInRange(100)
    primes.forEach{
        print("$it ")
    }

//    messageCoding("Hello",::encode)
}

fun isPrime(num:Int):Boolean{
    if(num == 0 || num == 1){
        return false
    }
    if(num == 2){
        return true
    }
    if(num == 3){
        return true
    }
    if(num%2 == 0){
        return false
    }
    var i = 3
    while (i<num/2){
        if(num%i == 0){
            return false
        }
        i+=2
    }
    return true
}

fun primesInRange(range:Int):MutableList<Int>{
    var primes:MutableList<Int> = mutableListOf()
    for (i in 1..range){
        if(isPrime(i)){
            primes.add(i)
        }
    }
    return primes
}
//fun messageCoding(msg: String, func: (String) -> String): String{
//
//}
fun encode(msg:String):String{
    return Base64.getEncoder().encodeToString(msg.toByteArray())
}
