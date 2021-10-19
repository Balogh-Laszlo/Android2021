package com.example.quizapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.quizapp.Utils.SharedViewModel


class ResultFragment : Fragment() {

    private lateinit var btnTryAgain : Button
    private lateinit var tvScore : TextView
    private val model: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_result, container, false)
        initialize(view)
        val score = model.score.value
        val numOfQuestion = model.numOfQuestions.value
        val temp = "$score/$numOfQuestion"
        tvScore.text = temp

        btnTryAgain.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_startFragment)
        }
        return view
    }

    private fun initialize(view: View?) {
        if(view != null){
            btnTryAgain = view.findViewById(R.id.btnTryAgain)
            tvScore = view.findViewById(R.id.tvScore)
        }

    }

}