package dev.skybit.pokedex.main.pokemontypes.presentation.ui.components

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import dev.skybit.pokedex.R
import dev.skybit.pokedex.main.core.presentation.utlis.mediumPadding
import dev.skybit.pokedex.main.core.presentation.utlis.smallPadding
import dev.skybit.pokedex.main.core.presentation.utlis.thirtyPercent
import dev.skybit.pokedex.main.core.presentation.utlis.twentyFivePercent

@Composable
fun PokemonTypeRetryOnErrorView(
    message: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(start = mediumPadding, end = mediumPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(thirtyPercent))

        Image(
            painter = painterResource(id = R.drawable.ic_erorr),
            contentDescription = stringResource(id = R.string.pokemon_type_loading_error_icon_content_description),
            modifier = Modifier.fillMaxSize(twentyFivePercent)
        )
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

@Preview(showBackground = true)
@Composable
fun EmptyPokemonTypesListPreview() {
    PokemonTypeRetryOnErrorView(
        message = "An error occurred while loading the pokemon types",
        onRetry = {}
    )
}
