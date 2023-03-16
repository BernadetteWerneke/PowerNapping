package com.example.powernapping.ui

import android.os.Bundle
import android.os.CountDownTimer
import android.text.format.DateFormat.is24HourFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.powernapping.R
import com.example.powernapping.TimerViewModel
import com.example.powernapping.databinding.FragmentTimerBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlinx.android.synthetic.main.fragment_timer.*

class TimerFragment : Fragment() {

    private var _binding : FragmentTimerBinding? = null
    private val binding get() = _binding!!
    private var progr = 0

    //private val viewModel: TimerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTimerBinding.inflate(inflater, container, false)
        val view = binding.root

        //press button for check feeling -> navigate to check fragment
        binding.timerCheckButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_timerFragment_to_checkFragment)
        }

        //Material Time Picker
        binding.timerShowprogressText.setOnClickListener {
            openTimePicker()
        }

    //timer handling
        binding.timerShowprogressText.text = "0"

        binding.plusButton.setOnClickListener {
            if (progr <= 90) {
                progr += 10
                updateProgressBar()
            }

        }

        binding.minusButton.setOnClickListener {
            if (progr >= 10) {
                progr -= 10
                updateProgressBar()
            }
        }

        //viewModel.progr.observe(viewLifecycleOwner){
          //  binding.timerShowprogressText.text = viewModel.progr.value.toString()
        //}


        return view
    }

    private fun openTimePicker() {
        val isSystem24Hours = is24HourFormat(requireContext())
        val clockFormat = if(isSystem24Hours) TimeFormat.CLOCK_24H else TimeFormat.CLOCK_12H

        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(clockFormat)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Set Alarm")
            .build()
        picker.show(childFragmentManager, "TAG")

        //what is triggert?
        picker.addOnPositiveButtonClickListener {
            Log.d("TimerFragment", "POSITIVE")
            val h = picker.hour
            val min = picker.minute
            Log.d("TimerFragment", "$h:$min")
        }
        picker.addOnNegativeButtonClickListener {
            Log.d("TimerFragment", "NEGATIVE")
        }
        //only by press somewhere on screen outside of material time picker
        picker.addOnCancelListener {
            Log.d("TimerFragment", "CANCEL")
        }
        picker.addOnDismissListener{
            Log.d("TimerFragment", "DISMISS")
        }
    }

    private fun updateProgressBar(){
        progress_bar.progress = progr
        timer_showprogress_text.text = "$progr%"
    }

}