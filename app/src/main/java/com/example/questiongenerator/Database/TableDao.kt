package com.example.questiongenerator.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TableDao {
    @Insert
    fun addQuestion(question: Question): Long

    @Insert
    fun addAnswer(answer: Array<Answer>)

    @Query("select* from tbl_answer where questionId = :questionID")
    fun getAnswers(questionID : Int): List<Answer>

    @Query("select* from tbl_question")
    fun getQuestions(): List<Question>

    @Query("select* from tbl_question where difficulty = :difficulty order by random() limit :num")
    fun getQuestions(difficulty : Int,  num : Int) : List<Question>

    @Delete
    fun deleteQuestion(question: Question)

    @Query("delete from tbl_answer where questionId = :questionID")
    fun deleteAnswers(questionID: Int)
}