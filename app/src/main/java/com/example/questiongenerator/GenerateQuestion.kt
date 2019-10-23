package com.example.questiongenerator


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.questiongenerator.Database.Database
import com.example.questiongenerator.Database.Question
import kotlinx.android.synthetic.main.fragment_generate_question.*
import kotlinx.android.synthetic.main.fragment_generate_question.view.*

/**
 * A simple [Fragment] subclass.
 */
class GenerateQuestion : Fragment() {

    private var hard = 0
    private var medium = 0
    private var easy = 0
    private lateinit var hardQuestions: ArrayList<Question>
    private lateinit var mediumQuestions: ArrayList<Question>
    private lateinit var easyQuestions: ArrayList<Question>
    private lateinit var totalCaptureResult: ArrayList<Question>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_generate_question, container, false)
        root.submit.setOnClickListener {
            if (Integer.valueOf(hardCount.text.toString()) * 30 +
                Integer.valueOf(mediumCount.text.toString()) * 20 +
                Integer.valueOf(easyCount.text.toString()) * 10 == 100
            ) {
                hard = Integer.valueOf(hardCount.text.toString())
                medium = Integer.valueOf(mediumCount.text.toString())
                easy = Integer.valueOf(easyCount.text.toString())
                hardQuestions = Database.getInstance(activity!!)?.tableDao()?.getQuestions(
                    3,
                    hard
                ) as ArrayList<Question>
                mediumQuestions = Database.getInstance(activity!!)?.tableDao()?.getQuestions(
                    2,
                    medium
                ) as ArrayList<Question>
                easyQuestions = Database.getInstance(activity!!)?.tableDao()?.getQuestions(
                    1,
                    easy
                ) as ArrayList<Question>
                if (hard == hardQuestions.size && medium == mediumQuestions.size && easy == easyQuestions.size) {
                    title.text = "Question Paper"
                    totalCaptureResult = easyQuestions;
                    totalCaptureResult.addAll(mediumQuestions)
                    totalCaptureResult.addAll(hardQuestions)
                    root.countLayout.visibility = View.GONE
                    root.questionPaper.visibility = View.VISIBLE
                    val recyclerAdapter =
                        RecyclerAdapter(totalCaptureResult, activity!!, root.questionPaper)
                    root.questionPaper.layoutManager =
                        LinearLayoutManager(activity!!, LinearLayoutManager.VERTICAL, false)
                    root.questionPaper.adapter = recyclerAdapter
                } else {
                    Toast.makeText(
                        activity!!.applicationContext,
                        "Please add enough questions for us to generate question paper",
                        Toast.LENGTH_LONG
                    ).show()

                }
            } else {
                Toast.makeText(
                    activity!!.applicationContext,
                    "Please enter the questions in order to get the paper total to 100",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        return root
    }


}
