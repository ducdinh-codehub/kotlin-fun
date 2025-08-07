package com.example.myapplication.ui.screens.Signup

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun Signup(navHostController: NavHostController, authModelView: AuthModelView){

    val firebaseAuthState = authModelView.firebaseAuthState.observeAsState();
    val context = LocalContext.current;
    LaunchedEffect(firebaseAuthState.value) {
        println("Firebase recomposition" + firebaseAuthState);
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

    val formState by accountViewModel.formObserver.collectAsState()


    fun setInitialAccountName(inputText: String) {
        accountViewModel.setName(inputText)
    }

    fun setInitialAccountPassword(inputText: String) {
        accountViewModel.setPassword(inputText)
    }

    var nextToSignUpSection by remember { mutableStateOf(false) }

    var isLoginSuccess : MutableLiveData<Boolean> = MutableLiveData(false);


    println("formState"+formState)




    Scaffold { innerPadding ->
        Column(modifier = Modifier.fillMaxSize().background(monk01), horizontalAlignment=Alignment.CenterHorizontally) {

            Column(modifier = Modifier.fillMaxWidth().height(200.dp).padding(15.dp)) {
                LottieAnimation(composition=composition, progress= progress, modifier = Modifier.size(250.dp).align(Alignment.CenterHorizontally))
            }


            Column(modifier = Modifier.fillMaxWidth().padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally)
            {

                    Column(Modifier.background(Teal200, shape = RoundedCornerShape(26.dp)).padding(35.dp), verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically), horizontalAlignment = Alignment.CenterHorizontally, ) {
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
                                    onTyping = { e -> setInitialAccountName(e) },
                                    notAllowEmpty = true,
                                    isFailToLogin = isLoginSuccess

                                )

                                TextInput(modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(
                                        RoundedCornerShape(25.dp)
                                    ),
                                    label = "Your middle & last name",
                                    placeholder = "Enter Your middle & last name",
                                    onTyping = { e -> setInitialAccountName(e) },
                                    notAllowEmpty = true,
                                    isFailToLogin = isLoginSuccess
                                )

                                TextInput(modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(
                                        RoundedCornerShape(25.dp)
                                    ),
                                    label = "Email",
                                    placeholder = "Enter Your middle & last name",
                                    onTyping = { e -> setInitialAccountUsername(e) },
                                    notAllowEmpty = true,
                                    isFailToLogin = isLoginSuccess
                                )

                                TextInput(modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(
                                        RoundedCornerShape(25.dp)
                                    ),
                                    label = "Phone",
                                    placeholder = "Enter Your middle & last name",
                                    onTyping = { e -> setInitialAccountUsername(e) },
                                    notAllowEmpty = true,
                                    isFailToLogin = isLoginSuccess
                                )
                            }
                            if(nextToSignUpSection){
                                TextInput(modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(
                                        RoundedCornerShape(25.dp)
                                    ), label = "Your account", placeholder = "Enter account", onTyping = {e -> setInitialAccountPassword(e)}, notAllowEmpty = true, isFailToLogin = isLoginSuccess)

                                TextInput(modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(
                                        RoundedCornerShape(25.dp)
                                    ), label = "Password", placeholder = "Enter Password", onTyping = {e -> setInitialAccountPassword(e)}, isPasswordField = true, notAllowEmpty = true, isFailToLogin = isLoginSuccess)
                            }
                            if(!nextToSignUpSection) {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    NumberInput(
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

            DatePickerModal({}, onDismiss = {changeStateOpenCalendar()}, openCalendarState)


        }
    }
}