package com.tutacademy.calculator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.notkamui.keval.Keval
import com.tutacademy.calculator.databinding.ActivityMainBinding
import kotlin.math.ceil
import kotlin.math.floor

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val operationsList = listOf('+', '-', '*', '/')

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.inputEt.keyListener = null
        binding.one.setOnClickListener {
            addNumber("1")
        }
        binding.two.setOnClickListener {
            addNumber("2")
        }
        binding.three.setOnClickListener {
            addNumber("3")
        }
        binding.four.setOnClickListener {
            addNumber("4")
        }
        binding.five.setOnClickListener {
            addNumber("5")
        }
        binding.six.setOnClickListener {
            addNumber("6")
        }
        binding.seven.setOnClickListener {
            addNumber("7")
        }
        binding.eight.setOnClickListener {
            addNumber("8")
        }
        binding.nine.setOnClickListener {
            addNumber("9")
        }
        binding.add.setOnClickListener {
            addOperation("+")
        }
        binding.subtract.setOnClickListener {
            addOperation("-")
        }
        binding.multiply.setOnClickListener {
            addOperation("*")
        }
        binding.divide.setOnClickListener {
            addOperation("/")
        }
        binding.equal.setOnClickListener {
            calculateExpression()
        }
        binding.Clean.setOnClickListener {
            binding.inputEt.text.clear()
        }
        binding.delete.setOnClickListener {
            if (binding.inputEt.text.isNotEmpty()) {
                val newExpression =
                    binding.inputEt.text.toString()
                        .dropLast(1)
                binding.inputEt.setText(newExpression)
            }
        }
        binding.brackets.setOnClickListener {
            val openBrackets = binding.inputEt.text.toString().count { it == '(' }
            val closedBrackets = binding.inputEt.text.toString().count { it == ')' }
            if (openBrackets > closedBrackets) {
                binding.inputEt.setText(binding.inputEt.text.toString() + ")")
            } else {
                binding.inputEt.setText(binding.inputEt.text.toString() + "(")
            }
        }
    }

    private fun addNumber(number: String) {
        binding.inputEt.setText(binding.inputEt.text.toString() + number)
    }

    private fun addOperation(operation: String) {
        val current = binding.inputEt.text.toString()

        if (current.isBlank() || current.last() == '(') {
            Toast.makeText(this, "Invalid format", Toast.LENGTH_SHORT).show()
            return
        }
        if (current.last() in operationsList) {
            binding.inputEt.setText(current.take(current.length - 1) + operation)
        } else {
            binding.inputEt.setText(current + operation)
        }


    }

    private fun calculateExpression() {
        try {
            val result = Keval.eval(binding.inputEt.text.toString())
            if (ceil(result) == floor(result))
                binding.inputEt.setText("${result.toInt()}")
            else binding.inputEt.setText("$result")
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}