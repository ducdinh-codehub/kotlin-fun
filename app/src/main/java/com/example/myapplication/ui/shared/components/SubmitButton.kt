package com.example.myapplication.ui.shared.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SubmitButton(label: String = "Default-content", onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(onClick = {
        onClick()
        println("Hello world !!!");
    },
        modifier = modifier) {
        Text("$label")
    }
}