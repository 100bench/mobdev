package io.github.mobdev

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import io.github.mobdev.calculator.CalculatorViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: CalculatorViewModel by viewModels()
    private lateinit var display: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.display)
        bindDigit(R.id.btn0, '0')
        bindDigit(R.id.btn1, '1')
        bindDigit(R.id.btn2, '2')
        bindDigit(R.id.btn3, '3')
        bindDigit(R.id.btn4, '4')
        bindDigit(R.id.btn5, '5')
        bindDigit(R.id.btn6, '6')
        bindDigit(R.id.btn7, '7')
        bindDigit(R.id.btn8, '8')
        bindDigit(R.id.btn9, '9')

        findViewById<Button>(R.id.btnDot).setOnClickListener {
            viewModel.onDot()
            updateDisplay()
        }
        findViewById<Button>(R.id.btnPlus).setOnClickListener { onOperator('+') }
        findViewById<Button>(R.id.btnMinus).setOnClickListener { onOperator('-') }
        findViewById<Button>(R.id.btnMul).setOnClickListener { onOperator('*') }
        findViewById<Button>(R.id.btnDiv).setOnClickListener { onOperator('/') }
        findViewById<Button>(R.id.btnClear).setOnClickListener {
            viewModel.onClear()
            updateDisplay()
        }
        findViewById<Button>(R.id.btnEquals).setOnClickListener {
            viewModel.onEquals()
            updateDisplay()
        }

        updateDisplay()
    }

    private fun bindDigit(buttonId: Int, digit: Char) {
        findViewById<Button>(buttonId).setOnClickListener {
            viewModel.onDigit(digit)
            updateDisplay()
        }
    }

    private fun onOperator(op: Char) {
        viewModel.onOperator(op)
        updateDisplay()
    }

    private fun updateDisplay() {
        display.text = viewModel.display
    }
}
