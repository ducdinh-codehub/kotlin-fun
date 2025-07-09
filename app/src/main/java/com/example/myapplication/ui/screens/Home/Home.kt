package com.example.myapplication.ui.screens.Home

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.myapplication.ui.InAppScreen
import androidx.compose.runtime.setValue

import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp


@Composable
fun Home(navHostController: NavHostController, modifier: Modifier= Modifier) {
    val startDestination = InAppScreen.Home
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

    Scaffold (
        modifier = modifier,
        bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                InAppScreen.entries.forEachIndexed { index, inAppScreen ->

                    NavigationBarItem(
                        selected = selectedDestination == index,
                        onClick = {
                            navHostController.navigate(route = inAppScreen.name)
                            selectedDestination = index
                        },
                        label={
                            Text(inAppScreen.name)
                        },
                        icon = {
                            Icon(Icons.Rounded.DateRange, contentDescription = "Localized description", modifier = Modifier.size(30.dp))
                        },
                    )
                }
            }
        }
    ){
        innerPadding ->
        Text("THIS IS HOME SECTION")
    }
}