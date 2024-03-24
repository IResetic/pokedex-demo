package dev.skybit.pokedex.main.typedetails.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import dev.skybit.pokedex.R
import dev.skybit.pokedex.main.core.presentation.style.mediumPadding
import dev.skybit.pokedex.main.core.presentation.style.smallPadding
import dev.skybit.pokedex.main.core.presentation.style.twentyFivePercent
import dev.skybit.pokedex.main.core.presentation.style.twentyPercent

@Composable
fun PokemonsRetryOnErrorView(
    backgroundColor: Color?,
    onRetry: () -> Unit
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
            painter = painterResource(id = R.drawable.ic_error_white),
            contentDescription = stringResource(id = R.string.empty_pokemons_list_content_description),
            modifier = Modifier.fillMaxSize(twentyFivePercent)
        )

        Text(
            text = stringResource(id = R.string.pokemon_types_error_message),
            style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.onPrimary
            ),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(mediumPadding))
        FilledTonalButton(
            onClick = { onRetry() }
        ) {
            Text(
                modifier = Modifier.padding(
                    top = smallPadding,
                    bottom = smallPadding,
                    start = mediumPadding,
                    end = mediumPadding
                ),
                text = stringResource(id = R.string.retry_button_text),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
@Preview
fun EmptyPokemonsListPreview() {
    PokemonsRetryOnErrorView(
        backgroundColor = null,
        onRetry = {}
    )
}
