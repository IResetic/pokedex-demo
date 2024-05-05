package dev.skybit.pokedex.main.main.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.skybit.pokedex.main.core.presentation.style.theme.PokedexTheme
import dev.skybit.pokedex.main.main.navigation.PokedexContent

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        actionBar?.hide()
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            installSplashScreen().apply {
                SplashScreenAnimation.runOnSplashScreenExitAnimation(this)
                setKeepOnScreenCondition {
                    !viewModel.mainActivityState.value.isReady
                }
            }
        }

        setContent {
            val navController = rememberNavController()

            PokedexTheme {
                PokedexContent(navController = navController)
            }
        }
    }
}
