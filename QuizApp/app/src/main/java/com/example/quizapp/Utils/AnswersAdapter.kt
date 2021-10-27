package com.example.quizapp.Utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R

class AnswersAdapter(
    private val context : Context,
    private val answers:List<Answer>
) : RecyclerView.Adapter<AnswersAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val tvSingleAnswer = itemView.findViewById<TextView>(R.id.tvSingleAnswer)
        private val rlAnswersLayout = itemView.findViewById<RelativeLayout>(R.id.rlAnswersLayout)
        @SuppressLint("ResourceAsColor")
        fun bind(position: Int) {
            val item = answers[position]
            tvSingleAnswer.text = item.answer
            if(item.value){
                rlAnswersLayout.setBackgroundColor(R.color.teal_200)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.answers_card,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = answers.size
}