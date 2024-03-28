package dev.skybit.pokedex.main.pokemon.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import dev.skybit.pokedex.main.core.presentation.style.twentyPercent

@Composable
fun PokemonDetailsHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(twentyPercent)
            .background(Brush.verticalGradient(listOf(Color.Black, Color.Transparent)))
    ) {
    }
}
