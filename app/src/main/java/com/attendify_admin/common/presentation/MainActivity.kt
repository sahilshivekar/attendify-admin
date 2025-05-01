package com.attendify_admin.common.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.attendify_admin.common.presentation.scaffold.AppScaffold
import com.attendify_admin.ui.theme.AttendifyAdminTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: InitialDestinationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // state destination depends on whether accessToken is available in the datastore
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.state == null
            }
        }

        setContent {
            AttendifyAdminTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    viewModel.state?.let { appDestination ->
                        AppScaffold(
                            startDestination = appDestination
                        )
                    }
                }
            }

        }
    }
}
