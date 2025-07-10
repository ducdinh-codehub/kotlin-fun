package com.example.myapplication
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.AppScreen
import com.example.myapplication.ui.screens.Home.Home
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.screens.Login.Login
import com.example.myapplication.ui.shared.components.DatePickerModal
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.shared.context.auth.AuthModelView
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import com.example.myapplication.ui.Navigation
import com.example.myapplication.ui.screens.News.News
import com.example.myapplication.ui.screens.Settings.Settings


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                    var authModelViewContext : AuthModelView = viewModel();
                    val isAuth : Boolean by authModelViewContext.authState.observeAsState(false);

                    if(isAuth){
                        println("LOGIN SUCCESS !!!")
                        Navigation(authModelViewContext);
                    }else{
                        Login(authModelViewContext)
                    }

            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF00FFF0, fontScale = 1.2F )
@Composable
fun TestComponent() {
    MyApplicationTheme {
        DatePickerModal(onDateSelected = {}, onDismiss = {})
    }
}