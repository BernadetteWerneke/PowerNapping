package com.example.powernapping.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private const val TIME_FORMAT = "%02d:%02d"
class TimerViewModel: ViewModel() {

    //selected time duration
    private var _napTimeTotal = MutableLiveData<Long>(0)
    val napTimeTotal: LiveData<Long>
        get() = _napTimeTotal

    //progress of progress bar
    private val _progress = MutableLiveData<Float>()
    val progress: LiveData<Float>
        get() = _progress

    private val _isPlaying = MutableLiveData<Boolean>()
    val isPlaying: MutableLiveData<Boolean>
        get() = _isPlaying

    fun set123(duration: Int) {             //workaround
        _napTimeTotal.value = duration.toLong()
    }

    //formatiert ausgewählte Zeit in Minuten und Sekunden
    fun formatCountDownText(milliseconds: Long):String{
        val minutes = milliseconds / 1000 / 60
        val seconds = milliseconds / 1000 % 60
        //return "$minutes:$seconds"
        return TIME_FORMAT.format(minutes, seconds)
    }

    fun countDownThePickedTime(){}


    fun restartTimer(){}





}