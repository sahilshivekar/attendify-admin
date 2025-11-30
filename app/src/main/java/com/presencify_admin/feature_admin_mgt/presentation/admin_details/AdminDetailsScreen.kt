package com.presencify_admin.feature_admin_mgt.presentation.admin_details

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.presencify_admin.common.presentation.PreviewWrapper
import com.presencify_admin.common.presentation.components.top_bar.PresencifyTopAppBar
import com.presencify_admin.common.presentation.components.top_bar.TopAppBarState
import com.presencify_admin.feature_admin_mgt.presentation.admin_details.components.AccountSettings
import com.presencify_admin.feature_admin_mgt.presentation.admin_details.components.EmailAndPasswordContainer
import com.presencify_admin.feature_admin_mgt.presentation.admin_details.components.PasswordContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDetailsScreen(
    modifier: Modifier = Modifier,
    navigateToUpdatePassword: () -> Unit,
    navigateToVerifyCode: () -> Unit,
    navigateToAddAdmin: () -> Unit,
    state: AdminDetailsState,
    onEvent: (AdminDetailsEvent) -> Unit,
    onBackIconButtonClick: () -> Unit,
) {
    LaunchedEffect(state.isVerificationCodeSent) {
        if (state.isVerificationCodeSent) {
            navigateToVerifyCode()
        }
    }


    Scaffold(
        topBar = {
            PresencifyTopAppBar(
                topAppBarState = TopAppBarState(
                    title = "Admin Details",
                    isTopAppBarVisible = true,
                    isBackIconButtonVisible = true,
                    isProfileIconButtonVisible = false
                ),
                onBackIconButtonClick = onBackIconButtonClick
            )
        }
    ) { paddingValues ->
        AnimatedContent(
            targetState = state.isInitialDataLoading,
            transitionSpec = {
                (slideInVertically { it / 4 } + fadeIn()).togetherWith(fadeOut())
            }
        ) { targetState ->
            if (targetState) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            } else {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(paddingValues)
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    // icon on top
                    Spacer(modifier = Modifier.height(16.dp))
                    Icon(
                        modifier = Modifier
                            .size(90.dp)
                            .clip(CircleShape),
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )

                    EmailAndPasswordContainer(
                        state = state,
                        onEvent = onEvent
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    PasswordContainer(
                        state = state,
                        navigateToUpdatePassword = navigateToUpdatePassword
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    AccountSettings(
                        state = state,
                        onEvent = onEvent,
                        navigateToAddAdmin = navigateToAddAdmin
                    )
                    Spacer(modifier = Modifier.height(16.dp))


                }
            }
        }
    }
}


@PreviewScreenSizes
@Composable
fun AdminDetailsScreenPreview() {
    PreviewWrapper {
        AdminDetailsScreen(
            navigateToUpdatePassword = {},
            navigateToAddAdmin = {},
            navigateToVerifyCode = {},
            state = AdminDetailsState(),
            onEvent = {},
            onBackIconButtonClick = {}
        )
    }
}