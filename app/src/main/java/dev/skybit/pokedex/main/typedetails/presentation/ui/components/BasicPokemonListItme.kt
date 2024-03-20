package dev.skybit.pokedex.main.typedetails.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.SubcomposeAsyncImage
import dev.skybit.pokedex.R
import dev.skybit.pokedex.main.core.presentation.style.defaultRadius
import dev.skybit.pokedex.main.core.presentation.style.largeImageSize
import dev.skybit.pokedex.main.core.presentation.style.largeRadius
import dev.skybit.pokedex.main.core.utils.capitalizeFirstLetter
import dev.skybit.pokedex.main.typedetails.presentation.model.PokemonBasicInfoUi

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
        SubcomposeAsyncImage(
            modifier = Modifier
                .size(largeImageSize)
                .align(Alignment.CenterHorizontally),
            model = pokemonBasicInfo.imageUrl,
            contentDescription = pokemonBasicInfo.name,
            loading = {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.scale(0.5f)
                )
            },
            error = {
                Image(
                    painter = painterResource(id = R.drawable.ic_image_placeholder),
                    contentDescription = stringResource(id = R.string.pokemon_image_placeholder_content_description)
                )
            }
        )

        Text(
            text = pokemonBasicInfo.name.capitalizeFirstLetter(),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun BasicPokemonListItemPreview() {
    BasicPokemonListItem(
        pokemonBasicInfo = PokemonBasicInfoUi(
            id = 1,
            name = "bulbasaur",
            imageUrl = ""
        )
    )
}
