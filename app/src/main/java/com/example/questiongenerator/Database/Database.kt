package com.example.questiongenerator.Database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

@androidx.room.Database(entities = arrayOf(Answer::class, Question::class), version = 2)
/*
* Maintains the database instance throughout the application
* */
abstract class Database : RoomDatabase() {

    abstract fun tableDao(): TableDao
/*
* keeps a single instance of the databse throughout the application
* */
    companion object {
        private var INSTANCE: Database? = null

        fun getInstance(context: Context): Database? {
            if (INSTANCE == null) {
                synchronized(Database::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        Database::class.java, "dataBase.db"
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

    /*
    * Destroys the instance of the database
    * */
        fun destroyInstance() {
            INSTANCE = null
        }
    }

}