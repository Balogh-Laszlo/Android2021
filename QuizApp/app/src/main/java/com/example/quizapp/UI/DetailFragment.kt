package com.example.quizapp.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.Utils.AnswersAdapter
import com.example.quizapp.Utils.SharedViewModel


class DetailFragment : Fragment() {
    private val model: SharedViewModel by activityViewModels()
    private lateinit var tvQuestion : TextView
    private lateinit var tvType : TextView
    private lateinit var rvAnswers : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        initialize(view)
        if (model.currentQuestion!= null) {
        val adapter = AnswersAdapter(requireContext(), model.currentQuestion!!.answers)
        rvAnswers.adapter = adapter
        rvAnswers.layoutManager = LinearLayoutManager(requireContext())
    }
        tvType.text = model.currentQuestion!!.type.name
        tvQuestion.text = model.currentQuestion!!.text
        return view
    }

    private fun initialize(view: View?) {
        if(view != null){
            tvQuestion = view.findViewById(R.id.tvQuestionDetail)
            tvType = view.findViewById(R.id.tvTypeDetail)
            rvAnswers = view.findViewById(R.id.rvAnswersDetail)
        }
    }

}