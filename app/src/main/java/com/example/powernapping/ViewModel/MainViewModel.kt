package com.example.powernapping.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

const val TAG = "MainViewModel"

/**
 * Dieses MAinViewModel kümmert sich um die Kommunikation mit der Firebase Authentication
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

    //Toast für Fehlermeldungen bei Einloggen
    private val _toast = MutableLiveData<String?>()
    val toast: LiveData<String?>
        get() = _toast


    //user erstellen, um anschließend gleich einzuloggen
    fun signUp(email: String, password:String){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
        //wenn User korrekt erstellt,dann einloggen
            .addOnCompleteListener {
                if (it.isSuccessful){
                    login(email, password)
                } else {
                    Log.e(TAG, "SignUp failed: ${it.exception?.message}")    //intern Fehlermeldung anzeigen
                    _toast.value = it.exception?.message                         //zusätzlich noch Fehlermeldung an User
                    _toast.value = null                                          //damit Toast nicht duarnd gefeurt wird
                }
            }
    }

    fun login(email: String, password: String){
        firebaseAuth.signInWithEmailAndPassword(email, password)    //eigentlich fertig
            .addOnCompleteListener {
                if (it.isSuccessful){
                    _currentUser.value = firebaseAuth.currentUser
                } else {
                    Log.e(TAG, "Login failed: ${it.exception?.message}")    //intern Fehlermeldung anzeigen
                    _toast.value = it.exception?.message                         //zusätzlich noch Fehlermeldung an User
                    _toast.value = null                                          //damit Toast nicht duarnd gefeurt wird
                }
            }
    }

    fun logout(){
        firebaseAuth.signOut()
        _currentUser.value = firebaseAuth.currentUser
    }

}