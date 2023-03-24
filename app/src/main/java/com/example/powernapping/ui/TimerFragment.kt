package com.example.powernapping.ui

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.powernapping.R
import com.example.powernapping.ViewModel.TimerViewModel
import com.example.powernapping.databinding.FragmentTimerBinding

class TimerFragment : Fragment() {

    private var _binding: FragmentTimerBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TimerViewModel by activityViewModels()

    //timer settings
    private val napTimerDuration: Long = 60 // napTimeTotal        //nap duration selected
    private var napTimer: CountDownTimer? = null
    private var napProgress = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTimerBinding.inflate(inflater, container, false)
        val view = binding.root

        setUpView()

        return view
    }

    //-----------------------------------------------------
    private fun setUpView() {

        //set selected duration of napping
        binding.timerShowprogressText.text = viewModel.napTimeTotal.value.toString()

        //navigation to scrolling Time Picker
        binding.timerShowprogressText.setOnClickListener {
            findNavController().navigate(R.id.action_timerFragment_to_timePickerFragment)
        }
        binding.timerMinText.setOnClickListener {
            findNavController().navigate(R.id.action_timerFragment_to_timePickerFragment)
        }

        //navigation to check fragment
        binding.timerCheckButton.setOnClickListener {
            findNavController().navigate(R.id.action_timerFragment_to_checkFragment)
        }

        //start Timer with start button
        binding.timerPlayFab.setOnClickListener {
            setProgressBar()
        }
    }

    //------------------------------------------------------
    //ProgressBar update
    private fun setProgressBar() {
        binding.napProgressBar.progress = napProgress
        //time count down for 60 seconds (napTimerDuration * 60000)
        //with 1 second as countDown interval
        napTimer = object : CountDownTimer(napTimerDuration * 1000, 1000) {
            override fun onTick(p0: Long) {
                //update progress
                napProgress++
                binding.napProgressBar.progress = 100 - napProgress
                //Show remaining time (60 sec)
                //binding.timerShowprogressText.text = (60 - napProgress).toString()
                updateTextUI()

            }

            override fun onFinish() {
                binding.timerShowprogressText.text = "done"
                // TODO  Alarm start ( and press finish)
            }
        }.start()

        //Aktualisierung
        viewModel.napTimeTotal.observe(viewLifecycleOwner, Observer {
            binding.timerShowprogressText.text = it.toString()
        })
    }

        fun updateTextUI() {
            val minute = (napTimerDuration / 1000) / 60
            val seconds = (napTimerDuration / 1000) % 60
            binding.timerShowprogressText.text = "$minute:$seconds"
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
}