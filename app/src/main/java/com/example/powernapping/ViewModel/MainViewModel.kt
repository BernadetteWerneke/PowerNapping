package com.example.powernapping.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

const val TAG = "MainViewModel"

/**
 * Dieses HomeViewModel kümmert sich um die Kommunikation mit der Firebase Authentication
 * um einen SHA-1 Key zu generieren einfach folgende Zeilen ins Terminal kopieren
 * >>keytool -alias androiddebugkey -keystore ~/.android/debug.keystore -list -v -storepass android<<
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {

    //Kommunikationspunkt mit der Firebase
    private val firebaseAuth = FirebaseAuth.getInstance()

    //currentUser ist null, wenn niemand eingeloggt ist
    private val _currentUser = MutableLiveData<FirebaseUser?>(firebaseAuth.currentUser)
    val currentUser: LiveData<FirebaseUser?>
        get() = _currentUser

}