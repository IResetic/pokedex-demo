package dev.skybit.pokedex.main.pokemontypes.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import dev.skybit.pokedex.R
import dev.skybit.pokedex.main.core.presentation.style.defaultRadius
import dev.skybit.pokedex.main.core.presentation.style.largeRadius
import dev.skybit.pokedex.main.core.presentation.style.mediumPadding
import dev.skybit.pokedex.main.core.presentation.style.theme.TypeNormal
import dev.skybit.pokedex.main.core.presentation.style.thirtyPercent
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_NORMAL
import dev.skybit.pokedex.main.pokemontypes.presentation.model.PokemonTypeUI
import java.util.Locale

@Composable
fun PokemonTypeListItem(
    pokemonType: PokemonTypeUI
) {
    Column(
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .shadow(defaultRadius, RoundedCornerShape(largeRadius))
            .clip(RoundedCornerShape(largeRadius))
            .aspectRatio(1f)
            .background(color = pokemonType.color)
    ) {
        Image(
            painter = painterResource(id = pokemonType.icon),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(thirtyPercent)
        )
        Spacer(modifier = Modifier.height(mediumPadding))
        Text(
            text = pokemonType.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
            },
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = false)
@Composable
fun PokemonTypeListItemPreview() {
    PokemonTypeListItem(
        pokemonType = PokemonTypeUI(
            id = 1,
            name = POKEMON_TYPE_NORMAL,
            icon = R.drawable.ic_normal,
            color = TypeNormal
        )
    )
}
