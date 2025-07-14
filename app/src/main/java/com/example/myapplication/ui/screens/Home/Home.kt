package com.example.myapplication.ui.screens.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.shared.components.Weather
import com.example.myapplication.ui.shared.utilizeFunctions.getScreenHeight
import com.example.myapplication.ui.shared.utilizeFunctions.getScreenWidth
import com.example.myapplication.ui.theme.Blue300
import com.example.myapplication.ui.theme.Blue400
import com.example.myapplication.ui.theme.Green100
import com.example.myapplication.ui.theme.Green50
import com.example.myapplication.ui.theme.LightGreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navHostController: NavHostController, authModelView: AuthModelView, topBarName: String, drawerState: DrawerState) {
    val startDestination = AppScreen.Home
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }
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
        Column(Modifier.width(getScreenWidth())
            .height(getScreenHeight()).background(Green50).padding(innerPadding).verticalScroll(
                rememberScrollState()
            )) {
            Column(Modifier.fillMaxWidth().height(450.dp).background(Blue300)) {
                Weather();
            }
            Column (Modifier.fillMaxWidth().height(800.dp).background(Blue400)) {

            }


        }

    }


}