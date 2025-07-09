package com.example.myapplication.ui.shared.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun TextInput(inputValue:String = "", label: String= "Default-text-input", placeholder: String = "Enter-text", modifier: Modifier = Modifier, modifierInlineTextStyle: Modifier = Modifier, isPasswordField: Boolean = false, isReadOnly:Boolean = false) {
    var enteredText by remember { mutableStateOf("") }// Declare a mutable state for the text
    var maskPasswordText by remember { mutableStateOf("") }// Declare a mutable state for the text

    if(isPasswordField){
        TextField(
            value = enteredText,
            label={ Text(text=label, modifier= Modifier.width(550.dp)) },
            onValueChange = { newText ->

                enteredText = newText

                println("enteredText"+enteredText)

            },
            placeholder = { Text(placeholder) },
            modifier = modifier,
            readOnly=isReadOnly,
            visualTransformation = PasswordVisualTransformation(),
            //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
    }else{
        TextField(
            value = enteredText,
            label={ Text(text=label, modifier= Modifier.width(550.dp)) },
            onValueChange = { newText ->
                enteredText = newText
            },
            placeholder = { Text(placeholder) },
            modifier = modifier,
            readOnly=isReadOnly,
            )
    }

}
