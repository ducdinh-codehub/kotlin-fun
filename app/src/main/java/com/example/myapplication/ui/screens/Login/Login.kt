package com.example.myapplication.ui.screens.Login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myapplication.ui.shared.components.DatePickerModal
import com.example.myapplication.ui.shared.components.NumberInput
import com.example.myapplication.ui.shared.components.SubmitButton
import com.example.myapplication.ui.shared.components.TextInput
import com.example.myapplication.ui.shared.context.auth.AuthModelView
import com.example.myapplication.ui.theme.Green400
import com.example.myapplication.ui.theme.white


@Composable
fun Login(navController: NavHostController, onLogin: () -> Unit) {
    var openCalendarState by remember { mutableStateOf(false) }// Declare a mutable state for the text

    val username = remember {
        mutableStateOf("")
    }


    val password = remember {
        mutableStateOf("")
    }

    fun changeStateOpenCalendar(){
        openCalendarState = false;
    }


    Column(
        modifier = Modifier
            .background(Green400)
            .width(500.dp)
            .height(550.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(25.dp, Alignment.CenterVertically),


        ) {
        Text("Welcome, searching for solution !", color = white, fontSize = 23.sp)
        TextInput(modifier = Modifier
            .width(350.dp)
            .clip(
                RoundedCornerShape(25.dp)
            ), label = "Username", placeholder = "Enter Username")
        TextInput(modifier = Modifier
            .width(350.dp)
            .clip(
                RoundedCornerShape(25.dp)
            ), label = "Password", placeholder = "Enter Password", isPasswordField = true, isReadOnly = true)

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

        Row(
            horizontalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            Button(onClick = { onLogin() } ){
                Text("Login")
            }
            SubmitButton(label = "Sign Up", onClick = {})
        }

        DatePickerModal({}, onDismiss = {changeStateOpenCalendar()}, openCalendarState)


    }
}