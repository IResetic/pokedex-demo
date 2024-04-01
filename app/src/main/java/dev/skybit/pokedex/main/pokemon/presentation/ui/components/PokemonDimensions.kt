package dev.skybit.pokedex.main.pokemon.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.skybit.pokedex.R
import dev.skybit.pokedex.main.core.presentation.utlis.sectionSize
import dev.skybit.pokedex.main.core.presentation.utlis.smallPadding
import dev.skybit.pokedex.main.core.utils.POKEMON_HEIGHT_UNIT
import dev.skybit.pokedex.main.core.utils.POKEMON_WEIGHT_UNIT

@Composable
fun PokemonDimensions(
    pokemonWeight: Double,
    pokemonHeight: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        PokemonDimensionsItem(
            dimensionsValue = pokemonWeight,
            dimensionsUnit = POKEMON_WEIGHT_UNIT,
            dimensionsIcon = painterResource(id = R.drawable.ic_weight),
            modifier = Modifier.weight(1f)
        )
        Spacer(
            modifier = Modifier
                .size(1.dp, sectionSize)
                .background(Color.LightGray)
        )
        PokemonDimensionsItem(
            dimensionsValue = pokemonHeight.toDouble(),
            dimensionsUnit = POKEMON_HEIGHT_UNIT,
            dimensionsIcon = painterResource(id = R.drawable.ic_height),
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun PokemonDimensionsItem(
    dimensionsValue: Double,
    dimensionsUnit: String,
    dimensionsIcon: Painter,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Icon(painter = dimensionsIcon, contentDescription = null, tint = MaterialTheme.colorScheme.onSurface)
        Spacer(modifier = Modifier.height(smallPadding))
        Text(
            text = "$dimensionsValue$dimensionsUnit",
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
