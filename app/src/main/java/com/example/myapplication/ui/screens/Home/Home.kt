package com.example.myapplication.ui.screens.Home

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.carousel.*
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.helper.widget.Carousel
import coil.compose.AsyncImage
import com.example.myapplication.ui.shared.components.Weather
import com.example.myapplication.ui.shared.utilizeFunctions.getScreenHeight
import com.example.myapplication.ui.shared.utilizeFunctions.getScreenWidth
import com.example.myapplication.ui.theme.Blue300
import com.example.myapplication.ui.theme.Blue400
import com.example.myapplication.ui.theme.Green100
import com.example.myapplication.ui.theme.Green50
import com.example.myapplication.ui.theme.Grey50
import com.example.myapplication.ui.theme.Grey600
import com.example.myapplication.ui.theme.LightGreen
import kotlinx.coroutines.launch
import com.example.myapplication.R
import com.example.myapplication.ui.shared.components.AutoSlidingCarousel
import com.example.myapplication.ui.shared.context.auth.FirebaseAuthState
import com.example.myapplication.ui.theme.Blue200
import com.example.myapplication.ui.theme.Green200
import com.example.myapplication.ui.theme.Green300
import com.example.myapplication.ui.theme.Grey100
import com.example.myapplication.ui.theme.Grey400
import com.example.myapplication.ui.theme.Grey500
import com.example.myapplication.ui.theme.LightGreen100
import com.example.myapplication.ui.theme.LightGreen200
import com.example.myapplication.ui.theme.LightGreen300
import com.example.myapplication.ui.theme.LightGreen50
import com.example.myapplication.ui.theme.Purple200
import com.example.myapplication.ui.theme.Purple300
import com.google.accompanist.pager.ExperimentalPagerApi
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import coil.request.ImageRequest


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun Home(navHostController: NavHostController, authModelView: AuthModelView, topBarName: String, drawerState: DrawerState) {
    val startDestination = AppScreen.Home
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

    val scope = rememberCoroutineScope()

    data class CarouselItem(
        val id: Int,
        @DrawableRes val imageResId: Int,
        //val contentDescription: String,
        val heading: String,
        val contentBrief: String
    )

    val items = remember {
        listOf(
            CarouselItem(0, R.drawable.demo_news_1, "Thủ tướng: Cần tạo nền tảng vững chắc cho tăng trưởng bền vững từ năm 2025","Hướng tới mục tiêu phát triển đột phá trong giai đoạn mới, Chính phủ đặt quyết tâm đạt mức tăng trưởng GDP 8,3–8,5% ngay trong năm 2025."),
            CarouselItem(1, R.drawable.demo_news_1, "donut",""),
            CarouselItem(2, R.drawable.demo_news_1, "eclair",""),
        )
    }

    val images = listOf(
        "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg",
        "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg",
        "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg",
    )
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(
                topBarName
            ) }, navigationIcon = {
                Icon(Icons.Default.Menu, contentDescription = "Localized description", modifier = Modifier
                    .size(30.dp)
                    .clickable { scope.launch { drawerState.apply { if (isClosed) open() else close() } } })
            })
        }

    ) { innerPadding ->
        Column(
            Modifier
                .background(Green50)
                .padding(innerPadding)
                .verticalScroll(
                    rememberScrollState()
                ),) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .height(420.dp)) {
                Weather();
            }
            Column (
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp, horizontal = 8.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(210.dp)){
                    Text("App fatures", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text("See All")
                }


                Column(Modifier.fillMaxWidth().height(170.dp).background(Color.White, shape = RoundedCornerShape(15.dp)), horizontalAlignment=Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()).padding(2.dp), horizontalArrangement= Arrangement.spacedBy(25.dp, Alignment.CenterHorizontally), verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .size(110.dp)
                                .background(
                                    color = Green50,
                                    shape = CircleShape
                                )
                                .border(
                                    width = 2.dp,
                                    color = Color.White,
                                    shape = CircleShape
                                )
                        ) {
                            Column(horizontalAlignment=Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                                Icon(
                                    painter = painterResource(id = R.drawable.camera_24dp),
                                    contentDescription = "",
                                    modifier = Modifier.size(40.dp), // Icon size is half the button size
                                    tint = Blue300 // Optional: Customize icon color
                                )
                                Text("Smart Detect", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                            }
                        }

                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .size(110.dp)
                                .background(
                                    color = Green50,
                                    shape = CircleShape
                                )
                                .border(
                                    width = 2.dp,
                                    color = Color.White,
                                    shape = CircleShape
                                )
                        ) {
                            Column(horizontalAlignment=Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                                Icon(
                                    painter = painterResource(id = R.drawable.smart_bot_24dp),
                                    contentDescription = "",
                                    modifier = Modifier.size(40.dp), // Icon size is half the button size
                                    tint = LightGreen300 // Optional: Customize icon color
                                )
                                Text("Smart Bot", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                            }
                        }

                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .size(110.dp)
                                .background(
                                    color = Green50,
                                    shape = CircleShape
                                )
                                .border(
                                    width = 2.dp,
                                    color = Color.White,
                                    shape = CircleShape
                                )
                        ) {
                            Column(horizontalAlignment=Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                                Icon(
                                    painter = painterResource(id = R.drawable.smart_predict_24dp),
                                    contentDescription = "",
                                    modifier = Modifier.size(40.dp), // Icon size is half the button size
                                    tint = Purple300 // Optional: Customize icon color
                                )
                                Text("Smart Predict", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }

            }

            Column (
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp, horizontal = 8.dp).height(500.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {

                Row(horizontalArrangement = Arrangement.spacedBy(170 .dp)){
                    Text("Agriculture news", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text("See All")
                }

                Column(Modifier.background(Color.White)) {
                    Card(
                        modifier = Modifier.padding(16.dp),
                        shape = RoundedCornerShape(16.dp),
                    ) {
                        AutoSlidingCarousel(
                            itemsCount = images.size,
                            itemContent = { index ->

                                Box {
                                    Image(
                                        modifier = Modifier
                                            .height(350.dp).clickable { println(items[index].heading+"contentDescription") },
                                        painter = painterResource(id = items[index].imageResId),
                                        contentDescription = items[index].heading,
                                        contentScale = ContentScale.Crop
                                    )

                                    Surface(

                                        shape = RoundedCornerShape(16.dp),
                                        modifier = Modifier
                                            .padding(10.dp)
                                            .align(Alignment.BottomCenter).width(320.dp).align(Alignment.Center),
                                        color = Color.Black.copy(alpha = 0.5f)
                                    ) {
                                        Box(
                                            Modifier.padding(15.dp)
                                        ){
                                            Text("${items[index].heading}", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.SansSerif)
                                        }
                                    }
                                }



                            }
                        )
                    }
                }



                /*
                Column(Modifier.fillMaxWidth().background(Color.White, shape = RoundedCornerShape(15.dp)).padding(10.dp).height(600.dp)) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Image( painterResource(R.drawable.demo_news_1), contentDescription = "", Modifier.clip(RoundedCornerShape(16.dp)).fillMaxWidth(), contentScale = ContentScale.FillWidth)

                    }


                    Column(
                        modifier = Modifier.fillMaxWidth().offset(y = -89.dp).background(Color.Black.copy(alpha = 0.5f), shape = RoundedCornerShape(15.dp)).padding(9.dp)

                    ){
                        Text("Hộ dân chuyển đất nông nghiệp sang đất ở có thể được tính tiền sử dụng đất theo cách cũ", color = Color.White)
                    }

                    Row(Modifier.padding(5.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(painterResource(R.drawable.article_person_24dp), contentDescription = "Localized description", Modifier.background(
                            Grey50))
                        Row(horizontalArrangement = Arrangement.SpaceBetween){
                            Text("https://vnexpress.net/ho-dan-chuyen-dat-nong-nghiep-sang-dat-o-co-the-duoc-tinh-tien-su-dung-dat-theo-cach-cu-4913213.html", Modifier.basicMarquee())
                        }

                    }
                }
                */
            }
        }
    }
}