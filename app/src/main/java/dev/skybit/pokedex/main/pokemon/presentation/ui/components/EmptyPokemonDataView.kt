package dev.skybit.pokedex.main.pokemon.presentation.ui.components

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import dev.skybit.pokedex.R
import dev.skybit.pokedex.main.core.presentation.utlis.mediumPadding
import dev.skybit.pokedex.main.core.presentation.utlis.twentyFivePercent
import dev.skybit.pokedex.main.core.presentation.utlis.twentyPercent

@Composable
fun EmptyPokemonDataView(
    backgroundColor: Color?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor ?: MaterialTheme.colorScheme.primary)
            .padding(start = mediumPadding, end = mediumPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(twentyPercent))

        Image(
            painter = painterResource(id = R.drawable.ic_list),
            contentDescription = stringResource(id = R.string.empty_pokemons_list_content_description),
            modifier = Modifier.fillMaxSize(twentyFivePercent)
        )

        Text(
            text = stringResource(id = R.string.empty_pokemons_list_message),
            style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.onPrimary
            ),
            textAlign = TextAlign.Center
        )
    }
}
