package dev.skybit.pokedex.main.pokemontypes.data.repository

import dev.skybit.pokedex.main.core.data.PAGE_SIZE
import dev.skybit.pokedex.main.core.utils.Resource
import dev.skybit.pokedex.main.pokemontypes.data.datasources.PokemonTypesLocalDataSource
import dev.skybit.pokedex.main.pokemontypes.data.datasources.PokemonTypesRemoteDataSource
import dev.skybit.pokedex.main.pokemontypes.data.remote.mappers.ResultDtoToPokemonEntityTypeMapper
import dev.skybit.pokedex.main.pokemontypes.domain.model.PokemonType
import dev.skybit.pokedex.main.pokemontypes.domain.repository.PokemonTypesRepository
import javax.inject.Inject

class PokemonTypesRepositoryImpl @Inject constructor(
    private val pokemonTypesRemoteDataSource: PokemonTypesRemoteDataSource,
    private val pokemonTypesLocalDataSource: PokemonTypesLocalDataSource,
    private val resultDtoToPokemonEntityTypeMapper: ResultDtoToPokemonEntityTypeMapper
) : PokemonTypesRepository {

    override suspend fun getPokemonTypes(): Resource<List<PokemonType>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemonTypeDetails(typeName: String): PokemonType {
        TODO("Not yet implemented")
    }

    override suspend fun populatePokemonTypes() {
        try {
            fetchNewPokemonTypes(0)
        } catch (e: Exception) {
            throw Exception("Error fetching pokemon types")
        }
    }

    private suspend fun fetchNewPokemonTypes(offset: Int) {
        val result = pokemonTypesRemoteDataSource.getPokemonTypes(offset)

        result.onSuccess { pageResponse ->

            val types = pageResponse.results.map { resultDto ->
                resultDtoToPokemonEntityTypeMapper(resultDto)
            }
            pokemonTypesLocalDataSource.insertPokemonType(types)

            if (pageResponse.next != null) {
                fetchNewPokemonTypes(offset + PAGE_SIZE)
            }
        }.onFailure {
            throw Exception("Error fetching pokemon types")
        }
    }
}
