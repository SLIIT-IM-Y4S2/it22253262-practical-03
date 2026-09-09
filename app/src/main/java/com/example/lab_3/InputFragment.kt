package com.example.lab_3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class InputFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_input, container, false)

        val viewModel: CalculatorViewModel =
            ViewModelProvider(requireActivity())[CalculatorViewModel::class.java]

        val etNum1: EditText = rootView.findViewById(R.id.etNum1)
        val etNum2: EditText = rootView.findViewById(R.id.etNum2)
        val btnAdd: Button = rootView.findViewById(R.id.btnAdd)
        val btnSub: Button = rootView.findViewById(R.id.btnSub)
        val btnMul: Button = rootView.findViewById(R.id.btnMul)
        val btnDiv: Button = rootView.findViewById(R.id.btnDiv)

        fun calculateAndNavigate(operation: String) {
            val num1Str = etNum1.text.toString()
            val num2Str = etNum2.text.toString()

            if (num1Str.isNotEmpty() && num2Str.isNotEmpty()) {
                val num1 = num1Str.toDouble()
                val num2 = num2Str.toDouble()
                val result = when (operation) {
                    "+" -> num1 + num2
                    "-" -> num1 - num2
                    "*" -> num1 * num2
                    "/" -> {
                        if (num2 != 0.0) {
                            num1 / num2
                        } else {
                            Toast.makeText(context, getString(R.string.error_divide_by_zero), Toast.LENGTH_SHORT).show()
                            return
                        }
                    }
                    else -> 0.0
                }

                // Save to ViewModel and navigate
                viewModel.finalResult = result
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ResultFragment())
                    .commit()
            } else {
                Toast.makeText(context, getString(R.string.error_empty_inputs), Toast.LENGTH_SHORT).show()
            }
        }

        btnAdd.setOnClickListener { calculateAndNavigate("+") }
        btnSub.setOnClickListener { calculateAndNavigate("-") }
        btnMul.setOnClickListener { calculateAndNavigate("*") }
        btnDiv.setOnClickListener { calculateAndNavigate("/") }

        return rootView
    }
}