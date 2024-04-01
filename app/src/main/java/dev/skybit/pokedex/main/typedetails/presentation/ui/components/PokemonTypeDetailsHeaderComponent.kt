@file:OptIn(ExperimentalMaterial3Api::class)

package dev.skybit.pokedex.main.typedetails.presentation.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.skybit.pokedex.R
import dev.skybit.pokedex.main.core.presentation.utlis.capitalizeFirstLetter
import dev.skybit.pokedex.main.core.presentation.utlis.largeIconSize
import dev.skybit.pokedex.main.core.presentation.utlis.largePadding
import dev.skybit.pokedex.main.core.presentation.utlis.twentyPercent
import dev.skybit.pokedex.main.core.utils.BUTTON_CLICK_DEBOUNCE_TIME
import dev.skybit.pokedex.main.core.utils.POKEMON_TYPE_FIRE
import dev.skybit.pokedex.main.pokemontypes.presentation.utils.parseTypeNameToImage

@Composable
fun PokemonTypeDetailsHeaderComponent(
    backgroundColor: Color?,
    title: String?,
    navigateBack: () -> Unit
) {
    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
    var lastClickTimestamp by remember { mutableStateOf(0L) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundColor ?: MaterialTheme.colorScheme.primary)
            .padding(top = largePadding, bottom = largePadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CenterAlignedTopAppBar(
            title = { Text(text = title?.capitalizeFirstLetter() ?: "") },
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
        if (!isLandscape) {
            Image(
                painter = painterResource(id = parseTypeNameToImage(title ?: "")),
                contentDescription = stringResource(id = R.string.pokemon_type_icon_content_description),
                modifier = Modifier
                    .fillMaxSize(twentyPercent)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Preview
@Composable
fun PokemonTypeDetailsHeaderComponentPreview() {
    PokemonTypeDetailsHeaderComponent(
        backgroundColor = MaterialTheme.colorScheme.primary,
        title = POKEMON_TYPE_FIRE,
        navigateBack = {}
    )
}
