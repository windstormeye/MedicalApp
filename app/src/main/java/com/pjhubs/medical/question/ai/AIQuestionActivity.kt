package com.pjhubs.medical.question.ai

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.pjhubs.medical.R
import kotlinx.android.synthetic.main.activity_a_i_question.*

class AIQuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a_i_question)

        val playerX = playerImageView.translationX
        val playerXAnimator = ObjectAnimator.ofFloat(playerImageView, "translationX", playerX - 500f, playerX, 500f, playerX)
        playerXAnimator.duration = 2000
        playerXAnimator.start()

        val aiX = aiImageView.translationX
        val aiXAnimator = ObjectAnimator.ofFloat(aiImageView, "translationX", aiX + 500f, aiX, -500f, aiX)
        aiXAnimator.duration = 2000
        aiXAnimator.start()

        Handler().postDelayed({
            ObjectAnimator.ofFloat(playerImageView, "alpha", 0f).start()
            ObjectAnimator.ofFloat(aiImageView, "alpha", 0f).start()
            ObjectAnimator.ofFloat(vsTextView, "alpha", 0f).start()
        }, 2000)

        Handler().postDelayed({
            // NOTE: 在这里载入新布局
            setContentView(R.layout.activity_battle_main)
        }, 2000)
    }
}