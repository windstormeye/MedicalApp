package com.pjhubs.medical.question

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.pjhubs.medical.R

class QuickQuestionFinishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quick_question_acitivy)

        val corretCount = intent.getIntExtra("corretCount", 0)
        Toast.makeText(this, "$corretCount", Toast.LENGTH_SHORT).show()
    }
}