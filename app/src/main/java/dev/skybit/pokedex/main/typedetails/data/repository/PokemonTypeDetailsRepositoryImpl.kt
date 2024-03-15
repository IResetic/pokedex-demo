package dev.skybit.pokedex.main.typedetails.data.repository

import dev.skybit.pokedex.main.typedetails.data.datasources.PokemonTypeDetailsLocalDataSource
import dev.skybit.pokedex.main.typedetails.data.datasources.PokemonTypeDetailsRemoteDataSource
import dev.skybit.pokedex.main.typedetails.domain.model.PokemonTypeDetails
import dev.skybit.pokedex.main.typedetails.domain.repository.PokemonTypeDetailsRepository
import javax.inject.Inject

class PokemonTypeDetailsRepositoryImpl @Inject constructor(
    private val pokemonTypeDetailsRemoteDataSource: PokemonTypeDetailsRemoteDataSource,
    private val pokemonTypeDetailsLocalDataSource: PokemonTypeDetailsLocalDataSource
): PokemonTypeDetailsRepository {
    override suspend fun getPokemonTypeDetails(pokemonTypeId: Int): List<PokemonTypeDetails> {
        TODO("Not yet implemented")
    }

    private suspend fun fetchPokemonTypeDetails(pokemonTypeId: Int) {
        try {

        } catch (e: Exception) {

        }
    }
}
