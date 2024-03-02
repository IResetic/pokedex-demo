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

    override suspend fun getPokemonTypeDetails(typeName: String): PokemonType {
        TODO("Not yet implemented")
    }

    override suspend fun populatePokemonTypes(): Resource<Unit> {
        return try {
            fetchNewPokemonTypes(0)

            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }

    private suspend fun fetchNewPokemonTypes(offset: Int) {
        val result = pokemonTypesRemoteDataSource.getPokemonTypes(offset)

        if (result.isSuccessful) {
            val body = result.body()
            body?.let {
                val types = it.results.map { resultDto ->
                    resultDtoToPokemonEntityTypeMapper(resultDto)
                }
                pokemonTypesLocalDataSource.insertPokemonType(types)

                if (it.next != null) {
                    fetchNewPokemonTypes(offset + PAGE_SIZE)
                }
            }
        } else {
            throw Exception("Error fetching pokemon types")
        }
    }
}
