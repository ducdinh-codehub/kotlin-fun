package com.example.myapplication.ui.shared.components

import android.annotation.SuppressLint
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.ui.shared.viewModel.WeatherViewModel
import com.example.myapplication.ui.theme.Grey50
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader

import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.myapplication.ui.theme.Blue100
import com.example.myapplication.ui.theme.Blue200
import com.example.myapplication.ui.theme.Blue300
import com.example.myapplication.ui.theme.Blue50
import com.example.myapplication.ui.theme.Green50
import com.example.myapplication.ui.theme.Grey100
import com.example.myapplication.ui.theme.Red100
import com.example.myapplication.ui.theme.Red300
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Weather(){
    var _weatherData: HttpResponse? by remember { mutableStateOf<HttpResponse?>(null) }
    val context = LocalContext.current
    var textContent by remember { mutableStateOf<String>("") }
    val weatherDT: WeatherViewModel = viewModel();
    val _weatherDT = weatherDT.weather.observeAsState();
    val scope = rememberCoroutineScope();
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.rainstorm)) // Replace with your file
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
    )

    // Synchronous read (risky on main thread)
    scope.launch {
        try {
            val inputStream = context.assets.open("openweather.txt")
            val reader = BufferedReader(InputStreamReader(inputStream))
            val stringBuilder = StringBuilder()
            var line: String? = reader.readLine()
            while (line != null) {
                stringBuilder.append(line).append("\n")
                line = reader.readLine()
            }
            reader.close()
            textContent = stringBuilder.toString()
        } catch (e: Exception) {
            textContent = "Error reading file: ${e.message}"
        }

        val key = textContent.split("=")[1].trim().replace("\\n","");
        weatherDT.fetchWeater(city = "Hanoi", key=key);
    }

    Column(Modifier.fillMaxWidth().fillMaxHeight().background(Blue200).padding(8.dp), horizontalAlignment=Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)) {
        Column(Modifier.fillMaxWidth().height(225.dp).background(color = Color.White, shape = RoundedCornerShape(15.dp)).padding(10.dp), verticalArrangement = Arrangement.Center) {
            Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {
                Box(Modifier.background(color = Grey100, shape = RoundedCornerShape(15.dp)).padding(2.dp).width(100.dp),) {
                    Row(Modifier.padding(5.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Place, contentDescription = "Localized description", modifier = Modifier.size(30.dp))
                        Text("Hà Nội")
                    }
                }

                Box(Modifier.padding(top=2.dp, bottom=2.dp)){
                    Row(verticalAlignment = Alignment.CenterVertically){
                        Text("16°C", fontSize = 50.sp, fontWeight = FontWeight.Bold)
                        Text(" Thứ 3 | ngày 15/6/2025", fontSize = 12.sp)
                        LottieAnimation(composition=composition, progress= progress, modifier = Modifier.size(120.dp))

                    }
                }

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp),){
                    Box(Modifier.background(color = Grey100, shape = RoundedCornerShape(15.dp)).padding(2.dp),) {
                        Row(Modifier.padding(5.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                            Icon(painterResource(R.drawable.air_24dp), contentDescription = "Localized description", Modifier.background(
                                Grey50))
                            Text(" 2,4 Km/h")
                        }
                    }

                    Box(Modifier.background(color = Grey100, shape = RoundedCornerShape(15.dp)).padding(2.dp)) {
                        Row(Modifier.padding(5.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                            Icon(painterResource(R.drawable.water_voc_24dp), contentDescription = "Localized description", Modifier.background(
                                Grey50))
                            Text(" 72,5 %")
                        }
                    }

                    Box(Modifier.background(color = Grey100, shape = RoundedCornerShape(15.dp)).padding(2.dp)) {
                        Row(Modifier.padding(5.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                            Icon(painterResource(R.drawable.sunny_24dp), contentDescription = "Localized description", Modifier.background(
                                Grey50))
                            Text("Clouds")
                        }
                    }
                }
            }
        }
        Row(Modifier.fillMaxWidth().height(130.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(Modifier.width(235.dp).fillMaxHeight().background(Color.White, shape = RoundedCornerShape(15.dp)).padding(25.dp)) {
                Box(Modifier.background(color = Grey100, shape = RoundedCornerShape(15.dp)).padding(2.dp)) {
                    Row(Modifier.padding(5.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                        Icon(painterResource(R.drawable.rainy_24dp), contentDescription = "Localized description", Modifier.background(
                            Grey50))
                        Text("Preciptation")
                    }
                }
                Text("5,1 ml", fontSize = 25.sp, fontWeight = FontWeight.Bold)
            }
            Column(Modifier.fillMaxHeight().background(Color.White, shape = RoundedCornerShape(15.dp)).padding(25.dp)) {
                Box(Modifier.background(color = Grey100, shape = RoundedCornerShape(15.dp)).padding(2.dp),) {
                    Row(Modifier.padding(5.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                        Icon(painterResource(R.drawable.wind_power_24dp), contentDescription = "Localized description", Modifier.background(
                            Grey50))
                        Text("Wind")
                    }
                }
                Text("23 m/s", fontSize = 25.sp, fontWeight = FontWeight.Bold)
            }
        }

    }
}