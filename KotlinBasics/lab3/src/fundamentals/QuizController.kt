package fundamentals

import java.io.File
import java.lang.IllegalArgumentException

class QuizController {
    companion object{
        const val filename = "questions.txt"
    }
    private val listOfQuestions : MutableList<Question>  = mutableListOf()
    init{
        val lines = File(filename).readLines()
        for(i in 0 until lines.size step 5){
            listOfQuestions.add(Question(lines[i],listOf(lines[i+1],lines[i+2],lines[i+3],lines[i+4])))
        }
    }
    private fun randomizeQuestions(){
        listOfQuestions.shuffle()
    }
    fun doQuiz(numberOfQuestions : Int){
        if(numberOfQuestions>size()){
            throw IllegalArgumentException("Not enough question")
        }
        randomizeQuestions()
        val questions = listOfQuestions.take(numberOfQuestions)
        var correctAnswers = 0
        var questionIndex = 0
        while(questionIndex<size()){
            printQuestion(questionIndex,questions)
            val answer = readLine()
            if(isCorrect(answer)){
                ++correctAnswers
            }
            ++questionIndex
            printScore(correctAnswers, questionIndex)
        }
    }

    private fun printScore(correctAnswers: Int, questionIndex: Int) {
        println("$correctAnswers / ${questionIndex}")
    }

    private fun printQuestion(questionIndex: Int, questions: List<Question>) {
        var i =1
        println(questions[questionIndex].text)
        questions[questionIndex].answers.forEach { println("$i. $it")
                                                    ++i}
    }

    private fun isCorrect(answer: String?): Boolean {
        if(answer == "1"){
            return true
        }
        return false
    }

    fun size():Int = listOfQuestions.size
}