package com.example.myapplication

import android.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.viewModels
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val displayViewModel:DisplayViewModel by viewModels()

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportFragmentManager
            .setFragmentResultListener("requestButton", this) { _, bundle ->
                val pressButton = bundle.getString("pressButton")
                if (pressButton != null) {
                    displayViewModel.press(pressButton)
                    binding.displayContainer.getFragment<DisplayFragment>().updateView()
                    binding.fragmentContainer.getFragment<LetterFragment>().updateView()
                    if (displayViewModel.success)
                    {
                        Toast.makeText(
                            binding.root.context,
                            "Congratuations! you win!",
                            Toast.LENGTH_SHORT
                        ).show()

                        Handler(Looper.getMainLooper()).postDelayed({
                            displayViewModel.renew()
                            binding.displayContainer.getFragment<DisplayFragment>().updateView()
                            binding.fragmentContainer.getFragment<LetterFragment>().updateView()
                        }, 1000)
                    }

                    if (displayViewModel.state==4)
                    {
                        Toast.makeText(
                            binding.root.context,
                            "Sorry! you lose...",
                            Toast.LENGTH_SHORT
                        ).show()

                        Handler(Looper.getMainLooper()).postDelayed({
                            displayViewModel.renew()
                            binding.displayContainer.getFragment<DisplayFragment>().updateView()
                            binding.fragmentContainer.getFragment<LetterFragment>().updateView()
                        }, 1000)
                    }

                }
            }
        setContentView(binding.root)
    }
}