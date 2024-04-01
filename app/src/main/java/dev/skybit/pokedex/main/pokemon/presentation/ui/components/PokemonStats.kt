package dev.skybit.pokedex.main.pokemon.presentation.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import dev.skybit.pokedex.R
import dev.skybit.pokedex.main.core.presentation.utlis.mediumPadding
import dev.skybit.pokedex.main.core.presentation.utlis.smallHeight
import dev.skybit.pokedex.main.core.presentation.utlis.smallPadding
import dev.skybit.pokedex.main.pokemon.presentation.model.PokemonDetailsUi
import dev.skybit.pokedex.main.pokemon.presentation.utlis.parseStatToColor
import java.util.Locale

const val STAT_ANIMATION_DELAY_PER_ITEM_MS = 100
const val STAT_ANIMATION_DURATION_MS = 1000

@Composable
fun PokemonStats(
    pokemonDetailsUi: PokemonDetailsUi
) {
    val maxBaseStat = remember {
        pokemonDetailsUi.stats.maxOf { it.statValue }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(mediumPadding + smallPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(smallPadding))

        Text(
            text = stringResource(id = R.string.basic_stats_title),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(mediumPadding))

        for (index in pokemonDetailsUi.stats.indices) {
            val stat = pokemonDetailsUi.stats[index]
            PokemonStat(
                statName = stat.statName.uppercase(Locale.ROOT),
                statValue = stat.statValue,
                statMaxValue = maxBaseStat,
                statColor = parseStatToColor(stat.statName),
                animDelay = index * STAT_ANIMATION_DELAY_PER_ITEM_MS
            )
            Spacer(modifier = Modifier.height(smallPadding))
        }
    }
}

@Composable
fun PokemonStat(
    statName: String,
    statValue: Int,
    statMaxValue: Int,
    statColor: Color,
    animDelay: Int = 0
) {
    var animationPlayed by remember { mutableStateOf(false) }

    val curPercent = animateFloatAsState(
        targetValue = if (animationPlayed) {
            statValue / statMaxValue.toFloat()
        } else {
            0f
        },
        animationSpec = tween(
            durationMillis = STAT_ANIMATION_DURATION_MS,
            delayMillis = animDelay
        ),
        label = ""
    )
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(smallHeight)
            .clip(CircleShape)
            .background(Color.LightGray)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(curPercent.value)
                .clip(CircleShape)
                .background(statColor)
                .padding(horizontal = smallPadding)
        ) {
            Text(
                text = statName,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                text = (curPercent.value * statMaxValue).toInt().toString(),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}
