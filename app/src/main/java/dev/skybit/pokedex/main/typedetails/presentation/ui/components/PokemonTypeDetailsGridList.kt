package dev.skybit.pokedex.main.typedetails.presentation.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import dev.skybit.pokedex.main.core.presentation.utlis.defaultPadding
import dev.skybit.pokedex.main.core.presentation.utlis.largePadding
import dev.skybit.pokedex.main.core.utils.LANDSCAPE_MODE_NUMBER_OF_COLUMNS
import dev.skybit.pokedex.main.core.utils.PORTRAIT_MODE_NUMBER_OF_COLUMNS

@Composable
fun PokemonTypeDetailsGridList(
    backgroundColor: Color?,
    itemCount: Int,
    renderItem: @Composable (Int) -> Unit
) {
    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
    val gridState = rememberLazyGridState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(
            if (isLandscape) LANDSCAPE_MODE_NUMBER_OF_COLUMNS else PORTRAIT_MODE_NUMBER_OF_COLUMNS
        ),
        state = gridState,
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor ?: MaterialTheme.colorScheme.primary),
        contentPadding = PaddingValues(
            start = largePadding,
            end = largePadding,
            top = defaultPadding,
            bottom = largePadding
        ),
        verticalArrangement = Arrangement.spacedBy(defaultPadding),
        horizontalArrangement = Arrangement.spacedBy(defaultPadding)
    ) {
        items(itemCount) { index ->
            renderItem(index)
        }
    }
}
