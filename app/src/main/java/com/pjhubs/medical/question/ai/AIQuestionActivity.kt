package com.pjhubs.medical.question.ai

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.pjhubs.medical.R
import com.pjhubs.medical.question.QuickQuestion
import com.pjhubs.medical.question.QuickQuestionFinishActivity
import kotlinx.android.synthetic.main.activity_a_i_question.*
import kotlinx.android.synthetic.main.activity_quick_question.*

class AIQuestionActivity : AppCompatActivity() {

    private val questionList = ArrayList<QuickQuestion>()
    private var answerList = ArrayList<String>()
    private var questionIndex = 0
    private var adapter: ArrayAdapter<String>? = null
    private var correctCount = 0

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
            initList()
        }, 2000)
    }

    private fun initList() {
        initModels()

        adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, answerList)
        answerListView.adapter = adapter

        answerListView.setOnItemClickListener { parent, view, position, id ->
            val quickQuestion = questionList[questionIndex]
            // NOTE: 答对数 +1
            if (quickQuestion.correct == position) {
                correctCount += 1
            }

            // NOTE: 下一题
            if (questionIndex + 1 < 3) {
                questionIndex += 1
                val question = questionList[questionIndex]
                updateLisView(question)
            } else {
                // NOTE: 答完进入总结页
                val errorCount = questionList.size - correctCount
                var message = "全部答对啦！"
                var nextMessage = "好"

                if (errorCount != 0) {
                    message = "答错了 $errorCount 道题"
                    nextMessage = "查看解析"
                }

                AlertDialog.Builder(this).apply {
                    setTitle("答题结束")
                    setMessage(message)
                    setCancelable(false)
                    setPositiveButton(nextMessage) { _, _ ->
                        val intent = Intent(this.context, QuickQuestionFinishActivity::class.java)
                        intent.putExtra("corretCount", correctCount)
                        startActivity(intent)
                        finish()
                    }
                    setNegativeButton("结束答题") { _, _ ->
                        finish()
                    }
                    show()
                }
            }
        }
    }

    private fun updateLisView(question: QuickQuestion) {
        // NOTE: 需要更换内存指向
        answerList.clear()
        answerList.addAll(question.answers.toList() as ArrayList<String>)
        contentTextView.text = question.desc
        adapter!!.notifyDataSetChanged()
    }

    private fun initModels() {
        questionList.add(QuickQuestion("下列关于热量的说法正确的是", listOf("A.每克脂肪可提供9千卡的热量", "B.每克脂肪可提供4千卡的热量", "C.每克蛋白质可提供9千卡的热量", "D每克碳水化合物可提供9千卡的热量").toTypedArray(), 0))
        questionList.add(QuickQuestion("下列糖友用餐方式恰当的是", listOf("A.每顿不吃主食", "B.把正餐的部分主食作为加餐", "C.每天主食低于150克", "D.按正餐量作为加餐").toTypedArray(), 1))
        questionList.add(QuickQuestion("判断某糖友低血糖的标准是", listOf("A.血糖≤2.8mmol/L", "B.血糖≤3.9mmol/L", "C.血糖≤4.4mmol/L", "D.按正餐量作为加餐").toTypedArray(), 1))

        val question = questionList[questionIndex]
        answerList = question.answers.toList() as ArrayList<String>
        contentTextView.text = question.desc
    }
}