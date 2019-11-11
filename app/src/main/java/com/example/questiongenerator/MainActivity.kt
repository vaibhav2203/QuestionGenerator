package com.example.questiongenerator

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
/*
* Splash Screen for the android application
* */
class MainActivity : AppCompatActivity() {


    /*
    * gets called when the activity is created
    * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler().postDelayed({
            finish()
            startActivity(Intent(this, Main2Activity::class.java))
        }, 2000)
    }
}
