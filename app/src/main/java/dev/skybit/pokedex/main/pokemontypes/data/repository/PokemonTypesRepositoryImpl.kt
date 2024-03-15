package dev.skybit.pokedex.main.pokemontypes.data.repository

import dev.skybit.pokedex.main.core.data.PAGE_SIZE
import dev.skybit.pokedex.main.core.di.IoDispatcher
import dev.skybit.pokedex.main.core.domain.model.PokemonType
import dev.skybit.pokedex.main.pokemontypes.data.datasources.PokemonTypesLocalDataSource
import dev.skybit.pokedex.main.pokemontypes.data.datasources.PokemonTypesRemoteDataSource
import dev.skybit.pokedex.main.pokemontypes.data.local.model.mappers.ResultDtoToPokemonEntityTypeMapper
import dev.skybit.pokedex.main.pokemontypes.domain.repository.PokemonTypesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonTypesRepositoryImpl @Inject constructor(
    private val pokemonTypesRemoteDataSource: PokemonTypesRemoteDataSource,
    private val pokemonTypesLocalDataSource: PokemonTypesLocalDataSource,
    private val resultDtoToPokemonEntityTypeMapper: ResultDtoToPokemonEntityTypeMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : PokemonTypesRepository {

    override suspend fun getPokemonTypes(): List<PokemonType> {
        return withContext(ioDispatcher) {
            pokemonTypesLocalDataSource.getPokemonTypes().map {
                it.toDomain()
            }
        }
    }

    override suspend fun getPokemonTypeBasicIInfoById(pokemonTypeId: Int): PokemonType {
        return withContext(ioDispatcher) {
            pokemonTypesLocalDataSource.getPokemonTypeById(pokemonTypeId).toDomain()
        }
    }

    override suspend fun populatePokemonTypes(offset: Int) {
        val result = pokemonTypesRemoteDataSource.getPokemonTypes(offset)

        if (result.isSuccessful) {
            val body = result.body()
            body?.let {
                val types = it.results?.map { resultDto ->
                    resultDtoToPokemonEntityTypeMapper(resultDto)
                }
                pokemonTypesLocalDataSource.insertOrUpdatePokemonType(types ?: emptyList())

                if (it.next != null) {
                    populatePokemonTypes(offset + PAGE_SIZE)
                }
            }
        } else {
            throw result.errorBody()?.let {
                val error = it.string()
                Exception(error)
            } ?: Exception("An error occurred")
        }
    }
}
