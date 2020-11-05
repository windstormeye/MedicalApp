package com.pjhubs.medical.question.ai

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.pjhubs.medical.R

class QuetionsContentLayout(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.activity_quick_question, this)
    }
}