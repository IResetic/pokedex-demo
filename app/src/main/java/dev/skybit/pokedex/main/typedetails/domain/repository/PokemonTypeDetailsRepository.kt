package dev.skybit.pokedex.main.typedetails.domain.repository

import dev.skybit.pokedex.main.typedetails.domain.model.PokemonTypeDetails

interface PokemonTypeDetailsRepository {
    suspend fun getPokemonTypeDetails(): List<PokemonTypeDetails>
}
