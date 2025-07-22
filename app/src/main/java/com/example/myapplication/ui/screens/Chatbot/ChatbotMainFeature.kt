package com.example.myapplication.ui.screens.Chatbot

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.ui.shared.components.ChatbotIntro
import com.example.myapplication.ui.shared.context.auth.AuthModelView
import com.example.myapplication.ui.theme.Blue100
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatbotMainfeature(navHostController: NavHostController, authModelView: AuthModelView, topBarName: String, drawerState: DrawerState){
    val scope = rememberCoroutineScope()
    var menuIconMoveState by remember { mutableStateOf(false) }
    var titleScreenFadeIn by remember {
        mutableStateOf(false)
    }
    val dpValue: Dp = 310.dp
    val pxToMove = with(LocalDensity.current) {
        dpValue.toPx().roundToInt()
    }

    LaunchedEffect(navHostController.currentBackStackEntryAsState().value) {
        println("LOADED")
        menuIconMoveState = true

    }

    val offset by animateIntOffsetAsState(
        targetValue = if (menuIconMoveState) {
            val a = 1
            IntOffset(pxToMove, 0)
        } else {
            IntOffset.Zero
        },
        label = "offset",
        animationSpec = tween(
            durationMillis = 300,
            delayMillis = 1000,
            easing = LinearOutSlowInEasing
        )
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text("$topBarName") }, navigationIcon = {
                Icon(Icons.Default.Menu, contentDescription = "Localized description", modifier = Modifier.size(30.dp).clickable { scope.launch { drawerState.apply { if(isClosed) open() else close() } } })
            })
        }

    ) {
        innerPadding ->
        Column(Modifier.padding(innerPadding).fillMaxSize()) {
            Column(Modifier.padding(10.dp).fillMaxSize()){
                ChatbotIntro(navHostController)
            }
        }
    }
}