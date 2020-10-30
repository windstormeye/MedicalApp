package com.pjhubs.medical.question

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.pjhubs.medical.R
import kotlinx.android.synthetic.main.activity_quick_question.*

class QuickQuestionActivity : AppCompatActivity() {

    private val answerList = listOf("第一个答案", "第二个答案", "第三个答案", "第四个答案")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quick_question)

        val position = intent.getIntExtra("position", -1)
        when (position) {
            0 -> {
            }
            1 -> {
            }
            2 -> {
            }
            3 -> {
            }
            else -> {

            }
        }

        contentTextView.text = "这是问题描述这是问题描述这是问题描述这是问题描述这是问题描述这是问题描述这是问题描述这是问题描述"

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, answerList)
        answerListView.adapter = adapter
    }
}