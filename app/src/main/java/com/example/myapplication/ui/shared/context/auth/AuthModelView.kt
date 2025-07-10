package com.example.myapplication.ui.shared.context.auth
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class AuthModelView : ViewModel() {

    private val _authState : MutableLiveData<Boolean> = MutableLiveData(false);
    val authState : LiveData<Boolean> = _authState;
    private val _userAccount : MutableLiveData<String> = MutableLiveData("");
    val userAccount : LiveData<String> = _userAccount;
    private val _userPassword : MutableLiveData<String> = MutableLiveData("");
    val userPassword : LiveData<String> = _userPassword;

    fun getAppAuthState() : Boolean {
        return _authState.value;
    }

    fun setAppAuthState(){
        this._authState.value = false;
    }

    fun getUserAccount() : String{
        return _userAccount.value;
    }

    fun login(userName: String,
              userPassword: String){
        if(userName.isNotEmpty() && userPassword.isNotEmpty()){
            // Calling API TO AUTHENTICATE
            // Storing Information
            this._userAccount.value = userName;
            this._userPassword.value = userPassword;
            this._authState.value = true;
        }else{
            _authState.value = false;
        }
    }

    fun signIn(
        userName: String,
        userPassword: String
    ){

    }


}