package com.example.calculator_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var number1EditText: EditText
    private lateinit var number2EditText: EditText
    private lateinit var operationSpinner: Spinner
    private lateinit var calculateButton: Button
    private lateinit var resultTextView: TextView

    var ops = arrayOf<String>("+", "-", "/", "*", "%")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        number1EditText = findViewById(R.id.number1EditText)
        number2EditText = findViewById(R.id.number2EditText)
        operationSpinner = findViewById(R.id.operationSpinner)
        calculateButton = findViewById(R.id.calculateButton)
        resultTextView = findViewById(R.id.resultTextView)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, ops)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        operationSpinner.adapter = adapter

        calculateButton.setOnClickListener {
            calculate(it)
        }
    }

    private fun calculate(view: View) {
        resultTextView.text = ""

        val number1Str = number1EditText.text.toString()
        val number2Str = number2EditText.text.toString()

        if (number1Str.isEmpty() || number2Str.isEmpty()) {
            Snackbar.make(
                view,
                R.string.not_enough,
                Snackbar.LENGTH_SHORT,
            )
                .show()
            return
        }

        val number1 = number1Str.toDouble()
        val number2 = number2Str.toDouble()
        val operation = operationSpinner.selectedItem.toString()

        if (number2 == 0.0 && operation == "/") {
            Snackbar.make(
                view,
                R.string.incorrect_divide,
                Snackbar.LENGTH_SHORT,
            )
                .show()
            return
        } else if (number2 == 0.0 && operation == "%"){
            Snackbar.make(
                view,
                R.string.incorrect_modulo,
                Snackbar.LENGTH_SHORT,
            )
                .show()
            return
        }

        val result = when (operation) {
            "+" -> number1 + number2
            "-" -> number1 - number2
            "*" -> number1 * number2
            "/" -> number1 / number2
            "%" -> number1 % number2
            else -> throw IllegalArgumentException()
        }
        resultTextView.text = "$result"
    }



}