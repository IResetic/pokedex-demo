package dev.skybit.pokedex.main.pokemon.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import dev.skybit.pokedex.main.core.presentation.utlis.capitalizeFirstLetter
import dev.skybit.pokedex.main.core.presentation.utlis.mediumHeight
import dev.skybit.pokedex.main.core.presentation.utlis.mediumPadding
import dev.skybit.pokedex.main.core.presentation.utlis.smallPadding
import dev.skybit.pokedex.main.core.utils.parseTypeToColor

@Composable
fun PokemonDetailsTypeSection(pokemonTypeNames: List<String>) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(mediumPadding)
    ) {
        for (typeName in pokemonTypeNames) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = smallPadding)
                    .clip(CircleShape)
                    .background(parseTypeToColor(typeName))
                    .height(mediumHeight)
            ) {
                Text(
                    text = typeName.capitalizeFirstLetter(),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}
