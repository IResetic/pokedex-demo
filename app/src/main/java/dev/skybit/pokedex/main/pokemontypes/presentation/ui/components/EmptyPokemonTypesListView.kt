package dev.skybit.pokedex.main.pokemontypes.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import dev.skybit.pokedex.R
import dev.skybit.pokedex.main.core.presentation.utlis.mediumPadding
import dev.skybit.pokedex.main.core.presentation.utlis.thirtyPercent
import dev.skybit.pokedex.main.core.presentation.utlis.twentyFivePercent

@Composable
fun EmptyPokemonTypesListView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(start = mediumPadding, end = mediumPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(thirtyPercent))

        Image(
            painter = painterResource(id = R.drawable.ic_list),
            contentDescription = stringResource(id = R.string.empty_pokemon_types_list_content_description),
            modifier = Modifier.fillMaxSize(twentyFivePercent)
        )

        Text(
            text = stringResource(id = R.string.pokemon_types_empty_list_message),
            style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.onPrimary
            ),
            textAlign = TextAlign.Center
        )
    }
}
