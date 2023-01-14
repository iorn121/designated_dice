package com.example.designateddice

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow

class MainActivity : AppCompatActivity(), View.OnTouchListener {

    private var begin: Long=System.nanoTime()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnTouchListener(this)
    }
    override fun onTouch(view: View, motEvent: MotionEvent): Boolean {
        when (view.id) {
            R.id.button -> {
                when (motEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        Log.d("app6/onTouch", "button(ACTION_DOWN)")
                        begin=System.nanoTime()
                    }
                    MotionEvent.ACTION_UP -> {
                        Log.d("app6/onTouch", "button(ACTION_UP)")
                        val now=System.nanoTime()
                        val pushedTime: Int= ((now-begin)/ 10.toDouble().pow(9.0)).toInt()%6+1
                        Log.d("diceNuapp executed", "pushed time: $pushedTime")
                        rollDice(pushedTime)
                    }
                    else -> {
                        Log.d("app6/onTouch", "button(" + motEvent.action.toString() + ")")
                        shuffleDice()
                    }
                }
            }
        }

        // trueでコールバック処理終了。falseはコールバック処理を継続。
        return (false)
    }


    private fun rollDice(diceNum: Int) {
        val diceImage: ImageView = findViewById(R.id.imageView)
        val diceText: TextView=findViewById(R.id.DiceNumber)
        val drawableResource = when (diceNum) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            else -> R.drawable.dice_6
        }
        diceText.text=diceNum.toString()
        diceImage.setImageResource(drawableResource)
        diceImage.contentDescription = diceNum.toString()
    }

    private fun shuffleDice() {
        val diceImage: ImageView = findViewById(R.id.imageView)
        val dices = arrayOf(R.drawable.dice_1,R.drawable.dice_2,R.drawable.dice_3,R.drawable.dice_4,R.drawable.dice_5,R.drawable.dice_6)
        val num= (1..6).random()
        diceImage.setImageResource(dices[num-1])
        diceImage.contentDescription = num.toString()
    }
}