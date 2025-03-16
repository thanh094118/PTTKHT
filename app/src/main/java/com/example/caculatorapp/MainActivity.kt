package com.example.calculatorapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var currentInput = StringBuilder()
    private var currentOperator: String? = null
    private var firstOperand: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.display)

        val numberButtons = listOf(
            findViewById<Button>(R.id.button0),
            findViewById<Button>(R.id.button1),
            findViewById<Button>(R.id.button2),
            findViewById<Button>(R.id.button3),
            findViewById<Button>(R.id.button4),
            findViewById<Button>(R.id.button5),
            findViewById<Button>(R.id.button6),
            findViewById<Button>(R.id.button7),
            findViewById<Button>(R.id.button8),
            findViewById<Button>(R.id.button9)
        )

        val operatorButtons = listOf(
            findViewById<Button>(R.id.buttonAdd),
            findViewById<Button>(R.id.buttonSubtract),
            findViewById<Button>(R.id.buttonMultiply),
            findViewById<Button>(R.id.buttonDivide)
        )

        val buttonDecimal = findViewById<Button>(R.id.buttonDecimal)
        val buttonEquals = findViewById<Button>(R.id.buttonEquals)
        val buttonClear = findViewById<Button>(R.id.buttonClear)
        val buttonPlusMinus = findViewById<Button>(R.id.buttonPlusMinus)

        numberButtons.forEach { button ->
            button.setOnClickListener { appendNumber(button.text.toString()) }
        }

        operatorButtons.forEach { button ->
            button.setOnClickListener { setOperator(button.text.toString()) }
        }

        buttonDecimal.setOnClickListener { appendDecimal() }
        buttonEquals.setOnClickListener { calculateResult() }
        buttonClear.setOnClickListener { clearDisplay() }
        buttonPlusMinus.setOnClickListener { toggleSign() }
    }

    private fun appendNumber(number: String) {
        currentInput.append(number)
        updateDisplay()
    }

    private fun appendDecimal() {
        if (!currentInput.contains(".")) {
            currentInput.append(".")
            updateDisplay()
        }
    }

    private fun setOperator(operator: String) {
        if (currentInput.isNotEmpty()) {
            firstOperand = currentInput.toString().toDouble()
            currentOperator = operator
            currentInput.clear()
            updateDisplay()
        }
    }

    private fun calculateResult() {
        if (currentInput.isNotEmpty() && currentOperator != null) {
            val secondOperand = currentInput.toString().toDouble()
            val result = when (currentOperator) {
                "+" -> firstOperand + secondOperand
                "-" -> firstOperand - secondOperand
                "*" -> firstOperand * secondOperand
                "/" -> firstOperand / secondOperand
                else -> 0.0
            }
            currentInput.clear()
            currentInput.append(result)
            currentOperator = null
            updateDisplay()
        }
    }

    private fun clearDisplay() {
        currentInput.clear()
        currentInput.append("0")
        currentOperator = null
        updateDisplay()
    }

    private fun toggleSign() {
        if (currentInput.isNotEmpty() && currentInput.toString() != "0") {
            val value = currentInput.toString().toDouble()
            currentInput.clear()
            currentInput.append(-value)
            updateDisplay()
        }
    }

    private fun updateDisplay() {
        display.text = currentInput.toString()
    }
}