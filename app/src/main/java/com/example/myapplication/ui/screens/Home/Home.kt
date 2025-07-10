package com.example.myapplication.ui.screens.Home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.compose.runtime.setValue

import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.AppScreen
import com.example.myapplication.ui.shared.context.auth.AuthModelView
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Home(navHostController: NavHostController, authModelView: AuthModelView) {
    val startDestination = AppScreen.Home
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    Scaffold { innerPadding ->

        Column {
            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    ModalDrawerSheet {
                        AppScreen.entries.forEachIndexed { index, inAppScreen ->
                            if(inAppScreen.name !== "Login"){
                                NavigationDrawerItem(
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
                }
            ) {
                Icon(Icons.Default.Menu, contentDescription = "Localized description", modifier = Modifier.size(30.dp).clickable { scope.launch { drawerState.apply { if(isClosed) open() else close() } } })
            }
            Button(
                onClick = {
                    authModelView.setAppAuthState();
                }
            ) { Text("Sign out") }
        }

    }


}