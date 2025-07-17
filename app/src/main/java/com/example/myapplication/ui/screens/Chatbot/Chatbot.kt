package com.example.myapplication.ui.screens.Chatbot

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.ui.shared.context.auth.AuthModelView
import kotlinx.coroutines.launch
import androidx.compose.ui.unit.Density
import androidx.compose.ui.platform.LocalDensity
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlin.math.roundToInt


@SuppressLint("RememberReturnType")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chatbot(navHostController: NavHostController, authModelView: AuthModelView, topBarName: String, drawerState: DrawerState){
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
        topBar = {

            TopAppBar(modifier = Modifier.offset{offset}, title = {
                         Text("")
                    }, navigationIcon = {
                Icon(Icons.Default.Menu, contentDescription = "Localized description", modifier = Modifier.size(30.dp).clickable { scope.launch { drawerState.apply { if(isClosed) open() else close() } } })
            })
        }

    ) {
        innerPadding ->

    }
}