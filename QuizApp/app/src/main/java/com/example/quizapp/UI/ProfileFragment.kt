package com.example.quizapp.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.quizapp.R
import com.example.quizapp.Utils.SharedViewModel


class ProfileFragment : Fragment() {
    private val model: SharedViewModel by activityViewModels()
    private lateinit var tvPlayerName: TextView
    private lateinit var tvHighScore: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        initializeView(view)
        setValues()
        return view
    }

    private fun setValues() {
        if(model.playerName.value != null){
            tvPlayerName.text = model.playerName.value
        }
        else{
            tvPlayerName.text = ""
        }
        if(model.highScore.value != null){
            tvHighScore.text = model.highScore.value.toString()
        }
        else{
            tvHighScore.text = "0.0"
        }
    }

    private fun initializeView(view: View?) {
        if(view != null){
            tvPlayerName = view.findViewById(R.id.tvPlayerName)
            tvHighScore = view.findViewById(R.id.tvHighScore)
        }
    }


}