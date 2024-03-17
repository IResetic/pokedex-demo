package dev.skybit.pokedex.main.typedetails.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import dev.skybit.pokedex.main.core.presentation.style.largePadding
import dev.skybit.pokedex.main.core.presentation.style.twentyPercent
import dev.skybit.pokedex.main.pokemontypes.utils.parseTypeNameToImage

@Composable
fun PokemonTypeDetailsHeaderComponent(
    backgroundColor: Color?,
    title: String?
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundColor ?: MaterialTheme.colorScheme.primary)
            .padding(top = largePadding, bottom = largePadding)
    ) {
        Column {
            Image(
                painter = painterResource(id = parseTypeNameToImage(title ?: "")),
                contentDescription = "Pokemon type icon",
                modifier = Modifier.fillMaxSize(twentyPercent).align(Alignment.CenterHorizontally)
            )
        }
    }
}
