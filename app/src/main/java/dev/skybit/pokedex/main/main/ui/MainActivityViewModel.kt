package dev.skybit.pokedex.main.main.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.skybit.pokedex.main.main.ui.SplashScreenAnimation.SPLASH_SCREEN_ZOOM_DURATION
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel() {
    private val _mainActivityState = MutableStateFlow(MainActivityState())
    val mainActivityState = _mainActivityState.asStateFlow()

    init {
        viewModelScope.launch {
            delay(SPLASH_SCREEN_ZOOM_DURATION)
            _mainActivityState.update {
                it.copy(isReady = true)
            }
        }
    }
}
