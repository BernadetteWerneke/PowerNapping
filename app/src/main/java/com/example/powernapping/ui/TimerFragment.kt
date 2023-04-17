package com.example.powernapping.ui

import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.powernapping.R
import com.example.powernapping.ViewModel.TimerViewModel
import com.example.powernapping.databinding.FragmentTimerBinding

private const val TIME_FORMAT = "%02d:%02d"
class TimerFragment : Fragment() {

    private lateinit var mediaPlayer: MediaPlayer
    private var _binding: FragmentTimerBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TimerViewModel by activityViewModels()

    //timer settings
    private var napTimer: CountDownTimer? = null //starting time
    private var napProgressBar = 0               //progress
    private var alarmIsRunning: Boolean = false

    private var timeSelected: Long = 0L

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
    }

    //-----------------------------------------------------
    private fun setUpView() {

        //set selected duration of napping
        var actualCountDownTime = formatCountDownText(viewModel.napTimeTotal.value!!) //formats time
        binding.timerShowprogressText.text = actualCountDownTime                                //show time

        //navigation to Time Picker to set time
        binding.timerShowprogressText.setOnClickListener {
            if (alarmIsRunning){
                Toast.makeText(requireContext(), "First stop running than change time", Toast.LENGTH_SHORT).show()
            } else {
                findNavController().navigate(R.id.action_timerFragment_to_timePickerFragment)
            }
        }


        //navigation to check fragment
        binding.timerCheckButton.setOnClickListener {
            if(alarmIsRunning){
                Toast.makeText(requireContext(), "Check during nap not possible", Toast.LENGTH_SHORT).show()
            } else {
                findNavController().navigate(R.id.action_timerFragment_to_checkFragment)
            }
        }

        //start Timer with start button
        binding.timerPlayFab.setOnClickListener {
            if (binding.timerShowprogressText.text == "00:00") {
                Toast.makeText(requireContext(), "Enter Time Duration", Toast.LENGTH_SHORT).show()
            } else {
                if (!alarmIsRunning) {
                    startTimer()
                }
            }
        }

        //reset the timer
        binding.timerRestartFab.setOnClickListener{
            if(alarmIsRunning){
                napTimer?.cancel()
            }
            restartTimer()
        }
    }

    //------------------------------------------------------
    private fun startTimer() {
        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.minionwecker)
        //binding.napProgressBar.progress = napProgressBar
        timeSelected = viewModel.napTimeTotal.value!!                           //ausgewählte Zeit
        binding.timerShowprogressText.text = formatCountDownText(timeSelected)  //show time
        binding.napProgressBar.max  = (timeSelected / 1000).toInt()             //max. length of progressbar
        alarmIsRunning = true

        //napTimer = object : CountDownTimer(napTimerDuration * 1000, 1000) {
        napTimer = object : CountDownTimer(timeSelected, 1000) {  //set 1) selected time in millisec, 2) Intervall in millisec
            override fun onTick(p0: Long) {
                binding.timerShowprogressText.text = formatCountDownText(p0)
                napProgressBar++
                binding.timerShowprogressText.text = formatCountDownText(p0)
                binding.napProgressBar.progress = (p0 / 1000).toInt()
            }

            override fun onFinish() {
                binding.timerShowprogressText.text = "OFF"
                //if(mediaPlayer != null)
                startMediaPlayerAlarm()
                stopMediaPlayer()
                alarmIsRunning = false
            }
        }.start()
    }

    private fun stopMediaPlayer() {
        binding.timerOffBtn.setOnClickListener(){
            mediaPlayer.stop()
            alarmIsRunning = false
            binding.timerOffBtn.visibility = View.INVISIBLE
            binding.timerShowprogressText.visibility = View.VISIBLE
            binding.timerShowprogressText.text = "00:00"

        }
    }

    fun startMediaPlayerAlarm(){
        alarmIsRunning = true
        binding.timerOffBtn.visibility = View.VISIBLE
        binding.timerShowprogressText.visibility = View.INVISIBLE
        mediaPlayer.start()
    }

    //formatiert ausgewählte Zeit in Minuten und Sekunden
    fun formatCountDownText(milliseconds: Long):String{
        val minutes = milliseconds / 1000 / 60
        val seconds = milliseconds / 1000 % 60
        return TIME_FORMAT.format(minutes, seconds)
    }
    private fun restartTimer(){
        //timer stoppen
        napTimer?.cancel()
        binding.napProgressBar.progress = timeSelected.toInt()
        setUpView()
        alarmIsRunning = false

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}