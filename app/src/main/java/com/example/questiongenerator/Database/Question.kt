package com.example.questiongenerator.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
* Question Table stores the details of the questions entered
* */
@Entity(tableName = "tbl_question")
class Question {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var detail = ""
    var difficulty = 0L

}