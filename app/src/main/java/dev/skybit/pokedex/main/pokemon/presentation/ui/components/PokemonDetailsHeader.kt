@file:OptIn(ExperimentalMaterial3Api::class)

package dev.skybit.pokedex.main.pokemon.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import dev.skybit.pokedex.R
import dev.skybit.pokedex.main.core.presentation.utlis.largeIconSize
import dev.skybit.pokedex.main.core.presentation.utlis.largePadding
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
        CenterAlignedTopAppBar(
            title = { Text(text = "") },
            modifier = Modifier
                .background(Color.Transparent)
                .padding(top = largePadding, bottom = largePadding),
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                actionIconContentColor = Color.Transparent
            ),
            navigationIcon = {
                IconButton(
                    onClick = {
                        val currentTime = System.currentTimeMillis()
                        if (currentTime - lastClickTimestamp > BUTTON_CLICK_DEBOUNCE_TIME) {
                            navigateBack()
                            lastClickTimestamp = currentTime
                        }
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(largeIconSize),
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.navigate_back_icon_content_description)
                    )
                }
            }
        )
    }
}
