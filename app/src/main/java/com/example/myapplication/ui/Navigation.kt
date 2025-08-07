package com.example.myapplication.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.ui.screens.Camera.Camera
import com.example.myapplication.ui.screens.Camera.CameraMainFeature
import com.example.myapplication.ui.screens.Chatbot.Chatbot
import com.example.myapplication.ui.screens.Chatbot.ChatbotMainfeature
import com.example.myapplication.ui.screens.Home.Home
import com.example.myapplication.ui.screens.Login.Login
import com.example.myapplication.ui.screens.News.News
import com.example.myapplication.ui.screens.Settings.Settings
import com.example.myapplication.ui.screens.Signup.Signup
import com.example.myapplication.ui.screens.SplashScreen.SplashScreen
import com.example.myapplication.ui.shared.context.auth.AuthModelView
import com.example.myapplication.ui.theme.Blue600
import com.example.myapplication.ui.theme.Grey100
import com.example.myapplication.ui.theme.Grey50
import kotlinx.coroutines.launch

enum class AppScreen(val icon : ImageVector) {
    Login(Icons.Filled.AccountCircle),
    Signup(Icons.Filled.AddCircle),
    Home(Icons.Filled.Home),
    News(Icons.Filled.Notifications),
    Settings(Icons.Filled.Settings),
    Splash(Icons.Filled.Notifications),
    Camera(Icons.Filled.Favorite),
    Smartbot(Icons.Filled.Face),
    SmartbotMainFeature(Icons.Filled.Face),
    CameraMainFeature(Icons.Filled.Favorite)
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.arguments?.getString(navController.currentBackStackEntry?.destination?.route)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(authModelView: AuthModelView, drawerState: DrawerState){
    val navController = rememberNavController();

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    var currentRoute by remember { mutableStateOf("Home") }

    LaunchedEffect(navBackStackEntry) {
        currentRoute = navBackStackEntry?.destination?.route.toString()
    }

    val startDestination = AppScreen.Home
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

    val isAuth : Boolean by authModelView.authState.observeAsState(false);
    val scope = rememberCoroutineScope();

    val bottomBarItemList = listOf(AppScreen.Home.name, AppScreen.News.name, AppScreen.Settings.name);
    val sideDrawerItemList = listOf(AppScreen.Home.name, AppScreen.News.name, AppScreen.SmartbotMainFeature.name, AppScreen.Settings.name,AppScreen.Signup.name)
    if(isAuth){
        Scaffold (
            Modifier.windowInsetsPadding(WindowInsets.safeDrawing).fillMaxSize(),
            topBar = {
                TopAppBar(title = {
                    if(currentRoute == AppScreen.SmartbotMainFeature.name){
                        Text("Smartbot")
                    }else{
                        Text(currentRoute)

                    }
                }, navigationIcon = {
                    Icon(Icons.Default.Menu, contentDescription = "Localized description", modifier = Modifier
                        .size(30.dp)
                        .clickable { scope.launch { drawerState.apply { if (isClosed) open() else close() } } })
                })
            },
            bottomBar = {
                if(currentRoute in bottomBarItemList){
                    NavigationBar(windowInsets = WindowInsets(0, 0, 0, 0), containerColor =  Grey50) {
                        AppScreen.entries.forEachIndexed { index, inAppScreen ->
                            if(inAppScreen.name in bottomBarItemList){
                                NavigationBarItem(
                                    selected = selectedDestination == index,
                                    onClick = {
                                        navController.navigate(route = inAppScreen.name)
                                        selectedDestination = index
                                    },
                                    label={
                                        if(inAppScreen.name === AppScreen.SmartbotMainFeature.name ) Text("Smartbot") else Text(inAppScreen.name)
                                    },
                                    icon = {
                                        Icon(inAppScreen.icon, contentDescription = "Localized description", modifier = Modifier.size(30.dp))
                                    },
                                )
                            }

                        }

                    }

                }
            }
        ){
            paddingValues ->
            ModalNavigationDrawer(
                modifier = Modifier.padding(paddingValues),
                drawerState = drawerState,
                drawerContent = {
                    ModalDrawerSheet {
                        AppScreen.entries.forEachIndexed { index, inAppScreen ->
                            if(inAppScreen.name in sideDrawerItemList){
                                NavigationDrawerItem(
                                    selected = selectedDestination == index,
                                    onClick = {
                                        navController.navigate(route = inAppScreen.name)
                                        selectedDestination = index
                                        scope.launch {
                                            drawerState.close();
                                        }
                                    },
                                    label={
                                        if(inAppScreen.name === AppScreen.SmartbotMainFeature.name) Text("Smartbot") else Text(inAppScreen.name)
                                    },
                                    icon = {
                                        Icon(inAppScreen.icon, contentDescription = "Localized description", modifier = Modifier.size(30.dp))
                                    },
                                )
                            }

                        }
                        Box(Modifier.height(80.dp).padding(20.dp).clickable { authModelView.signOut() }) {
                            Text("Sign out", color = Blue600, fontSize = 19.sp)
                        }
                    }
                }
            ){
                NavHost(navController, startDestination.name) {
                    composable(route= AppScreen.Home.name){
                        Home(navController, authModelView, AppScreen.Home.name, drawerState, Modifier.padding(paddingValues))
                    }
                    composable(route= AppScreen.Settings.name){
                        Settings(navController, authModelView, AppScreen.Settings.name, drawerState, Modifier.padding(paddingValues))
                    }
                    composable(route= AppScreen.News.name){
                        News(navController, authModelView, AppScreen.News.name, drawerState, Modifier.padding(paddingValues))
                    }
                    composable(route = AppScreen.Camera.name){
                        Camera(navController, authModelView, AppScreen.Camera.name, drawerState, Modifier.padding(paddingValues))
                    }
                    composable(route = AppScreen.Smartbot.name){
                        Chatbot(navController, authModelView, AppScreen.Smartbot.name, drawerState, Modifier.padding(paddingValues))
                    }
                    composable(route = AppScreen.CameraMainFeature.name){
                        CameraMainFeature(navController, authModelView, AppScreen.CameraMainFeature.name, drawerState, Modifier.padding(paddingValues))
                    }
                    composable(route = AppScreen.SmartbotMainFeature.name) {
                        ChatbotMainfeature(navController, authModelView, "Smartbot", drawerState, Modifier.padding(paddingValues))

                    }
                }
            }
        }
    }else {
        println("LOGIN SECTION")
        Scaffold(
        ) { innerPadding ->
            NavHost(navController, AppScreen.Login.name) {
                composable(route = AppScreen.Login.name) {
                    Login(navController, authModelView, drawerState)
                }
                composable(route=AppScreen.Signup.name){
                    Signup(navController, authModelView)
                }
            }
        }
    }

}