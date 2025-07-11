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
import com.example.myapplication.ui.shared.components.TextInput
import com.example.myapplication.ui.shared.context.auth.AuthModelView
import com.example.myapplication.ui.shared.context.auth.FirebaseAuthState
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

    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    fun setUsername(inputText: String) {
        username = inputText
    }

    fun setPassword(inputText: String) {
        password = inputText
    }
    Scaffold { innerPadding ->
        Column(modifier = Modifier
            .width(getScreenWidth())
            .height(getScreenHeight().plus(100.dp)).background(monk01),) {

            Column(modifier = Modifier.offset(y=30.dp).width(getScreenWidth()).height(290.dp)) {
                LottieAnimation(composition=composition, progress= progress, modifier = Modifier.size(250.dp).align(Alignment.CenterHorizontally))
            }


            Column(modifier = Modifier.width(getScreenWidth() - 20.dp).height(390.dp).align(Alignment.CenterHorizontally))
            {
                Box(
                    modifier = Modifier.fillMaxSize()
                        .background(Teal200, shape = RoundedCornerShape(26.dp))
                        .padding(16.dp).align(Alignment.CenterHorizontally)
                ) {
                    Column() {
                        Text("Let Plant With Us !", fontSize = 25.sp, fontWeight = FontWeight.Bold)

                        Column(Modifier.padding(vertical = 15.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(15.dp)){
                            TextInput(modifier = Modifier
                                .width(350.dp)
                                .clip(
                                    RoundedCornerShape(25.dp)
                                ), label = "Username", placeholder = "Enter Username", onTyping = {e -> setUsername(e)}, notAllowEmpty = true)
                            TextInput(modifier = Modifier
                                .width(350.dp)
                                .clip(
                                    RoundedCornerShape(25.dp)
                                ), label = "Password", placeholder = "Enter Password", onTyping = {e -> setPassword(e)}, isPasswordField = true, notAllowEmpty = true)

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(35.dp)
                            ){
                                NumberInput(modifier = Modifier
                                    .width(258.dp)
                                    .clip(
                                        RoundedCornerShape(25.dp)
                                    ), label = "Age")
                                IconButton(
                                    onClick = { openCalendarState = true },

                                    ) {
                                    Icon(Icons.Rounded.DateRange, contentDescription = "Localized description", modifier = Modifier.size(30.dp))
                                }
                            }

                            Button(onClick = {
                                if(username.isNotEmpty() && password.isNotEmpty()){
                                    authModelView.signUp(username, password);
                                }

                            }, colors = ButtonDefaults.buttonColors(containerColor = black), modifier = Modifier.width(500.dp)){
                                Text("Sign up", fontSize = 17.sp)
                            }

                            Text("I already had account, back to login screen.", modifier = Modifier.clickable { navHostController.popBackStack() }, textDecoration = TextDecoration.Underline)
                        }

                    }

                }
            }

            DatePickerModal({}, onDismiss = {changeStateOpenCalendar()}, openCalendarState)


        }
    }
}