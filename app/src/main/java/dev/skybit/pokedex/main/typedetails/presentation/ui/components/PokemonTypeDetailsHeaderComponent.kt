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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import dev.skybit.pokedex.R
import dev.skybit.pokedex.main.core.presentation.style.largePadding
import dev.skybit.pokedex.main.core.presentation.style.twentyPercent
import dev.skybit.pokedex.main.core.utils.capitalizeFirstLetter
import dev.skybit.pokedex.main.pokemontypes.utils.parseTypeNameToImage

@Composable
fun PokemonTypeDetailsHeaderComponent(
    backgroundColor: Color?,
    title: String?,
    navigateBack: () -> Unit
) {
    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

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
                IconButton(onClick = { navigateBack() }) {
                    Icon(
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
