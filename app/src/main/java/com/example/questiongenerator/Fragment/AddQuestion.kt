package com.example.questiongenerator.Fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.questiongenerator.Database.Answer
import com.example.questiongenerator.Database.Database
import com.example.questiongenerator.Database.Question
import com.example.questiongenerator.R
import kotlinx.android.synthetic.main.fragment_add_question.view.*

/**
 * A simple [Fragment] subclass for adding question
 */
class AddQuestion : Fragment() {

    var difficulty = arrayOf("Select One", "Easy", "Medium", "Hard")
    var option = arrayOf("Select One", "1", "2", "3", "4")


    /*
    * gets called when fragment is displayed in the activity
    * lifecycle spans throughout the activity
    * */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_add_question, container, false)
        val arrayAdapterDifficulty =
            ArrayAdapter(activity!!, android.R.layout.simple_spinner_item, difficulty)
        root.difficultySpinner.adapter = arrayAdapterDifficulty

        val arrayAdapterOption =
            ArrayAdapter(activity!!, android.R.layout.simple_spinner_item, option)
        root.correctOption.adapter = arrayAdapterOption

        root.submit.setOnClickListener {
            if (root.question.text.isNullOrEmpty()) {
                root.question.error = "Please fill up the blank"
            } else if (root.option1.text.isNullOrEmpty()) {
                root.option1.error = "Please fill up the blank"
            } else if (root.option2.text.isNullOrEmpty()) {
                root.option2.error = "Please fill up the blank"
            } else if (root.option3.text.isNullOrEmpty()) {
                root.option3.error = "Please fill up the blank"
            } else if (root.option4.text.isNullOrEmpty()) {
                root.option4.error = "Please fill up the blank"
            } else if (root.difficultySpinner.selectedItemId == 0L) {
                Toast.makeText(
                    activity!!,
                    "Please select the difficulty properly",
                    Toast.LENGTH_LONG
                ).show()
            } else if (root.correctOption.selectedItemId == 0L) {
                Toast.makeText(
                    activity!!,
                    "Please select the option properly",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val question = Question()
                question.detail = root.question.text.toString()
                question.difficulty = root.difficultySpinner.selectedItemId
                val questionID = Database.getInstance(activity!!.applicationContext)?.tableDao()
                    ?.addQuestion(question)
                val answers = Array<Answer>(4) { Answer() }
                answers[0].detail = root.option1.text.toString()
                answers[1].detail = root.option2.text.toString()
                answers[2].detail = root.option3.text.toString()
                answers[3].detail = root.option4.text.toString()
                answers[root.correctOption.selectedItemId.toInt() - 1].correct = true
                for (answer in answers)
                    answer.questionId = questionID!!.toInt()
                Database.getInstance(activity!!.applicationContext)?.tableDao()
                    ?.addAnswer(answers)
                Toast.makeText(
                    activity!!,
                    "Question added successfully",
                    Toast.LENGTH_LONG
                ).show()
                root.question.setText("")
                root.option1.setText("")
                root.option2.setText("")
                root.option3.setText("")
                root.option4.setText("")
                root.difficultySpinner.setSelection(0)
                root.correctOption.setSelection(0)
            }
        }
        return root
    }


}
