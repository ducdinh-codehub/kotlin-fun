package com.example.myapplication.ui.screens.News

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.ui.shared.context.auth.AuthModelView
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun News(navHostController: NavHostController, authModelView: AuthModelView, topBarName: String, drawerState: DrawerState) {
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(
                topBarName
            ) }, navigationIcon = {
                Icon(Icons.Default.Menu, contentDescription = "Localized description", modifier = Modifier.size(30.dp).clickable { scope.launch { drawerState.apply { if(isClosed) open() else close() } } })
            })
        }

    ) { innerPadding ->

        Column {

            Button(
                onClick = {
                    authModelView.signOut();
                }
            ) { Text("Sign out") }
        }

    }
}