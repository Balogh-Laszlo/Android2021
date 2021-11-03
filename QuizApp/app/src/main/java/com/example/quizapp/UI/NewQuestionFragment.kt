package com.example.quizapp.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.QuizController
import com.example.quizapp.R
import com.example.quizapp.Utils.Answer
import com.example.quizapp.Utils.NewAnswerAdapter
import com.example.quizapp.Utils.Question
import com.example.quizapp.Utils.QuestionType


class NewQuestionFragment : Fragment() {
    private lateinit var rvNewAnswers : RecyclerView
    private lateinit var btnSubmit : Button
    private lateinit var etQuestion : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_new_question, container, false)
        rvNewAnswers = view.findViewById(R.id.rvNewAnswers)
        btnSubmit = view.findViewById(R.id.btnSubmitNewQuestion)
        etQuestion = view.findViewById(R.id.etNewQuestion)

        val answers = mutableListOf<Answer>()
        val adapter = NewAnswerAdapter(requireContext(),answers)
        rvNewAnswers.adapter = adapter
        rvNewAnswers.layoutManager = LinearLayoutManager(requireContext())

        btnSubmit.setOnClickListener {
            if(etQuestion.text.isNotEmpty()){
                if(answers.size >1) {
                    QuizController.listOfQuestions.add(Question(etQuestion.text.toString(),answers,QuestionType.MANY_CORRECT))
                    findNavController().navigate(R.id.questionListFragment)
                }
            }
        }
        return view
    }

}