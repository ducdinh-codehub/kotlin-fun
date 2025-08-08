package com.example.myapplication.ui.screens.Signup

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.myapplication.R
import com.example.myapplication.ui.AppScreen
import com.example.myapplication.ui.shared.api.SignupClient.createAccount
import com.example.myapplication.ui.shared.components.DatePickerModal
import com.example.myapplication.ui.shared.components.NumberInput
import com.example.myapplication.ui.shared.components.SubmitButton
import com.example.myapplication.ui.shared.components.TextInput
import com.example.myapplication.ui.shared.context.auth.AuthModelView
import com.example.myapplication.ui.shared.context.auth.FirebaseAuthState
import com.example.myapplication.ui.shared.dataModel.Account
import com.example.myapplication.ui.shared.dataModel.AccountModelView
import com.example.myapplication.ui.shared.utilizeFunctions.getScreenHeight
import com.example.myapplication.ui.shared.utilizeFunctions.getScreenWidth
import com.example.myapplication.ui.theme.Blue300
import com.example.myapplication.ui.theme.Red200
import com.example.myapplication.ui.theme.Teal100
import com.example.myapplication.ui.theme.Teal200
import com.example.myapplication.ui.theme.Teal300
import com.example.myapplication.ui.theme.Teal400
import com.example.myapplication.ui.theme.black
import com.example.myapplication.ui.theme.monk01
import com.example.myapplication.ui.theme.white
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.text.SimpleDateFormat
import java.util.Date


