package com.busanit.ch10_fragment.manager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.busanit.ch10_fragment.databinding.FragmentFristBinding
import com.busanit.ch10_fragment.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSecondBinding.inflate(inflater,
            container, false)
        return binding.root
    }
}