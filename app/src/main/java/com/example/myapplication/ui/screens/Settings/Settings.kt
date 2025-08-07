package com.example.myapplication.ui.screens.Settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
fun Settings(navHostController: NavHostController, authModelView: AuthModelView, topBarName: String, drawerState: DrawerState, modifier: Modifier) {
    val scope = rememberCoroutineScope()

    Scaffold{ innerPadding ->

        Column(Modifier.padding(innerPadding)) {

            Button(
                onClick = {
                    authModelView.signOut();
                }
            ) { Text("Sign out") }
        }

    }
}