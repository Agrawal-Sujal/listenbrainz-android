package org.listenbrainz.android.ui.screens.profile

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import org.listenbrainz.android.R
import org.listenbrainz.android.util.Constants.Strings.STATUS_LOGGED_IN
import org.listenbrainz.android.viewmodel.UserViewModel

@Composable
fun ProfileScreen(
    context: Context = LocalContext.current,
    viewModel: UserViewModel = hiltViewModel(),
    scrollRequestState: Boolean,
    onScrollToTop: (suspend () -> Unit) -> Unit,
    username: String?,
    snackbarState: SnackbarHostState,
    goToUserProfile: (String) -> Unit,
    goToArtistPage: (String) -> Unit,
    goToPlaylist: (String) -> Unit
) {
    val scrollState = rememberScrollState()
    val uiState = viewModel.uiState.collectAsState()
    // Scroll to the top when shouldScrollToTop becomes true
    LaunchedEffect(scrollRequestState) {
        onScrollToTop {
            scrollState.animateScrollTo(0)
        }
    }
    
    val loginStatus by viewModel.loginStatusFlow.collectAsState()

    when(loginStatus) {
        STATUS_LOGGED_IN -> {
            LaunchedEffect(Unit) {
                viewModel.getUserDataFromRemote(username)
            }

            BaseProfileScreen(
                username = username,
                snackbarState = snackbarState,
                uiState = uiState.value,
                goToUserProfile = goToUserProfile,
                goToArtistPage = goToArtistPage,
                goToPlaylist = goToPlaylist,
            )
        }
        else -> LoginScreen {
            goToUserProfile(viewModel.appPreferences.username.get())
        }
    }
}