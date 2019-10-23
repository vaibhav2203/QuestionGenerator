package com.example.questiongenerator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.questiongenerator.Database.Answer
import com.example.questiongenerator.Database.Database
import com.example.questiongenerator.Database.Question
import kotlinx.android.synthetic.main.layout_row.view.*

class RecyclerAdapter(
    val items: ArrayList<Question>,
    val activity: FragmentActivity,
    val recyclerView: RecyclerView
) : RecyclerView.Adapter<RecyclerAdapter.holder>() {

    override fun onBindViewHolder(holder: holder, position: Int) {
        if (recyclerView.id != R.id.deleteLayout) {
            holder.delete.visibility = View.GONE
            holder.difficulty.visibility = View.GONE
        }
        holder.question.setText("Question : " + items.get(position).detail)
        val answers =
            Database.getInstance(activity)!!.tableDao().getAnswers(items[position].id) as ArrayList<Answer>
        holder.option1.text = "A : ${answers[0].detail}"
        holder.option2.text = "B : ${answers[1].detail}"
        holder.option3.text = "C : ${answers[2].detail}"
        holder.option4.text = "D : ${answers[3].detail}"
        holder.delete.setOnClickListener {
            Database.getInstance(activity)!!.tableDao().deleteAnswers(items[position].id)
            Database.getInstance(activity)!!.tableDao().deleteQuestion(items[position])
            items.removeAt(position)
            notifyItemRemoved(position)
        }
        when {
            items[position].difficulty == 1L -> {
                holder.difficulty.text = "Difficulty : EASY"
            }

            items[position].difficulty == 2L -> {
                holder.difficulty.text = "Difficulty : MEDIUM"
            }

            items[position].difficulty == 3L -> {
                holder.difficulty.text = "Difficulty : HARD"
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder {
        return holder(LayoutInflater.from(activity).inflate(R.layout.layout_row, parent, false))
    }

    inner class holder(view: View) : RecyclerView.ViewHolder(view) {
        val question = view.question
        val option1 = view.option1
        val option2 = view.option2
        val option3 = view.option3
        val option4 = view.option4
        val delete = view.delete
        val difficulty = view.difficulty
    }
}


