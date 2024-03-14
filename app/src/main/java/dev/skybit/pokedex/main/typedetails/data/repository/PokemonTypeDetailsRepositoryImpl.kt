package dev.skybit.pokedex.main.typedetails.data.repository

import dev.skybit.pokedex.main.typedetails.domain.model.PokemonTypeDetails
import dev.skybit.pokedex.main.typedetails.domain.repository.PokemonTypeDetailsRepository

class PokemonTypeDetailsRepositoryImpl : PokemonTypeDetailsRepository {
    override suspend fun getPokemonTypeDetails(): List<PokemonTypeDetails> {
        TODO("Not yet implemented")
    }
}
