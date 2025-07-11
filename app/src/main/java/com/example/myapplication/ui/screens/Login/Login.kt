package com.example.myapplication.ui.screens.Login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
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
import com.example.myapplication.ui.shared.utilizeFunctions.getStatusBarHeight
import com.example.myapplication.ui.theme.Green300
import com.example.myapplication.ui.theme.black
import com.example.myapplication.ui.theme.monk01

@Composable
fun Login(navHostController: NavHostController, authModelView: AuthModelView) {

    val firebaseAuthState = authModelView.firebaseAuthState.observeAsState();
    val context = LocalContext.current;

    LaunchedEffect(firebaseAuthState.value) {

        println("Firebase recomposition" + firebaseAuthState.value);
        when(firebaseAuthState.value){
            is FirebaseAuthState.Authenticated -> authModelView.setAppAuthState(true);
            is FirebaseAuthState.Error -> Toast.makeText(context, (firebaseAuthState.value as FirebaseAuthState.Error).message, Toast.LENGTH_SHORT).show();
            else -> Unit
        }

    }

    var openCalendarState by remember { mutableStateOf(false) }// Declare a mutable state for the text

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loginanimation))
    val progress by animateLottieCompositionAsState(
        isPlaying = true,
        composition = composition,
        iterations = LottieConstants.IterateForever,
        speed = 0.7f
    )

    var isLoginSuccess : MutableLiveData<Boolean> = MutableLiveData(false);
    println("isLoginSuccess"+isLoginSuccess.value.toString());

    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    fun changeStateOpenCalendar(){
        openCalendarState = false;
    }

    fun setUsername(inputText: String) {
        username = inputText
    }

    fun setPassword(inputText: String) {
        password = inputText
    }


    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                modifier = Modifier
                    .width(getScreenWidth())
                    .height(getScreenHeight().plus(100.dp)).background(monk01),
                ) {
                Column(verticalArrangement = Arrangement.spacedBy(35.dp), horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.background(Green300).width(getScreenWidth()).height(525.dp).padding(vertical = getStatusBarHeight().plus(35.dp))){
                    Text("Welcome, searching for solution", fontSize = 23.sp, fontWeight = FontWeight.Bold)
                    TextInput(modifier = Modifier
                        .width(350.dp)
                        .clip(
                            RoundedCornerShape(25.dp)
                        ), label = "Username", placeholder = "Enter Username", onTyping = {e -> setUsername(e)}, notAllowEmpty = true, isFailToLogin = isLoginSuccess)
                    TextInput(modifier = Modifier
                        .width(350.dp)
                        .clip(
                            RoundedCornerShape(25.dp)
                        ), label = "Password", placeholder = "Enter Password", onTyping = {e -> setPassword(e)}, isPasswordField = true, notAllowEmpty = true, isFailToLogin = isLoginSuccess)

                    /*
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
                    }*/

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(25.dp)
                    ) {
                        Button(onClick = {
                            if(username.isNotEmpty() && password.isNotEmpty()){
                                authModelView.login(username, password)
                            }else{
                                isLoginSuccess.value = false
                            }
                        }, colors = ButtonDefaults.buttonColors(containerColor = black) ){
                            Text("Đăng nhập")
                        }
                        Button(onClick = {
                            navHostController.navigate(route = AppScreen.Signup.name)
                        }, colors = ButtonDefaults.buttonColors(containerColor = black)){
                            Text("Đăng ký")
                        }
                    }
                }

                //DatePickerModal({}, onDismiss = {changeStateOpenCalendar()}, openCalendarState)

                Column(
                    modifier = Modifier.width(getScreenWidth()).height(250.dp).offset(y = -90.dp)
                ){
                    LottieAnimation(composition=composition, progress= progress, modifier = Modifier.size(320.dp).align(Alignment.CenterHorizontally))
                }

                Column(modifier = Modifier.width(getScreenWidth()).height(250.dp)) {
                    Text("\uD83C\uDF3F\u200B Smart Agriculture Solutions \uD83C\uDF3F\u200B Diagnose Leaf Disease \uD83C\uDF3F\u200B Diagnose Plan Disease \uD83C\uDF3F\u200B Smart Agriculture Bot", modifier = Modifier.basicMarquee(
                    ), fontSize = 19.sp, fontWeight = FontWeight.Bold)
                }


            }


    }
}