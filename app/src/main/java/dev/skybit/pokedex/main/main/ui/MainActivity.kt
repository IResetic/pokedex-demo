package dev.skybit.pokedex.main.main.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.skybit.pokedex.main.core.presentation.style.theme.PokedexTheme
import dev.skybit.pokedex.main.main.navigation.PokedexContent

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            SplashScreenAnimation.runOnSplashScreenExitAnimation(this)
        }

        setContent {
            val navController = rememberNavController()

            PokedexTheme {
                PokedexContent(navController = navController)
            }
        }
    }
}
