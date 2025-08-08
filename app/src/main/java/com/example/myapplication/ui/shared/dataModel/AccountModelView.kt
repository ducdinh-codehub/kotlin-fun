package com.example.myapplication.ui.shared.dataModel

import androidx.compose.runtime.remember
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class AccountModelView : ViewModel() {
    private val _formObserver =  MutableLiveData(Account(
        name = "",
        middleLastName = "",
        fullName = "",
        email = "",
        phone = "", // Can be null, e.g., phone = null
        age = 0,
        createdAt = "",
        updatedAt = "",
        imageAvatar = "",
        username = "",
        password = ""
    ))

    val formObserver : MutableLiveData<Account> = _formObserver

    fun setName(input: String){
        _formObserver.value = _formObserver.value.copy(name = input.lowercase())
    }

    fun setMiddleLastName(input: String){
        _formObserver.value = _formObserver.value.copy(middleLastName = input.lowercase())
    }

    fun setFullName(){
        _formObserver.value = _formObserver.value.copy(fullName = (formObserver.value.middleLastName + formObserver.value.name).lowercase())
    }

    fun setEmail(input: String){
        _formObserver.value = _formObserver.value.copy(email = input)
    }

    fun setPhone(input: String){
        _formObserver.value = _formObserver.value.copy(phone = input)
    }

    fun setAge(input: Int){
        _formObserver.value = _formObserver.value.copy(age = input)
    }

    fun setCreatedAt(input: String){
        _formObserver.value = _formObserver.value.copy(createdAt = input)
    }

    fun setUpdateAt(input: String){
        _formObserver.value = _formObserver.value.copy(updatedAt = input)
    }

    fun setImageAvatar(input: String){
        _formObserver.value = _formObserver.value.copy(imageAvatar = input)
    }

    fun setUsername(input: String){
        _formObserver.value = _formObserver.value.copy(username = input)
    }

    fun setPassword(input: String){
        _formObserver.value = _formObserver.value.copy(password = input)
    }

}