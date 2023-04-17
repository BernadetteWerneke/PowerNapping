package com.example.powernapping.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.powernapping.R
import com.example.powernapping.ViewModel.TimerViewModel
import com.example.powernapping.databinding.FragmentTimePickerBinding

class TimePickerFragment : Fragment() {

    // selected time
    var duration = 1

    private var _binding : FragmentTimePickerBinding? = null
    private val binding get() = _binding!!

    private val viewModel : TimerViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTimePickerBinding.inflate(inflater, container, false)
        val view = binding.root

        //setting min and max values
        binding.numPicker.maxValue = 60
        binding.numPicker.minValue = 1

        //time picker with spinner mode
        binding.numPicker.setOnValueChangedListener{
            numPicker, oldValue, newValue -> duration = newValue    //duration is selected time
        }

        //select time and then navigation back to TimerFragment
        binding.timepickerEndFab.setOnClickListener {
            viewModel.set123(duration*60000)        // save selected time (type INT -> milliseconds)
            //println(duration)                     // check: output in RUN should be selected time -> I/System.out: 15
            findNavController().navigate(R.id.action_timePickerFragment_to_timerFragment)
        }

        //shows selected time in time picker
        /*viewModel.napTimeTotal.observe(viewLifecycleOwner, Observer {
            binding.timepickerMaxTv.text = it.toString()
        })*/
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}