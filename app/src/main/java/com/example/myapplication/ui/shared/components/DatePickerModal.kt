package com.example.myapplication.ui.shared.components

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.shared.dataModel.AccountModelView
import java.util.Date
import java.util.Locale




@SuppressLint("SimpleDateFormat")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    onDateSelected: (Int?) -> Unit,
    onDismiss: () -> Unit,
    isOpen: Boolean = false,
    onSignUpObserveViewModel: AccountModelView = viewModel(),
    savingModeIndex: Int = 0
) {
    val savingModeCategory = listOf(
        "default-mode",
        "signup-mode",
        "login-mode"
    )
    fun convertMillisToDate(millis: Long?): Int?{
        val formatter = SimpleDateFormat("yyyy", Locale.getDefault())
        var a: Int? = null
        if(millis !== null){
            a = formatter.format(Date(millis)).toInt()
        }
        return a
    }

    val datePickerState = rememberDatePickerState()
    if(isOpen) {
        DatePickerDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(onClick = {
                    if(savingModeCategory[savingModeIndex] === savingModeCategory[1]){
                        val selectedYear = convertMillisToDate(datePickerState.selectedDateMillis)
                        val sdf = java.text.SimpleDateFormat("yyyy")
                        val currentDate = sdf.format(Date()).toInt()
                        var age = 0
                        if(selectedYear !== null){
                            age = currentDate - selectedYear
                        }
                        onSignUpObserveViewModel.setAge(age)
                        onDateSelected(selectedYear)
                    }
                    onDismiss()
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    onDismiss()
                }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}