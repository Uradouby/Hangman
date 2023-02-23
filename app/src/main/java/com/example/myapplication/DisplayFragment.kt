package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.databinding.FragmentDisplayBinding

class DisplayFragment : Fragment() {
    private val displayViewModel:DisplayViewModel by activityViewModels()

    private var _binding: FragmentDisplayBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentDisplayBinding.inflate(layoutInflater, container, false)
        updateView();
        return binding.root
    }

    fun updateView()
    {
        binding.userGuessLetters.text = displayViewModel.getword()
        when (displayViewModel.state)
        {
            0->binding.hangmanView.setImageResource(R.drawable.state0)
            1->binding.hangmanView.setImageResource(R.drawable.state1)
            2->binding.hangmanView.setImageResource(R.drawable.state2)
            3->binding.hangmanView.setImageResource(R.drawable.state3)
            4->binding.hangmanView.setImageResource(R.drawable.state4)
        }
    }
}