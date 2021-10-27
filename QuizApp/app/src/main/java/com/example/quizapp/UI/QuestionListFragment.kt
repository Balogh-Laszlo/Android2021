package com.example.quizapp.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.QuizController
import com.example.quizapp.R
import com.example.quizapp.Utils.QuestionCardAdapter
import com.example.quizapp.Utils.SharedViewModel


class QuestionListFragment : Fragment() {
    private lateinit var rvQuestion : RecyclerView
    private lateinit var adapter : QuestionCardAdapter
    private val model: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_question_list, container, false)
        rvQuestion = view.findViewById(R.id.rvQuestions)
        adapter = QuestionCardAdapter(requireContext(),QuizController.listOfQuestions,this,model)
        rvQuestion.adapter = adapter
        rvQuestion.layoutManager = LinearLayoutManager(requireContext())

        return view
    }

}