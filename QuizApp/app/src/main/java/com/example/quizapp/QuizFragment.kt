package com.example.quizapp


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.quizapp.Utils.Question
import com.example.quizapp.Utils.QuestionType
import com.example.quizapp.Utils.SharedViewModel


class QuizFragment : Fragment() {

    private lateinit var btnNext:Button
    private lateinit var tvQuestion : TextView
    private lateinit var quiz:QuizController
    private lateinit var rgAnswers : RadioGroup
    private var currentQuestion = 0
    private val checkBoxes = mutableListOf<CheckBox>()
    private val radioButtons = mutableListOf<RadioButton>()
    private lateinit var shortAnswerEditText : EditText
    private var score:Double = 0.0
    private val model:SharedViewModel by activityViewModels()
    companion object{
        const val TAG = "QUIZ FRAGMENT"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val callback : OnBackPressedCallback = object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                showdialog()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,callback)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_quiz, container, false)
        initializeView(view)
        quiz = QuizController()
        quiz.randomizeQuestions()
        var question = quiz.getQuestion(currentQuestion)
        bindQuestion(question)

        btnNext.setOnClickListener {
            currentQuestion++

                markAnswer(question)
                if(currentQuestion == quiz.size()-1){
                    btnNext.text = getString(R.string.submit)
                }
                if (currentQuestion < quiz.size()) {
                    question = quiz.getQuestion(currentQuestion)
                    bindQuestion(question)
                } else {
                    model.result(score)
                    model.numOfQuestions(currentQuestion)
                    findNavController().navigate(R.id.action_quizFragment_to_resultFragment)
                    Log.i(TAG,"current score $score")
                }
            }
        return view
    }

    private fun markAnswer(question: Question) {
        if(question.type == QuestionType.ONE_CORRECT){
            for (i in 0 until radioButtons.size){
                if (radioButtons[i].isChecked && question.answers[i].value){
                    score +=1.0
                    Log.i(TAG,"current score $score")
                }
            }
        }
        if(question.type == QuestionType.TRUE_OR_FALSE || question.type == QuestionType.MANY_CORRECT) {
            val numCorrect = question.answers.count { it.value }
            for (i in 0 until checkBoxes.size) {
                if (checkBoxes[i].isChecked && question.answers[i].value) {
                    score += (1.0 / numCorrect)
                }
//                if(!checkBoxes[i].isChecked && !question.answers[i].value) {
////                    score -= (1.0 / (question.answers.size -numCorrect))
//                }
            }
        }
        if(question.type == QuestionType.SHORT_ANSWER){
            for ( i in 0 until question.answers.size){
                if(shortAnswerEditText.text.toString() == question.answers[i].answer){
                    score+=1.0
                }
            }
        }
        Log.i(TAG,"current score $score")
    }

    private fun bindQuestion(question: Question) {
        tvQuestion.text = question.text
        rgAnswers.removeAllViews()
        if(question.type == QuestionType.ONE_CORRECT) {
            radioButtons.removeAll{ it==it}
            for (i in 0 until question.answers.size) {
                val geek = RadioButton(requireContext())
                geek.layoutParams = RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                geek.text = question.answers[i].answer
                geek.id = i
                radioButtons.add(geek)
                rgAnswers.addView(geek)
            }
        }
        else if(question.type == QuestionType.MANY_CORRECT || question.type == QuestionType.TRUE_OR_FALSE){
            checkBoxes.removeAll{ it==it}
            for (i in 0 until question.answers.size) {
                val geek = CheckBox(requireContext())
                geek.layoutParams = RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                geek.text = question.answers[i].answer
                geek.id = i
                checkBoxes.add(geek)
                rgAnswers.addView(geek)
            }
        }
        else if(question.type == QuestionType.SHORT_ANSWER){
            val geek = EditText(requireContext())
            geek.layoutParams = RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            geek.hint = "Type here!"
            shortAnswerEditText=geek
            rgAnswers .addView(geek)

        }
    }


    private fun initializeView(view: View) {
        btnNext = view.findViewById(R.id.btnNext)
        tvQuestion = view.findViewById(
            R.id.tvQuestion
        )
        rgAnswers = view.findViewById(R.id.rgAnswers)
    }
    private fun showdialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Exit")
            .setMessage("Are you sure you want to end this quiz?")
            .setNegativeButton("Cancel",null)
            .setPositiveButton("OK"){ _, _ ->
                findNavController().navigate(R.id.action_quizFragment_to_resultFragment)
            }.show()
    }

}