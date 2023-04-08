package com.example.powernapping.ui

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.powernapping.R
import com.example.powernapping.ViewModel.TimerViewModel
import com.example.powernapping.databinding.FragmentTimerBinding

class TimerFragment : Fragment() {

    private var _binding: FragmentTimerBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TimerViewModel by activityViewModels()

    //timer settings
    //private var napTimerDuration: Long = viewModel.napTimeTotal.value!! // =>napTimeTotal//nap duration selected
    //private var napTimerDuration: Long = 0L
    private var napTimer: CountDownTimer? = null
    private var napProgressBar = 0
    private var mTimerRunning: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTimerBinding.inflate(inflater, container, false)
        val view = binding.root

        //show and set timer with time and progress bar
        setUpView()

        return view
    }

    //-----------------------------------------------------
    private fun setUpView() {

        //set selected duration of napping
        var actualCountDownTime = viewModel.formatCountDownText(viewModel.napTimeTotal.value!!)
        binding.timerShowprogressText.text = actualCountDownTime

        //navigation to scrolling Time Picker
        binding.timerShowprogressText.setOnClickListener {
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
    fun setProgressBar() {
        binding.napProgressBar.progress = napProgressBar
        //napTimer = object : CountDownTimer(napTimerDuration * 1000, 1000) {
        napTimer = object : CountDownTimer(viewModel.napTimeTotal.value!!, 1000) {
            override fun onTick(p0: Long) {
                var actualCountDownTime = viewModel.formatCountDownText(viewModel.napTimeTotal.value!!)
                binding.timerShowprogressText.text = actualCountDownTime

                napProgressBar++
                binding.napProgressBar.progress = 100 - napProgressBar

                //val minutes = ((napTimerDuration / 1000) / 60).toString()
                //val seconds = ((napTimerDuration / 1000) % 60).toString()
                //binding.timerShowprogressText.text = (60 - napProgressBar).toString() //60sec
                //binding.timerShowprogressText.text = "$minutes:$seconds"
            }

            override fun onFinish() {
                binding.timerShowprogressText.text = "done"
                // TODO  Alarm start ( and press finish)
            }
        }.start()
        //timer is on
        mTimerRunning = true
        binding.timerRestartFab.setOnClickListener(){
            restartTimer()
        }
    }


    fun restartTimer(){}


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}