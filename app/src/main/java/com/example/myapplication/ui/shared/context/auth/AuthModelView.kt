package com.example.myapplication.ui.shared.context.auth
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

sealed class FirebaseAuthState {
    object Authenticated : FirebaseAuthState();
    object Unauthenticated: FirebaseAuthState();
    object Loading: FirebaseAuthState();
    object CreateAccountSuccess: FirebaseAuthState();
    data class Error(val message: String): FirebaseAuthState();
}

class AuthModelView : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance();
    private val _firebaseAuthState = MutableLiveData<FirebaseAuthState>();
    val firebaseAuthState: LiveData<FirebaseAuthState> = _firebaseAuthState;


    private val _authState : MutableLiveData<Boolean> = MutableLiveData(false);
    val authState : LiveData<Boolean> = _authState;
    private val _userAccount : MutableLiveData<String> = MutableLiveData("");
    val userAccount : LiveData<String> = _userAccount;
    private val _userPassword : MutableLiveData<String> = MutableLiveData("");
    val userPassword : LiveData<String> = _userPassword;


    fun checkFirebaseEmailAuthStatus(){
        if(auth.currentUser == null){
            _firebaseAuthState.value = FirebaseAuthState.Unauthenticated;
        }else{
            _firebaseAuthState.value = FirebaseAuthState.Authenticated;
        }
    }

    fun getAppAuthState() : Boolean? {
        return this._authState.value;
    }

    fun setAppAuthState(currentState: Boolean) {
        this._authState.value = currentState;
    }

    fun getUserAccount() : String?{
        return this._userAccount.value;
    }

    fun login(userName: String,
              userPassword: String){
        if(userName.isNotEmpty() && userPassword.isNotEmpty()){
            val email = userName;
            val password = userPassword;

            // Storing Information
            this._userAccount.value = userName;
            this._userPassword.value = userPassword;

            this._firebaseAuthState.value = FirebaseAuthState.Loading;
            // Calling API TO AUTHENTICATE
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
                task ->
                if(task.isSuccessful){
                    this._firebaseAuthState.value = FirebaseAuthState.Authenticated;
                }else{
                    this._firebaseAuthState.value = FirebaseAuthState.Error(task.exception?.message?:" Error Authentication ");
                }
            }
        }else{
            this._firebaseAuthState.value = FirebaseAuthState.Error(" Email and password can't be empty ");
        }
    }

    fun signUp(
        userName: String,
        userPassword: String
    ){
        if(userName.isNotEmpty() && userPassword.isNotEmpty()){
            val email = userName;
            val password = userPassword;

            println("Create account " + email + " pass: " + password);

            // Storing Information
            this._userAccount.value = userName;
            this._userPassword.value = userPassword;

            this._firebaseAuthState.value = FirebaseAuthState.Loading;
            // Calling API TO AUTHENTICATE
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                    task ->
                if(task.isSuccessful){
                    this._firebaseAuthState.value = FirebaseAuthState.CreateAccountSuccess;
                }else{
                    this._firebaseAuthState.value = FirebaseAuthState.Error(task.exception?.message?:" Error Authentication ");
                }
            }
        }else{
            this._firebaseAuthState.value = FirebaseAuthState.Error(" Email and password can't be empty ");
        }
    }

    fun signOut(){
        this._firebaseAuthState.value = FirebaseAuthState.Unauthenticated;
        this.setAppAuthState(false);
        auth.signOut();
    }


}