package dev.skybit.pokedex.main.pokemon.presentation.ui.components

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.skybit.pokedex.R
import dev.skybit.pokedex.main.core.presentation.utlis.capitalizeFirstLetter
import dev.skybit.pokedex.main.core.presentation.utlis.largeIconSize
import dev.skybit.pokedex.main.core.presentation.utlis.mediumPadding
import dev.skybit.pokedex.main.core.presentation.utlis.mediumRadius
import dev.skybit.pokedex.main.pokemon.presentation.model.PokemonDetailsUi
import java.util.Locale

val POKEMON_DETAILS_BODY_OFFSET_Y = 100.dp

@Composable
fun PokemonDetailsBody(
    pokemonDetailsUi: PokemonDetailsUi
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val textToSpeech = TextToSpeech(context) {
    }
    textToSpeech.language = Locale.US

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(
                top = 128.dp,
                start = mediumPadding,
                end = mediumPadding,
                bottom = mediumPadding
            )
            .shadow(mediumRadius, RoundedCornerShape(mediumRadius))
            .clip(RoundedCornerShape(mediumRadius))
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxSize()
            .offset(y = POKEMON_DETAILS_BODY_OFFSET_Y)
            .verticalScroll(scrollState)
    ) {
        Text(
            text = pokemonDetailsUi.name.capitalizeFirstLetter(),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )

        IconButton(onClick = {
            textToSpeech.speak(
                pokemonDetailsUi.name,
                TextToSpeech.QUEUE_FLUSH,
                null,
                ""
            )
        }) {
            Icon(
                modifier = Modifier.size(largeIconSize),
                painter = painterResource(id = R.drawable.ic_name_to_speech_24),
                contentDescription = stringResource(id = R.string.navigate_back_icon_content_description)
            )
        }

        PokemonDetailsTypeSection(pokemonDetailsUi.types)
        PokemonDimensions(
            pokemonWeight = pokemonDetailsUi.weightKg,
            pokemonHeight = pokemonDetailsUi.heightCm
        )
        PokemonStats(pokemonDetailsUi)
        Spacer(modifier = Modifier.height(POKEMON_DETAILS_BODY_OFFSET_Y))
    }
}
