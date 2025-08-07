package com.example.myapplication.ui.screens.Chatbot

import android.annotation.SuppressLint
import android.view.ViewTreeObserver
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.ui.shared.context.auth.AuthModelView
import kotlinx.coroutines.launch
import androidx.compose.ui.unit.Density
import androidx.compose.ui.platform.LocalDensity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.ui.theme.Grey100
import com.example.myapplication.ui.theme.white
import kotlin.math.roundToInt
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import com.example.myapplication.ui.theme.Blue100
import com.example.myapplication.ui.theme.Grey300
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.myapplication.R
import com.example.myapplication.ui.shared.api.ChatbotClient
import com.example.myapplication.ui.theme.Blue50
import com.example.myapplication.ui.theme.Grey50
import com.example.myapplication.ui.theme.Teal100
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.input.ImeAction

import androidx.activity.enableEdgeToEdge




class InputMessage : ViewModel(){
    private val _senderMessage =  MutableLiveData<String>("From user");
    val senderMessage: LiveData<String> = _senderMessage;

    private val _botMessage = MutableLiveData<String>("From bot");
    val botMessage: LiveData<String> = _botMessage;

    fun setSenderMessage(input: String){
        _senderMessage.value = input
    }

    fun setBotMessage(input: String?){
        _botMessage.value = if(input!!.isNotEmpty()) input else null;
    }

}

val ListOfAllMessage = mutableListOf<InputMessage>()



@Composable
fun GetInputData(text: String) {
    Column{
        LazyColumn(Modifier.weight(1f)) {
            item {
                Text(text = "First item $text")
            }
        }
    }

}



val ListOfAllMessage2 = mutableListOf<String>("Item1", "Item2", "Item3")
enum class Keyboard {
    Opened, Closed
}

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun Chatbot(navHostController: NavHostController, authModelView: AuthModelView, topBarName: String, drawerState: DrawerState, modifier: Modifier){

    var listItemsOpen by remember { mutableStateOf(listOf<String>("Item1", "Item2", "Item3")) }

    var listMessage by remember { mutableStateOf(listOf<InputMessage>()) }

    var isLoadingBotResp by remember { mutableStateOf(false) }


    val scope = rememberCoroutineScope()
    var menuIconMoveState by remember { mutableStateOf(false) }
    var titleScreenFadeIn by remember {
        mutableStateOf(false)
    }
    val dpValue: Dp = 310.dp
    val pxToMove = with(LocalDensity.current) {
        dpValue.toPx().roundToInt()
    }

    var textValue by remember { mutableStateOf("") }

    println(textValue + "textValue")

    val verticalScrollState = rememberScrollState();

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

    val density = LocalDensity.current
    val keyboardHeight by rememberUpdatedState(
       WindowInsets.navigationBars.getBottom(density)
    )

    val brush = remember {
        Brush.linearGradient(
            colors = listOf(Color.Red, Color.Yellow, Color.Green, Color.Blue, Color.Magenta)
        )
    }

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.botthinking)) // Replace with your file
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
    )

    val keyboardState by remember { mutableStateOf(false) }

    Scaffold {
        innerPadding ->
        Column(Modifier.fillMaxSize().padding(innerPadding)) {
            Column(Modifier.fillMaxWidth().padding(5.dp)) {

                LazyColumn(Modifier.weight(1f).height(300.dp).imePadding()) {
                    items(listMessage) { item ->
                        Column(verticalArrangement = Arrangement.SpaceAround) {
                            Box(
                                modifier = Modifier.defaultMinSize(minHeight = 100.dp)
                                    .fillMaxWidth().padding(10.dp)
                            ) {
                                Box(
                                    modifier = Modifier.background(
                                        color = Blue50,
                                        shape = RoundedCornerShape(80f)
                                    ).fillMaxWidth().defaultMinSize(minHeight = 50.dp)
                                        .padding(12.dp)
                                ) {
                                    Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                                            modifier = Modifier.padding(2.dp)
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.user_attributes_24dp),
                                                contentDescription = "",
                                                Modifier.size(22.dp)
                                            )
                                            Text("You:")
                                        }
                                        Text(text = "${item.senderMessage.value}", fontSize = 18.sp)
                                    }
                                }
                            }

                            Box(Modifier.height(15.dp))

                            Box(
                                Modifier.fillMaxWidth().defaultMinSize(minHeight = 200.dp)
                                    .background(
                                        Grey100
                                    ).padding(15.dp)
                            ) {
                                Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                                        modifier = Modifier.padding(2.dp)
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.chat_apps_script_24dp),
                                            contentDescription = "",
                                            Modifier.size(22.dp)
                                        )
                                        Text("Bot:")
                                    }

                                    if (isLoadingBotResp) {
                                        LottieAnimation(
                                            composition = composition,
                                            progress = progress,
                                            modifier = Modifier.size(200.dp)
                                                .align(Alignment.CenterHorizontally)
                                        )
                                    } else {
                                        Text(item.botMessage.value)
                                    }

                                }

                            }
                        }
                    }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically){
                    BasicTextField(
                        value = textValue,
                        onValueChange = { textValue = it },
                        modifier = Modifier.width(320.dp).background(color= Grey300,
                            shape = RoundedCornerShape(200.dp) // Apply rounded corners
                        ).height(55.dp).padding(15.dp).align(Alignment.CenterVertically).imePadding(),
                        textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
                    )

                    Box(Modifier.background(color = Teal100, shape = CircleShape).size(40.dp).padding(7.dp).clickable {
                        if(listItemsOpen.isNotEmpty()){
                            val message: InputMessage = InputMessage();
                            message.setSenderMessage(textValue);
                            listMessage = listOf(message) + listMessage;
                            isLoadingBotResp = true;
                            scope.launch {
                                val resp = ChatbotClient.getChatbotResponse(input= textValue)
                                message.setBotMessage(resp)
                                isLoadingBotResp = false;
                            }
                        }
                    }){
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_right_alt_24dp),
                            contentDescription = "",
                        )
                    }
                }
            }
        }
    }
}