package com.example.lab_3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class ResultFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_result, container, false)

        val viewModel: CalculatorViewModel =
            ViewModelProvider(requireActivity())[CalculatorViewModel::class.java]

        val tvAnswer: TextView = rootView.findViewById(R.id.tvAnswer)
        val btnBack: Button = rootView.findViewById(R.id.btnBack)

        // Set the calculated answer
        tvAnswer.text = getString(R.string.answer_format, viewModel.finalResult)

        // Handle back button navigation
        btnBack.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, InputFragment())
                .commit()
        }

        return rootView
    }
}