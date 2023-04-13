package com.example.powernapping.ViewModel

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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
    /*private val _napProgress = MutableLiveData<Float>()
    val napProgressBar: LiveData<Float>
        get() = _napProgress*/

    //Progress bar is running or not
    private val _isRunning = MutableLiveData<Boolean>()
    val isPlaying: MutableLiveData<Boolean>
        get() = _isRunning

    fun set123(duration: Int) {             //workaround necessary (reason not explainable)
        _napTimeTotal.value = duration.toLong()
    }






}