val default_login_image = "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"
@SuppressLint("SimpleDateFormat")
@Composable
fun Signup(navHostController: NavHostController, authModelView: AuthModelView){
    val firebaseAuthState = authModelView.firebaseAuthState.observeAsState();
    val context = LocalContext.current;
    LaunchedEffect(firebaseAuthState.value) {
        when(firebaseAuthState.value){
            is FirebaseAuthState.CreateAccountSuccess-> navHostController.popBackStack()
            is FirebaseAuthState.Error -> Toast.makeText(context, (firebaseAuthState.value as FirebaseAuthState.Error).message, Toast.LENGTH_SHORT).show();
            else -> Unit
        }
    }
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.plantwithus))
    val progress by animateLottieCompositionAsState(
        isPlaying = true,
        composition = composition,
        iterations = LottieConstants.IterateForever,
        speed = 0.7f
    )
    var openCalendarState by remember { mutableStateOf(false) }// Declare a mutable state for the text
    fun changeStateOpenCalendar(){
        openCalendarState = false;
    }
    val accountViewModel: AccountModelView = viewModel()
    val formState by accountViewModel.formObserver.observeAsState()
    fun setInitialAccountName(inputText: String) {
        accountViewModel.setName(inputText)
    }
    fun setInitialAccountPassword(inputText: String) {
        accountViewModel.setPassword(inputText)
    }
    var nextToSignUpSection by remember { mutableStateOf(false) }
    val isLoginSuccess : MutableLiveData<Boolean> = MutableLiveData(false);

    var scope = rememberCoroutineScope()

    Scaffold { innerPadding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .background(monk01), horizontalAlignment=Alignment.CenterHorizontally) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(15.dp)) {
                LottieAnimation(composition=composition, progress= progress, modifier = Modifier
                    .size(250.dp)
                    .align(Alignment.CenterHorizontally))
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally)
            {
                    Column(
                        Modifier
                            .background(Teal200, shape = RoundedCornerShape(26.dp))
                            .padding(35.dp), verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically), horizontalAlignment = Alignment.CenterHorizontally, ) {
                        Text("Let Plant With Us !", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                        Column( horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically)){
                            if(!nextToSignUpSection) {
                                TextInput(modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(
                                        RoundedCornerShape(25.dp)
                                    ),
                                    label = "Your first name",
                                    placeholder = "Enter Your first name",
                                    onTyping = {},
                                    notAllowEmpty = true,
                                    isFailToLogin = isLoginSuccess,
                                    onSignUpObserveViewModel = accountViewModel,
                                    savingModeIndex = 1,
                                    inputName = "name"
                                )
                                TextInput(modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(
                                        RoundedCornerShape(25.dp)
                                    ),
                                    label = "Your middle & last name",
                                    placeholder = "Enter Your middle & last name",
                                    onTyping = {},
                                    notAllowEmpty = true,
                                    isFailToLogin = isLoginSuccess,
                                    onSignUpObserveViewModel = accountViewModel,
                                    savingModeIndex = 1,
                                    inputName = "middleLastName"
                                )
                                TextInput(modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(
                                        RoundedCornerShape(25.dp)
                                    ),
                                    label = "Email",
                                    placeholder = "Enter Your middle & last name",
                                    onTyping = {},
                                    notAllowEmpty = true,
                                    isFailToLogin = isLoginSuccess,
                                    onSignUpObserveViewModel = accountViewModel,
                                    savingModeIndex = 1,
                                    inputName = "email"
                                )
                                TextInput(modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(
                                        RoundedCornerShape(25.dp)
                                    ),
                                    label = "Phone",
                                    placeholder = "Enter Your middle & last name",
                                    onTyping = {},
                                    notAllowEmpty = true,
                                    isFailToLogin = isLoginSuccess,
                                    onSignUpObserveViewModel = accountViewModel,
                                    savingModeIndex = 1,
                                    inputName = "phone"
                                )
                            }
                            if(nextToSignUpSection){
                                TextInput(modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(
                                        RoundedCornerShape(25.dp)
                                    ), label = "Your account",
                                    placeholder = "Enter account",
                                    onTyping = {},
                                    notAllowEmpty = true,
                                    isFailToLogin = isLoginSuccess,
                                    onSignUpObserveViewModel = accountViewModel,
                                    savingModeIndex = 1,
                                    inputName = "username")

                                TextInput(modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(
                                        RoundedCornerShape(25.dp)
                                    ), label = "Password",
                                    placeholder = "Enter Password",
                                    onTyping = {},
                                    isPasswordField = true,
                                    notAllowEmpty = true,
                                    isFailToLogin = isLoginSuccess,
                                    onSignUpObserveViewModel = accountViewModel,
                                    savingModeIndex = 1,
                                    inputName = "password")
                            }
                            if(!nextToSignUpSection) {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    NumberInput(
                                        inputValue = if(formState?.age !== null) formState?.age else 0,
                                        modifier = Modifier
                                            .width(220.dp)
                                            .clip(
                                                RoundedCornerShape(25.dp)
                                            ), label = "Age"
                                    )
                                    IconButton(
                                        onClick = { openCalendarState = true },

                                        ) {
                                        Icon(
                                            Icons.Rounded.DateRange,
                                            contentDescription = "Localized description",
                                            modifier = Modifier.size(25.dp)
                                        )
                                    }
                                }
                            }
                            if(nextToSignUpSection){
                                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround){
                                    Button(modifier = Modifier.width(120.dp), onClick = {
                                        nextToSignUpSection = false

                                    }, colors = ButtonDefaults.buttonColors(containerColor = black) ){
                                        Text("Prev")
                                    }
                                    Button(modifier = Modifier.width(120.dp), onClick = {
                                        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
                                        val currentDate = sdf.format(Date())
                                        accountViewModel.setFullName()
                                        accountViewModel.setCreatedAt(currentDate)
                                        accountViewModel.setUpdateAt(currentDate)
                                        accountViewModel.setImageAvatar(default_login_image)
                                        var response: String? = null
                                        scope.launch {
                                            response = createAccount(formState)
                                        }

                                    }, colors = ButtonDefaults.buttonColors(containerColor = black) ){
                                        Text("Sign up")
                                    }
                                }
                            }else{
                                Button(modifier = Modifier.width(250.dp), onClick = {
                                    //if(isLoginSuccess.value == true){
                                        nextToSignUpSection = true
                                    //}
                                }, colors = ButtonDefaults.buttonColors(containerColor = black) ){
                                    Text("Next")
                                }
                            }
                            Row(verticalAlignment = Alignment.CenterVertically){
                                Text("I already had account.", modifier = Modifier.clickable { navHostController.popBackStack() }, textDecoration = TextDecoration.Underline, fontWeight = FontWeight.Bold)

                            }
                        }
                    }
            }
            DatePickerModal( onDateSelected = {}, onDismiss = {changeStateOpenCalendar()}, openCalendarState, accountViewModel, 1)
        }
    }
}