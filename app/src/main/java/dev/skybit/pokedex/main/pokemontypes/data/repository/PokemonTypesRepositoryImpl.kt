package dev.skybit.pokedex.main.pokemontypes.data.repository

import dev.skybit.pokedex.main.pokemontypes.data.datasources.PokemonTypesRemoteDataSource
import dev.skybit.pokedex.main.pokemontypes.domain.model.PokemonType
import dev.skybit.pokedex.main.pokemontypes.domain.repository.PokemonTypesRepository
import javax.inject.Inject

class PokemonTypesRepositoryImpl @Inject constructor(
    private val pokemonTypesRemoteDataSource: PokemonTypesRemoteDataSource
) : PokemonTypesRepository {

    override suspend fun getPokemonTypes(): List<PokemonType> {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemonTypeDetails(typeName: String): PokemonType {
        TODO("Not yet implemented")
    }
}
