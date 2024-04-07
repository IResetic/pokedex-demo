package dev.skybit.pokedex.main.pokemon.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import dev.skybit.pokedex.main.core.presentation.ui.components.shimmerEffect
import dev.skybit.pokedex.main.core.presentation.utlis.mediumPadding
import dev.skybit.pokedex.main.core.presentation.utlis.mediumRadius

@Composable
fun PokemonDetailsShimmerView() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(
                top = 128.dp,
                start = mediumPadding,
                end = mediumPadding,
                bottom = mediumPadding
            )
            .shadow(mediumRadius, RoundedCornerShape(mediumRadius))
            .clip(RoundedCornerShape(mediumRadius))
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxSize()
            .shimmerEffect()
    ) {}
}
