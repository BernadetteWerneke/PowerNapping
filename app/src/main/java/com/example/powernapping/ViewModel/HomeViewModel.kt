package com.example.powernapping.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.powernapping.data.model.Category


class HomeViewModel: ViewModel() {

    //prepare testing
    private val _categoryList = MutableLiveData<List<Category>>()
    val categoryList: LiveData<List<Category>>
        get() = _categoryList

    //TODO Functionalities fun xy kategoriern holen, genre auswählen und einlesen, genre laden, musik abspielen



}