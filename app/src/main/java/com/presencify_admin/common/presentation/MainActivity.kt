package com.presencify_admin.common.presentation

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.presencify_admin.common.presentation.components.global_snackbar.ObserveAsEvents
import com.presencify_admin.common.presentation.components.global_snackbar.SnackbarController
import com.presencify_admin.root_navigation.PresencifyNavHost
import com.presencify_admin.ui.theme.PresencifyAdminTheme
import com.presencify_admin.ui.theme.DarkThemePrimary
import com.presencify_admin.ui.theme.LightThemePrimary
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


        @AndroidEntryPoint
        class MainActivity : ComponentActivity() {

            private val viewModel: InitialDestinationViewModel by viewModels()

            @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")  // to avoid extra padding in screens from top
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)


                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) { // Q = Android 10, API 29
                    enableEdgeToEdge() // this shows label on android 12+ devices
                }

                // state destination depends on whether accessToken is available in the datastore
                installSplashScreen().apply {
                    setKeepOnScreenCondition {
                        viewModel.state == null
                    }
                }

                setContent {
                    PresencifyAdminTheme {
                        viewModel.state?.let { appDestination ->



                            val snackbarHostState = remember { SnackbarHostState() }
                            val scope = rememberCoroutineScope()


                            ObserveAsEvents(
                                SnackbarController.events,
                            ) { event ->
                                scope.launch {
                                    snackbarHostState.currentSnackbarData?.dismiss()

                                    val result = snackbarHostState.showSnackbar(
                                        message = event.message,
                                        actionLabel = event.action?.name,
                                        duration = if (event.action == null) SnackbarDuration.Short else SnackbarDuration.Long
                                    )

                                    if (result == SnackbarResult.ActionPerformed) {
                                        event.action?.action()
                                    }
                                }

                            }

                            Scaffold(
                                snackbarHost = {
                                    SnackbarHost(
                                        hostState = snackbarHostState,
                                        snackbar = { data ->
                                            Snackbar(
                                                snackbarData = data,
                                                actionColor = if(isSystemInDarkTheme()) LightThemePrimary else DarkThemePrimary,
                                                dismissActionContentColor = MaterialTheme.colorScheme.onSurface,
                                            )
                                        }
                                    )
                                }
                            ) {
                                PresencifyNavHost(
                                    startDestination = appDestination
                                )
                            }
                        }
                    }
                }
            }
        }
