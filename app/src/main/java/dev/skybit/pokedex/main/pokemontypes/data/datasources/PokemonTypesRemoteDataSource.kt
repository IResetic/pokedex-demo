package dev.skybit.pokedex.main.pokemontypes.data.datasources

import dev.skybit.pokedex.main.core.data.remote.model.PagedResponse

interface PokemonTypesRemoteDataSource {
    suspend fun getPokemonTypes(offset: Int): Result<PagedResponse>
}
