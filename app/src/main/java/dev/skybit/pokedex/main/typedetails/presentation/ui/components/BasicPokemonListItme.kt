package dev.skybit.pokedex.main.typedetails.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import dev.skybit.pokedex.main.core.presentation.style.defaultRadius
import dev.skybit.pokedex.main.core.presentation.style.largeImageSize
import dev.skybit.pokedex.main.core.presentation.style.largeRadius
import dev.skybit.pokedex.main.typedetails.presentation.model.PokemonBasicInfoUi
import java.util.Locale

@Composable
fun BasicPokemonListItem(
    pokemonBasicInfo: PokemonBasicInfoUi
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .shadow(defaultRadius, RoundedCornerShape(largeRadius))
            .clip(RoundedCornerShape(largeRadius))
            .aspectRatio(1f)
            .background(color = MaterialTheme.colorScheme.onPrimary)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(largeImageSize)
                .align(Alignment.CenterHorizontally),
            model = pokemonBasicInfo.imageUrl,
            contentDescription = pokemonBasicInfo.name
        )

        Text(
            text = pokemonBasicInfo.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
            },
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}
