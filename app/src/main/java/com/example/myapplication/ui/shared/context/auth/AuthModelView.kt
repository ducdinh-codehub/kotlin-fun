package com.example.myapplication.ui.shared.context.auth
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class AuthModelView : ViewModel() {

    private val _authState : MutableLiveData<Boolean> = MutableLiveData(false);
    val authState : LiveData<Boolean> = _authState;
    private var userAccount = "";
    private var userPassword = "";

    fun getAppAuthState() : Boolean {
        return authState.value;
    }


    fun login(){
        println("START LOGIN")
        _authState.value = true;
    }

    fun signIn(

    ){

    }


}