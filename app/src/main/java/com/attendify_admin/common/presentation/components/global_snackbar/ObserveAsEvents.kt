package com.attendify_admin.common.presentation.components.global_snackbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

@Composable
fun <T> ObserveAsEvents(
    flow: Flow<T>,
    key1: Any? = null,
    key2: Any? = null,
    onEvent: (T) -> Unit
){

    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(
        lifecycleOwner.lifecycle, key1, key2, flow // flow is passed bcz in a case you can change the flow from the place where this function is called
    ) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {  // will repeat whenever lifecycle is in the started state
            withContext(Dispatchers.Main.immediate) { // .immediate tells to execute it immediately instead of dispatching if already on the main thread (launchedEffect in compose works on main thread by default)
                flow.collect(onEvent)
            }
        }
    }

}
