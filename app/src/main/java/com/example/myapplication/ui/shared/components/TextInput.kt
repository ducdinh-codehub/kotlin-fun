package com.example.myapplication.ui.shared.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.R
import com.example.myapplication.ui.theme.Grey
import com.example.myapplication.ui.theme.Grey200
import com.example.myapplication.ui.theme.Grey400
import com.example.myapplication.ui.theme.Grey50
import com.example.myapplication.ui.theme.Grey500
import com.example.myapplication.ui.theme.Grey600
import com.example.myapplication.ui.theme.Red

@Composable
fun TextInput(inputValue:String = "",
              label: String= "Default-text-input",
              placeholder: String = "Enter-text",
              modifier: Modifier = Modifier,
              modifierInlineTextStyle: Modifier = Modifier,
              isPasswordField: Boolean = false,
              isReadOnly:Boolean = false,
              onTyping : (inputText: String) -> Unit,
              notAllowEmpty: Boolean = false,
              isFailToLogin: MutableLiveData<Boolean> = MutableLiveData(false)
) {
    var enteredText by remember { mutableStateOf("") }// Declare a mutable state for the text
    var passwordVisible by remember { mutableStateOf(false) }
    if(isPasswordField){
        TextField(
            maxLines = 1,
            value = enteredText,
            label={ Text(text=label, modifier= Modifier.width(550.dp), color = if(notAllowEmpty && isFailToLogin.value) Red else Grey600) },
            onValueChange = { newText ->
                enteredText = newText
                println("enteredText"+enteredText)
                onTyping(newText)

            },
            placeholder = { Text(placeholder, color = if(notAllowEmpty && isFailToLogin.value) Red else Grey600) },
            modifier = modifier,
            readOnly=isReadOnly,
            visualTransformation = if(!passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "") },
            trailingIcon = {
                val painter = if(!passwordVisible) painterResource(id = R.drawable.visibility) else painterResource(id = R.drawable.unvisibility) // Replace 'your_image_name' with your PNG file name

                Icon(
                    painter=painter,
                    contentDescription = "",
                    modifier = Modifier.size(25.dp).clickable { passwordVisible = !passwordVisible }
                )
            }
        )
    }else{
        TextField(
            maxLines = 1,
            value = enteredText,
            label={ Text(text=label, modifier= Modifier.width(550.dp), color = if(notAllowEmpty && isFailToLogin.value) Red else Grey600) },
            onValueChange = { newText ->
                enteredText = newText
                onTyping(newText)
            },
            placeholder = { Text(placeholder, color = if(notAllowEmpty && isFailToLogin.value) Red else Grey600) },
            modifier = modifier,
            readOnly=isReadOnly,
            leadingIcon = { Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "") }

        )
    }

}
