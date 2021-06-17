package com.pjhubs.medical.question

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.pjhubs.medical.R
import kotlinx.android.synthetic.main.activity_quick_question.*

class QuickQuestionActivity : AppCompatActivity() {
    private val questionList = ArrayList<QuickQuestion>()
    private var answerList = ArrayList<String>()
    private var questionIndex = 0
    private var adapter: ArrayAdapter<String>? = null
    private var correctCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quick_question)

        initModels()

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
//                    setPositiveButton(nextMessage) { _, _ ->
//                        val intent = Intent(this.context, QuickQuestionFinishActivity::class.java)
//                        intent.putExtra("corretCount", correctCount)
//                        startActivity(intent)
//                        finish()
//                    }
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