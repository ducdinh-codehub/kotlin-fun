package com.example.myapplication.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost

import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.screens.Home.Home
import com.example.myapplication.ui.screens.News.News
import com.example.myapplication.ui.screens.Settings.Settings
import com.example.myapplication.ui.shared.context.auth.AuthModelView

enum class AppScreen(val icon : ImageVector) {
    Login(Icons.Filled.AccountCircle),
    Home(Icons.Filled.Home),
    News(Icons.Filled.Notifications),
    Settings(Icons.Filled.Settings)
}

@Composable
fun Navigation(authModelView: AuthModelView){
    val navController = rememberNavController();
    val startDestination = AppScreen.Home
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }
    val userAcc = authModelView.getUserAccount();
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                AppScreen.entries.forEachIndexed { index, inAppScreen ->
                    if(inAppScreen.name !== "Login"){
                        NavigationBarItem(
                            selected = selectedDestination == index,
                            onClick = {
                                navController.navigate(route = inAppScreen.name)
                                selectedDestination = index
                            },
                            label={
                                Text(inAppScreen.name)
                            },
                            icon = {
                                Icon(inAppScreen.icon, contentDescription = "Localized description", modifier = Modifier.size(30.dp))
                            },
                        )
                    }

                }
            }
        }
    ){
        contentPadding ->



        NavHost(navController, startDestination.name, Modifier.padding(contentPadding)) {
            composable(route= AppScreen.Home.name){
                Home(navController, authModelView)
            }
            composable(route= AppScreen.Settings.name){
                Settings(navController)
            }
            composable(route= AppScreen.News.name){
                News(navController)
            }
        }

    }
}