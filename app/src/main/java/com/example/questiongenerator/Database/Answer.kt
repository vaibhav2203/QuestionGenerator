package com.example.questiongenerator.Database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_answer",
    foreignKeys = [ForeignKey(
        entity = Question::class,
        parentColumns = ["id"],
        childColumns = ["questionId"],
        onDelete = CASCADE
    )]
)
class Answer {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var detail = ""
    var correct = false
    var questionId = 0
}