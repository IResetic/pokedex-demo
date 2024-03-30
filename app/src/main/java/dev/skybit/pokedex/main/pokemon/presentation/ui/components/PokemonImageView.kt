package dev.skybit.pokedex.main.pokemon.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.skybit.pokedex.main.core.presentation.style.largeImageSite
import dev.skybit.pokedex.main.core.utils.IMAGE_BASE_URL
import dev.skybit.pokedex.main.core.utils.POKEMON_IMG_FORMAT
import dev.skybit.pokedex.main.pokemon.presentation.model.PokemonDetailsUi

@Composable
fun PokemonImageView(
    pokemonDetailsUi: PokemonDetailsUi
) {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxSize()
    ) {
        AsyncImage(
            modifier = Modifier
                .size(largeImageSite)
                .offset(y = 20.dp),
            model = "$IMAGE_BASE_URL${pokemonDetailsUi.id}$POKEMON_IMG_FORMAT",
            contentDescription = pokemonDetailsUi.name
        )
    }
}
