package com.example.quizapp.Utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.UI.QuestionListFragment

class QuestionCardAdapter(
    private val context: Context,
    private val questions: MutableList<Question>,
    private val questionListFragment: QuestionListFragment,
    private val model: SharedViewModel

):
    RecyclerView.Adapter<QuestionCardAdapter.ViewHolder>()  {

    inner class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        private val tvQuestion = itemView.findViewById<TextView>(R.id.tvQuestionInCard)
        private val tvType = itemView.findViewById<TextView>(R.id.tvType)
        private val btnDetails = itemView.findViewById<Button>(R.id.btnDetails)
        private val btnDelete = itemView.findViewById<Button>(R.id.btnDelete)
        fun bind(position:Int){
            val item = questions[position]
            tvQuestion.text = item.text
            tvType.text = item.type.name
            btnDelete.setOnClickListener {
                questions.removeAt(position)
                notifyDataSetChanged()
            }
            btnDetails.setOnClickListener {
                model.currentQuestion = questions[position]
                questionListFragment.findNavController().navigate(R.id.detailFragment)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.question_card,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = questions.size
}
