package com.example.myapplication.ui.shared.components

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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.ui.AppScreen
import com.example.myapplication.ui.theme.Blue100
import com.example.myapplication.ui.theme.Blue200
import com.example.myapplication.ui.theme.Blue300
import com.example.myapplication.ui.theme.Blue50
import com.example.myapplication.ui.theme.Green50
import com.example.myapplication.ui.theme.Grey100
import com.example.myapplication.ui.theme.Grey200
import com.example.myapplication.ui.theme.Grey300
import com.example.myapplication.ui.theme.Grey50
import com.example.myapplication.ui.theme.Purple300
import com.example.myapplication.ui.theme.Red300
import com.example.myapplication.ui.theme.Teal100
import com.example.myapplication.ui.theme.Teal200

@Composable
fun ChatbotIntro(navHostController: NavHostController){

    val colorStops = arrayOf(
        0.8f to Teal200,
        0.1f to Blue100,
        //0.2f to Green100
    )

    val colorStops2 = arrayOf(
        0.7f to Teal200,
        0.1f to Blue100,
        //0.2f to Green100
    )

    val colorStops3 = arrayOf(
        0.9f to Teal200,
        0.1f to Blue100,
        //0.2f to Green100
    )

    val scrollState = rememberScrollState();

    Column(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("How can I help you today ?", fontSize = 15.sp, fontWeight = FontWeight.Bold)

            Row(Modifier.fillMaxWidth().height(180.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround) {
                Column(verticalArrangement =  Arrangement.SpaceBetween, modifier = Modifier.width(150.dp).fillMaxHeight().background(
                    Brush.linearGradient(colorStops = colorStops), shape = RoundedCornerShape(30f)).padding(10.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.mic_24dp),
                        contentDescription = "",
                        modifier = Modifier.size(22.dp), // Icon size is half the button size
                    )
                    Text("Talk with bot", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                }

                Column(verticalArrangement =  Arrangement.SpaceBetween, modifier = Modifier.width(150.dp).fillMaxHeight().background(
                    Brush.linearGradient(colorStops = colorStops2), shape = RoundedCornerShape(30f)
                ).padding(10.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.document_scanner_24dp),
                        contentDescription = "",
                        modifier = Modifier.size(22.dp), // Icon size is half the button size
                    )
                    Text("Ocr text", fontWeight =  FontWeight.Bold, fontSize = 15.sp)
                }



            }

            Column(Modifier.fillMaxWidth().padding(10.dp).height(100.dp)) {
                Column(Modifier.fillMaxWidth().fillMaxHeight().background(
                    Brush.linearGradient(colorStops = colorStops3), shape = RoundedCornerShape(30f)).padding(10.dp), verticalArrangement = Arrangement.SpaceBetween) {
                    Icon(
                        painter = painterResource(id = R.drawable.chat_24dp),
                        contentDescription = "",
                        modifier = Modifier.size(22.dp), // Icon size is half the button size
                    )
                    Text("Chat with bot", fontWeight =  FontWeight.Bold, fontSize = 15.sp)
                }
            }

            Text("Related chat", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Grey300)

            Column(Modifier.verticalScroll(scrollState, true).fillMaxWidth().padding(5.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                    Box(Modifier.background(color = Teal100, shape = CircleShape).size(30.dp).padding(7.dp)){
                        Icon(
                            painter = painterResource(id = R.drawable.chat_bubble_24dp),
                            contentDescription = "",
                        )
                    }
                    Text("What is the most famous crop in North side Vietnam ?", fontSize = 13.sp)
                }

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.background(color = Teal100, shape = CircleShape).size(30.dp).padding(7.dp)){
                        Icon(
                            painter = painterResource(id = R.drawable.chat_bubble_24dp),
                            contentDescription = "",
                        )
                    }
                    Text("Give me the way to protect my crop field on the storm season", fontSize = 13.sp)

                }

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.background(color = Teal100, shape = CircleShape).size(30.dp).padding(7.dp)){
                        Icon(
                            painter = painterResource(id = R.drawable.chat_bubble_24dp),
                            contentDescription = "",
                        )
                    }
                    Text("The rice yields in Vietnam 2025 ?", fontSize = 13.sp)


                }



            }
            Row(modifier = Modifier.fillMaxWidth().background(color= Grey200, shape = RoundedCornerShape(70f)).height(50.dp).padding(10.dp).clickable { navHostController.navigate( AppScreen.Smartbot.name ) }, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Start new chat", color = Color.Black, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                Box(Modifier.background(color = Teal100, shape = CircleShape).size(30.dp).padding(7.dp)){
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_right_alt_24dp),
                        contentDescription = "",
                    )
                }
            }

        }
    }
}