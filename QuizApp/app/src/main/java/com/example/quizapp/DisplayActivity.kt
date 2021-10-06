package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.quizapp.Utils.EXTRA_NAME

class DisplayActivity : AppCompatActivity() {
    private lateinit var tvText:TextView
    private lateinit var tvName:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)
        tvText = findViewById(R.id.tvText)
        tvText.text = "The name of the player is:"
        tvName = findViewById(R.id.tvName)
        tvName.text = intent.getStringExtra(EXTRA_NAME)


    }
}