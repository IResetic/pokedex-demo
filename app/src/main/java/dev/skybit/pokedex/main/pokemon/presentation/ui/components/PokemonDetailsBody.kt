package dev.skybit.pokedex.main.pokemon.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.skybit.pokedex.main.core.presentation.utlis.capitalizeFirstLetter
import dev.skybit.pokedex.main.core.presentation.utlis.mediumPadding
import dev.skybit.pokedex.main.core.presentation.utlis.mediumRadius
import dev.skybit.pokedex.main.pokemon.presentation.model.PokemonDetailsUi

val POKEMON_DETAILS_BODY_OFFSET_Y = 100.dp

@Composable
fun PokemonDetailsBody(
    pokemonDetailsUi: PokemonDetailsUi
) {
    val scrollState = rememberScrollState()
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
            .offset(y = POKEMON_DETAILS_BODY_OFFSET_Y)
            .verticalScroll(scrollState)
    ) {
        Text(
            text = pokemonDetailsUi.name.capitalizeFirstLetter(),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )

        PokemonDetailsTypeSection(pokemonDetailsUi.types)
        PokemonDimensions(
            pokemonWeight = pokemonDetailsUi.weightKg,
            pokemonHeight = pokemonDetailsUi.heightCm
        )
        PokemonStats(pokemonDetailsUi)
        Spacer(modifier = Modifier.height(POKEMON_DETAILS_BODY_OFFSET_Y))
    }
}
