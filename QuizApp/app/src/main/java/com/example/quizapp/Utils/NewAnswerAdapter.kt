package com.example.quizapp.Utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R

class NewAnswerAdapter(
    private val context:Context,
    private val answers: MutableList<Answer>
) : RecyclerView.Adapter<NewAnswerAdapter.ViewHolder>() {
    inner class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        private val etAnswer = itemView.findViewById<EditText>(R.id.etSingleNewAnswer)
        private val cbIsCorrect = itemView.findViewById<CheckBox>(R.id.cbIsCorrect)
        private val btnSubmit = itemView.findViewById<Button>(R.id.btnSubmitAnswer)
        fun bind(){
            btnSubmit.setOnClickListener {
                removeEditPrivileges()
                saveAnswer()
            }
        }

        private fun removeEditPrivileges() {
            etAnswer.isEnabled = false
            cbIsCorrect.isEnabled = false
        }

        private fun saveAnswer() {
            answers.add(Answer(etAnswer.text.toString(),cbIsCorrect.isChecked))
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.new_answer_card,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = answers.size+1
}