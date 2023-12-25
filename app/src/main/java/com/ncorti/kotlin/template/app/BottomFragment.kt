package com.ncorti.kotlin.template.app

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ncorti.kotlin.template.app.databinding.FragmentBottomBinding

class BottomFragment : Fragment() {
        private lateinit var binding: FragmentBottomBinding
        
        interface BottomFragmentListener {
            fun onButtonClick()
        }

        lateinit var activityCallback: BottomFragmentListener

        override fun onAttach(context: Context) {
            super.onAttach(context)
            activityCallback = context as BottomFragmentListener
        }

        override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            binding = FragmentBottomBinding.inflate(inflater, container, false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            binding.btnHabits.setOnClickListener {
                //Toast.makeText(context, "deneme", Toast.LENGTH_SHORT).show()
                activityCallback.onButtonClick()
            }
        }
    }