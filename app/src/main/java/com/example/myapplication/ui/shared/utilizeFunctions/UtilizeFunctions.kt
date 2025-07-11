package com.example.myapplication.ui.shared.utilizeFunctions

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun getStatusBarHeight(): Dp {
    val configuration = LocalConfiguration.current;
    var statusBarHeightDp by remember { mutableStateOf(0.dp) }
    with(LocalDensity.current) {
        statusBarHeightDp = WindowInsets.statusBars.getTop(this).toDp()
    }
    return statusBarHeightDp;
}

@Composable
fun getScreenHeight(): Dp {
    val configuration = LocalConfiguration.current;
    var navBarHeightDp by remember { mutableStateOf(0.dp) }
    with(LocalDensity.current) {
        navBarHeightDp =  WindowInsets.navigationBars.getBottom(this).toDp()
    }
    return configuration.screenHeightDp.dp + navBarHeightDp;
}

@Composable
fun getScreenWidth(): Dp {
    val configuration = LocalConfiguration.current;
    return configuration.screenWidthDp.dp;
}