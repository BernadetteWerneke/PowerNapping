package com.example.powernapping.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimerViewModel: ViewModel() {

    //selected nap time duration
    private var _napTimeTotal = MutableLiveData<Int>(0)
    val napTimeTotal: LiveData<Int>
        get() = _napTimeTotal

    fun set123(duration: Int) {
        _napTimeTotal.value = duration
    }

}