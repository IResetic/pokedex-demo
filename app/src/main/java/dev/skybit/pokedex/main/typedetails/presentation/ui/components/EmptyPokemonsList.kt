package dev.skybit.pokedex.main.typedetails.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import dev.skybit.pokedex.main.core.presentation.style.mediumPadding
import dev.skybit.pokedex.main.core.presentation.style.smallPadding

@Composable
fun EmptyPokemonsList(
    message: String,
    backgroundColor: Color?,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor ?: MaterialTheme.colorScheme.primary)
            .padding(start = mediumPadding, end = mediumPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(mediumPadding))
        FilledTonalButton(onClick = { onRetry() }) {
            Text(
                modifier = Modifier.padding(
                    top = smallPadding,
                    bottom = smallPadding,
                    start = mediumPadding,
                    end = mediumPadding
                ),
                text = "Retry",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
@Preview
fun EmptyPokemonsListPreview() {
    EmptyPokemonsList(
        message = "No pokemons found",
        backgroundColor = null,
        onRetry = {}
    )
}
