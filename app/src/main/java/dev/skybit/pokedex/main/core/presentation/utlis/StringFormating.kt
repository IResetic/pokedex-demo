package dev.skybit.pokedex.main.core.presentation.utlis

import java.util.Locale

fun String.capitalizeFirstLetter(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
    }
}
