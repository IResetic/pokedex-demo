package dev.skybit.pokedex.main.pokemon.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.skybit.pokedex.R
import dev.skybit.pokedex.main.core.presentation.utlis.largeIconSize
import dev.skybit.pokedex.main.core.presentation.utlis.twentyPercent
import dev.skybit.pokedex.main.core.utils.BUTTON_CLICK_DEBOUNCE_TIME

@Composable
fun PokemonDetailsHeader(
    navigateBack: () -> Unit
) {
    var lastClickTimestamp by remember { mutableStateOf(0L) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(twentyPercent)
            .background(Brush.verticalGradient(listOf(Color.Black, Color.Transparent)))
    ) {
        IconButton(
            onClick = {
                val currentTime = System.currentTimeMillis()
                if (currentTime - lastClickTimestamp > BUTTON_CLICK_DEBOUNCE_TIME) {
                    navigateBack()
                    lastClickTimestamp = currentTime
                }
            },
            modifier = Modifier.offset(16.dp, 16.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                tint = Color.White,
                contentDescription = stringResource(id = R.string.navigate_back_icon_content_description),
                modifier = Modifier.size(largeIconSize)
            )
        }
    }
}
