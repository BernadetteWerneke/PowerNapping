package com.example.powernapping.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimerViewModel: ViewModel() {

    //selected nap time duration
    private var _napTimeTotal = MutableLiveData<Long>(0)
    val napTimeTotal: LiveData<Long>
        get() = _napTimeTotal

    fun set123(duration: Int) {             //workaround
        _napTimeTotal.value = duration.toLong()
    }

    fun formatCountDownText(milliseconds: Long):String{
        val minutes = milliseconds / 1000 / 60
        val seconds = milliseconds / 1000 % 60
        return "$minutes:$seconds"
    }


}