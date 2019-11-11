package com.example.questiongenerator.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


/*
* Used to call queries for the tables stored in the app
* */
@Dao
interface TableDao {
    /*
    * requests to add a question
    * */
    @Insert
    fun addQuestion(question: Question): Long

    /*
    * requests to add the answers associated with a question
    * */
    @Insert
    fun addAnswer(answer: Array<Answer>)

    /*
    * requests to get the answers associated with the question
    * */
    @Query("select* from tbl_answer where questionId = :questionID")
    fun getAnswers(questionID : Int): List<Answer>

    /*
    * requests to get all the questions from the database
    * */
    @Query("select* from tbl_question")
    fun getQuestions(): List<Question>

    /*
    * requests to get $num the question from the database corresponding to a particular difficulty randomly
    * */
    @Query("select* from tbl_question where difficulty = :difficulty order by random() limit :num")
    fun getQuestions(difficulty : Int,  num : Int) : List<Question>

    /*
    * requests to delete a particular question from the database
    * */
    @Delete
    fun deleteQuestion(question: Question)

    /*
    * requests to delete all the answers corresponding to a question
    * */
    @Query("delete from tbl_answer where questionId = :questionID")
    fun deleteAnswers(questionID: Int)
}