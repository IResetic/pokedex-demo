package dev.skybit.pokedex.main.core.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import dev.skybit.pokedex.main.core.presentation.style.theme.PokedexTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            SplashScreenAnimation.runOnSplashScreenExitAnimation(this)
        }

        setContent {
            PokedexTheme {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = "THis is pokedex")
                }
            }
        }
    }
}
