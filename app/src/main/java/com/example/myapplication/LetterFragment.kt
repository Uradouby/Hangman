package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableRow
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentLetterBinding

private const val TAG = "LetterFragment"

class LetterFragment : Fragment() {
    private var _binding: FragmentLetterBinding? = null
    private val displayViewModel:DisplayViewModel by activityViewModels()
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
            FragmentLetterBinding.inflate(layoutInflater, container, false)
        updateView()
        return binding.root
    }

    fun updateView()
    {
        binding.hint.text=displayViewModel.gethint()
        for (i in 0 until binding.alphabet.childCount)
        {
            var v:View=binding.alphabet.getChildAt(i);
            if (v is TableRow) {
                for (j in 0 until v.childCount) {
                    var b:View=v.getChildAt(j)
                    if (b is Button) {
                        b.isEnabled = !displayViewModel.isPressed(b.text.toString());
                        Log.d("aaa",b.text.toString()+b.isEnabled.toString())
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        for (i in 0 until binding.alphabet.childCount)
        {
            var v:View=binding.alphabet.getChildAt(i);
            if (v is TableRow) {
                for (j in 0 until v.childCount) {
                    var b:View=v.getChildAt(j)
                    if (b is Button)
                        b.setOnClickListener(){
                            setFragmentResult("requestButton", bundleOf("pressButton" to b.text.toString()))
                        }
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}