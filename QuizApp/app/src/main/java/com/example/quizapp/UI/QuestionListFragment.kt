package com.example.quizapp.UI

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.QuizController
import com.example.quizapp.R
import com.example.quizapp.Utils.Question
import com.example.quizapp.Utils.QuestionCardAdapter
import com.example.quizapp.Utils.SharedViewModel
import com.example.quizapp.Utils.any_category


class QuestionListFragment : Fragment() {
    private lateinit var rvQuestion : RecyclerView
    private lateinit var adapter : QuestionCardAdapter
    private lateinit var spCategory: Spinner
    private val model: SharedViewModel by activityViewModels()
    private lateinit var questionList : MutableList<Question>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_question_list, container, false)

        spCategory = view.findViewById(R.id.spCategory)

        if(model.categoryList.value == null){
            QuizController()
            model.categoryList.value = mutableListOf(any_category)
        }
        QuizController.listOfQuestions.forEach {
            if(!model.categoryList.value!!.contains(it.category)){
                model.categoryList.value!!.add(it.category)
                Log.i("TEST", model.categoryList.value!!.last())
            }
        }
        if(model.categoryList.value!=null) {
            val spAdapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                model.categoryList.value!!
            )
            spCategory.adapter = spAdapter
        }
        rvQuestion = view.findViewById(R.id.rvQuestions)
        questionList = mutableListOf()
        questionList.addAll(QuizController.listOfQuestions)
        adapter = QuestionCardAdapter(requireContext(),questionList,this,model)
        rvQuestion.adapter = adapter
        rvQuestion.layoutManager = LinearLayoutManager(requireContext())
        spCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            @SuppressLint("NotifyDataSetChanged")
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedCategory = model.categoryList.value!![p2]
                Log.d("TEST","The selected category $selectedCategory")
                if(selectedCategory == any_category){
                    questionList.removeAll{ it!=null }
                    questionList.addAll(QuizController.listOfQuestions)
                }
                else{
                    questionList.removeAll{ it!=null }
                    Log.i("TEST","listOfQuestions size: ${QuizController.listOfQuestions.size}")
                    QuizController.listOfQuestions.forEach {
                        if(it.category == selectedCategory){
                            questionList.add(it)
                        }

                    }
                }
                adapter.notifyDataSetChanged()
                Log.i("TEST",questionList.size.toString())

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        return view
    }

}