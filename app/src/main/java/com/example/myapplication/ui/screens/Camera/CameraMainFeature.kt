package com.example.myapplication.ui.screens.Camera

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.myapplication.R
import com.example.myapplication.ui.AppScreen
import com.example.myapplication.ui.shared.context.auth.AuthModelView
import com.example.myapplication.ui.theme.Blue100
import com.example.myapplication.ui.theme.Blue200
import com.example.myapplication.ui.theme.Blue300
import com.example.myapplication.ui.theme.Green100
import com.example.myapplication.ui.theme.Green200
import com.example.myapplication.ui.theme.Green300
import com.example.myapplication.ui.theme.Grey100
import com.example.myapplication.ui.theme.Grey200
import com.example.myapplication.ui.theme.Grey300
import com.example.myapplication.ui.theme.Grey400
import com.example.myapplication.ui.theme.Grey50
import com.example.myapplication.ui.theme.Purple200
import com.example.myapplication.ui.theme.Purple300
import com.example.myapplication.ui.theme.Purple400
import com.example.myapplication.ui.theme.Purple500
import com.example.myapplication.ui.theme.Red200
import com.example.myapplication.ui.theme.monk01
import com.example.myapplication.ui.theme.monk04
import com.example.myapplication.ui.theme.offWhite10
import com.example.myapplication.ui.theme.offWhite100
import com.example.myapplication.ui.theme.offWhite200
import com.example.myapplication.ui.theme.offWhite300
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CameraMainFeature(navHostController: NavHostController, authModelView: AuthModelView, topBarName: String, drawerState: DrawerState){
    val scope = rememberCoroutineScope()

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.plantdetect2)) // Replace with your file
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
    )

    val composition2 by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.plantdetect3)) // Replace with your file
    val progress2 by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
    )

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(
                "Smart detect"
            ) }, navigationIcon = {
                Icon(Icons.Default.Menu, contentDescription = "Localized description", modifier = Modifier.size(30.dp).clickable { scope.launch { drawerState.apply { if(isClosed) open() else close() } } })
            })
        }

    ){
        innerPadding ->
        Column(Modifier.padding(innerPadding).fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Column(Modifier.fillMaxSize().padding(5.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {

                Column(Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 25.dp)) {
                    Box(Modifier.background(color = Grey100, shape = RoundedCornerShape(15.dp)).padding(5.dp).fillMaxWidth()) {
                        Row(Modifier.padding(5.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Absolute.SpaceAround) {
                            Icon(
                                painterResource(R.drawable.mobile_camera_24dp), contentDescription = "Localized description", Modifier.background(
                                    Grey50
                                ))
                            Text("Open camera and let us help you ", fontSize = 17.sp, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold)
                        }
                    }
                }


                Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.height(250.dp).fillMaxWidth()) {
                    Column(Modifier.background(color = Blue100, shape = RoundedCornerShape(15.dp)).width(170.dp).fillMaxHeight().padding(15.dp).clickable { navHostController.navigate(AppScreen.Camera.name) }, horizontalAlignment=Alignment.CenterHorizontally) {
                        Text("Smart plant image searching", fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold)
                        LottieAnimation(composition=composition, progress= progress, modifier = Modifier.size(350.dp).align(Alignment.CenterHorizontally))
                    }
                    Column(Modifier.background(color = Green100, shape = RoundedCornerShape(15.dp)).width(170.dp).fillMaxHeight().padding(15.dp).clickable { navHostController.navigate(AppScreen.Camera.name) }, horizontalAlignment=Alignment.CenterHorizontally) {
                            Text("Diagnose disease", fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold)
                        LottieAnimation(composition=composition2, progress= progress2, modifier = Modifier.size(350.dp).align(Alignment.CenterHorizontally))
                    }
                }

                Column(Modifier.fillMaxWidth().padding(15.dp), verticalArrangement = Arrangement.spacedBy(15.dp)) {
                    Text("Related question", fontSize = 17.sp, fontFamily = FontFamily.SansSerif, color = Grey300, fontWeight = FontWeight.Bold)

                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround){
                        Box(Modifier.background(color = Green100, shape = CircleShape).size(45.dp).padding(15.dp).align(Alignment.CenterVertically)){
                            Icon(
                                painter = painterResource(id = R.drawable.psychology_alt_24dp),
                                contentDescription = "",
                                modifier = Modifier.size(45.dp), // Icon size is half the button size
                                tint = Purple300 // Optional: Customize icon color
                            )
                        }
                        Box(Modifier.background(color = Grey100, shape = RoundedCornerShape(15.dp)).padding(7.dp).width(250.dp).align(Alignment.CenterVertically)){
                            Text("Why my crop leaf has a lot of black dot ?", fontFamily = FontFamily.SansSerif, fontSize = 12.sp)
                        }
                    }

                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround){
                        Box(Modifier.background(color = Green100, shape = CircleShape).size(45.dp).padding(15.dp).align(Alignment.CenterVertically)){
                            Icon(
                                painter = painterResource(id = R.drawable.psychology_alt_24dp),
                                contentDescription = "",
                                modifier = Modifier.size(45.dp), // Icon size is half the button size
                                tint = Purple300 // Optional: Customize icon color
                            )
                        }
                        Box(Modifier.background(color = Grey100, shape = RoundedCornerShape(15.dp)).padding(7.dp).width(250.dp).align(Alignment.CenterVertically)){
                            Text("Why my crop leaf is yellow after raining day ?", fontFamily = FontFamily.SansSerif, fontSize = 12.sp)
                        }
                    }

                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround){
                        Box(Modifier.background(color = Green100, shape = CircleShape).size(45.dp).padding(15.dp).align(Alignment.CenterVertically)){
                            Icon(
                                painter = painterResource(id = R.drawable.psychology_alt_24dp),
                                contentDescription = "",
                                modifier = Modifier.size(45.dp), // Icon size is half the button size
                                tint = Purple300 // Optional: Customize icon color
                            )
                        }
                        Box(Modifier.background(color = Grey100, shape = RoundedCornerShape(15.dp)).padding(7.dp).width(250.dp).align(Alignment.CenterVertically)){
                            Text("I got this plant when go around what is it ?", fontFamily = FontFamily.SansSerif, fontSize = 12.sp)
                        }
                    }

                }


            }

        }

    }
}