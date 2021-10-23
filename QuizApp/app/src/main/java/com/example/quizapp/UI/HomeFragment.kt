package com.example.quizapp.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R

class HomeFragment : Fragment() {
    private lateinit var btnStartQuiz:Button
    private lateinit var btnReadQuestions:Button
    private lateinit var btnNewQuestion:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        initialize(view)
        registerListeners()
        return view
    }

    private fun initialize(view: View?) {
        if(view != null){
            btnStartQuiz = view.findViewById(R.id.btnStartQuiz)
            btnReadQuestions = view.findViewById(R.id.btnReadQuestions)
            btnNewQuestion = view.findViewById(R.id.btnNewQuestion)
        }
    }
    private fun registerListeners() {
        btnStartQuiz.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_startFragment)
        }
    }


}