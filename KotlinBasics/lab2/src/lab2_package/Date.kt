package lab2_package

import java.time.LocalDate

data class Date(var year:Int = LocalDate.now().year, var month:Int = LocalDate.now().month.value, var day:Int = LocalDate.now().dayOfMonth) : Comparable<Date>{
    override fun compareTo(other: Date): Int {
        when {
            this.year > other.year -> {
                return 1
            }
            this.year < other.year -> {
                return -1
            }
            else -> {
                return if(this.month > other.month){
                    1
                } else if(this.month < other.month){
                    -1
                } else{
                    if(this.day > other.day){
                        1
                    } else {
                        0
                    }
                }
            }
        }
    }

}