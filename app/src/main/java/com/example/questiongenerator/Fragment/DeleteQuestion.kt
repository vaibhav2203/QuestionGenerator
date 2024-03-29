package com.example.questiongenerator.Fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.questiongenerator.Database.Database
import com.example.questiongenerator.Database.Question
import com.example.questiongenerator.R
import com.example.questiongenerator.Adapter.RecyclerAdapter
import kotlinx.android.synthetic.main.fragment_delete_question.view.*

/**
 * A simple [Fragment] subclass to delete qustinos from database.
 */
class DeleteQuestion : Fragment() {

    lateinit var adapterRecycler: RecyclerAdapter
    lateinit var questions: ArrayList<Question>
    /*
    * gets called when fragment is displayed in the activity
    * lifecycle spans throughout the activity
    * */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_delete_question, container, false)
        questions = Database.getInstance(activity!!)?.tableDao()?.getQuestions() as ArrayList<Question>
        adapterRecycler =
            RecyclerAdapter(
                questions,
                activity!!,
                root.deleteLayout
            )
        root.deleteLayout.layoutManager = LinearLayoutManager(activity!!, LinearLayoutManager.VERTICAL, false)
        root.deleteLayout.adapter = adapterRecycler
        return root
    }


}
