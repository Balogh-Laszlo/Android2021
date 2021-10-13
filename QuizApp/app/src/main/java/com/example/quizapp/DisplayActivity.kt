package com.example.quizapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.quizapp.Utils.EXTRA_NAME
import com.example.quizapp.Utils.EXTRA_URI

class DisplayActivity : AppCompatActivity() {
    private lateinit var tvText:TextView
    private lateinit var tvName:TextView
    private lateinit var ivImageProfile: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)
        tvText = findViewById(R.id.tvText)
        tvName = findViewById(R.id.tvName)
        tvName.text = intent.getStringExtra(EXTRA_NAME)

        ivImageProfile = findViewById(R.id.ivImageProfile)
        val myUri : Uri = Uri.parse(intent.getStringExtra(EXTRA_URI))
        ivImageProfile.setImageURI(myUri)


    }
}