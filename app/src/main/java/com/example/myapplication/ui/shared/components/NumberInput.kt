package com.example.myapplication.ui.shared.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NumberInput(inputValue:Int = 0, label: String= "Default-number-input", placeholder: String = "Enter-number", modifier: Modifier = Modifier, modifierInlineTextStyle: Modifier = Modifier, isReadOnly: Boolean = false) {
    TextField(
        value = inputValue.toString(),
        label={ Text(text=label, modifier= Modifier.width(550.dp)) },
        onValueChange = { enterValue -> println(enterValue)},
        placeholder = { Text(placeholder) },
        modifier = modifier,
        readOnly = isReadOnly
    )
}