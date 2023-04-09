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
    private var isPlaying: Boolean = false

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.napTimeTotal.observe(viewLifecycleOwner){
            //TODO
        }

        viewModel.progress.observe(viewLifecycleOwner){
            //TODO
        }

        viewModel.isPlaying.observe(viewLifecycleOwner){
            //TODO
        }


    }

    //-----------------------------------------------------
    private fun setUpView() {

        //set selected duration of napping
        var actualCountDownTime = viewModel.formatCountDownText(viewModel.napTimeTotal.value!!) //formats time from
        binding.timerShowprogressText.text = actualCountDownTime

        //navigation to Time Picker
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
        napTimer = object : CountDownTimer(viewModel.napTimeTotal.value!!, 1000) {  //set
            override fun onTick(p0: Long) {
                var actualCountDownTime = viewModel.formatCountDownText(viewModel.napTimeTotal.value!!)
                binding.timerShowprogressText.text = actualCountDownTime
                //viewModel.countDownThePickedTime()

                napProgressBar++
                binding.napProgressBar.progress = 100 - napProgressBar

                //binding.timerShowprogressText.text = (60 - napProgressBar).toString() //60sec
                //binding.timerShowprogressText.text = "$minutes:$seconds"
            }

            override fun onFinish() {
                binding.timerShowprogressText.text = "done"
                // TODO  Alarm start ( and press finish)
            }
        }.start()
        //timer is on
        isPlaying = true
        binding.timerRestartFab.setOnClickListener(){
            viewModel.restartTimer()
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}