package com.example.solve_example

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import java.text.DecimalFormat

val operation = listOf(
    "+",
    "-",
    "*",
    "/"
)
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
}
    fun btnStartClickFun(view:View){
        val firstNum = findViewById<TextView>(R.id.txtFirstOperand)
        val secondNum = findViewById<TextView>(R.id.txtSecondOperand)
        val operationStr = findViewById<TextView>(R.id.txtOperation)
        val Layout = findViewById<LinearLayout>(R.id.LayoutResult)
        Layout.setBackgroundColor(Color.argb(255, 255, 255, 255))
        operationStr.text = operation.random()
        var firstNumKey:Int
        var secondNumKey:Int
        if(operationStr.text == "/"){
            do {
                firstNumKey = (10..99).random()
                secondNumKey = (10..99).random()
            }while (firstNumKey%secondNumKey != 0)

            firstNum.text = firstNumKey.toString()
            secondNum.text = secondNumKey.toString()
        }
        else{
            firstNum.text = (10..99).random().toString()
            secondNum.text = (10..99).random().toString()
        }
        val EnterEdit = findViewById<EditText>(R.id.editValue)


        EnterEdit.text.clear()
        val btn1 = findViewById<Button>(R.id.btnStart)
        btn1.isEnabled = false
        val btn2 = findViewById<Button>(R.id.btnCheck)
        val searchTo : EditText = findViewById(R.id.editValue)
        searchTo.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable) {
                val str:String = EnterEdit.text.toString()
                btn2.isEnabled = !str.trim().isEmpty()
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }

    fun btnCheckClickFun(view: View){
        val btn1 = findViewById<Button>(R.id.btnStart)
        btn1.isEnabled = true
        val btn2 = findViewById<Button>(R.id.btnCheck)
        btn2.isEnabled = false
        val firstNum = findViewById<TextView>(R.id.txtFirstOperand)
        val secondNum = findViewById<TextView>(R.id.txtSecondOperand)
        val operationStr = findViewById<TextView>(R.id.txtOperation)
        val EnterEdit = findViewById<EditText>(R.id.editValue)

        val Layout = findViewById<LinearLayout>(R.id.LayoutResult)
        val RightNum = findViewById<TextView>(R.id.txtRight)
        val WrongNum = findViewById<TextView>(R.id.txtwrong)
        val AllNum = findViewById<TextView>(R.id.txtAllExamples)
        AllNum.text = (AllNum.text.toString().toInt() + 1).toString()
        var result = 0
        when(operationStr.text){
            "+" -> {
                result = firstNum.text.toString().toInt() + secondNum.text.toString().toInt()
            }
            "-" -> {
                result = firstNum.text.toString().toInt() - secondNum.text.toString().toInt()
            }
            "*" -> {
                result = firstNum.text.toString().toInt() * secondNum.text.toString().toInt()
            }
            "/" -> {
                result = firstNum.text.toString().toInt() / secondNum.text.toString().toInt()
            }
        }
       if (EnterEdit.text.toString().toInt() != result ) {
           Layout.setBackgroundColor(Color.argb(255,223,36,36))
           WrongNum.text = (WrongNum.text.toString().toInt() + 1).toString()
       }
        else{
           Layout.setBackgroundColor(Color.argb(255,77,223,36))
           RightNum.text = (RightNum.text.toString().toInt() + 1).toString()
        }
        val AllProcent = findViewById<TextView>(R.id.txtProcent)
        var dop = DecimalFormat("#.##")
        var allkey = dop.format((RightNum.text.toString().toDouble() * 100)/AllNum.text.toString().toDouble())
        AllProcent.text = allkey.toString() + "%"
    }
}