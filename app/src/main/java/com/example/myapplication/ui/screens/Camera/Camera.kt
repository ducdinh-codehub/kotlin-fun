package com.example.myapplication.ui.screens.Camera

import android.Manifest
import android.view.ViewGroup
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavHostController
import com.example.myapplication.ui.shared.context.auth.AuthModelView
import com.example.myapplication.ui.theme.Red400
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import kotlinx.coroutines.launch
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun Camera(navHostController: NavHostController, authModelView: AuthModelView, topBarName: String, drawerState: DrawerState, modifier: Modifier){
    println("HELLO CAMERA")
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val coroutineScope = rememberCoroutineScope()
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    var showDialog by remember { mutableStateOf(false) }


    println("cameraPermissionState"+cameraPermissionState.status)

    val cameraController: LifecycleCameraController = remember { LifecycleCameraController(context) }
    Scaffold(
        modifier = modifier

    ) { paddingValues ->
        Column(Modifier.padding(paddingValues).fillMaxSize()) {
            when (cameraPermissionState.status) {
                is PermissionStatus.Granted -> {
                    showDialog = false;
                    AndroidView(factory = {
                        PreviewView(it).apply {
                            layoutParams = ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                            )
                            implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                            scaleType = PreviewView.ScaleType.FILL_CENTER
                        }.also { previewView ->
                            coroutineScope.launch {
                                cameraController.cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
                                cameraController.bindToLifecycle(lifecycleOwner)
                                previewView.controller = cameraController
                            }
                        }
                    })
                    // Proceed with camera functionality
                }
                is PermissionStatus.Denied -> {
                    showDialog = true;


                    AlertDialog(
                        onDismissRequest = { showDialog }, // Called when the user dismisses the dialog
                        title = { Text("Camera permission") },
                        text = { Text(if (cameraPermissionState.status.shouldShowRationale)
                            "The camera is important for this app. Please grant the permission."
                        else
                            "Camera permission denied. Please enable it in settings."
                        ) },
                        confirmButton = {
                            TextButton(onClick = { showDialog = false
                                cameraPermissionState.launchPermissionRequest()
                            }) {
                                Text("Request Permission")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { showDialog = false }) {
                                Text("Cancel")
                            }
                        }
                    )

                    /*
                    Column {
                        val textToShow = if (cameraPermissionState.status.shouldShowRationale) {
                            "The camera is important for this app. Please grant the permission."
                        } else {
                            "Camera permission denied. Please enable it in settings."
                        }
                        Text(textToShow)
                        Button(onClick = { cameraPermissionState.launchPermissionRequest() }) {
                            Text("Request Permission")
                        }
                    }*/
                }

            }


        }

        /*
        if (permissionState.allPermissionsGranted) {

            println("GRANTED")
            AndroidView(
                factory = { ctx ->
                    val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)
                    cameraProviderFuture.addListener({
                        val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
                        val preview = androidx.camera.core.Preview.Builder()
                            .build()
                            .also {
                                it.setSurfaceProvider(previewView.surfaceProvider)
                            }

                        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                        try {
                            cameraProvider.unbindAll()
                            cameraProvider.bindToLifecycle(
                                lifecycleOwner,
                                cameraSelector,
                                preview
                            )
                        } catch (exc: Exception) {
                            // Handle errors
                        }
                    }, ContextCompat.getMainExecutor(ctx))
                    previewView
                },
                modifier = Modifier.fillMaxSize()
            )
        } else {
            println("NONGRANTED")

            // Handle permission request
        }*/
    }
}